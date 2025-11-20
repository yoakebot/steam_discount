package com.steam.discount.service.impl;

import com.steam.discount.client.BuffClient;
import com.steam.discount.constant.Constants;
import com.steam.discount.enums.CategoryGroupEnum;
import com.steam.discount.enums.ExteriorEnum;
import com.steam.discount.enums.QualityEnum;
import com.steam.discount.enums.RarityEnum;
import com.steam.discount.model.GoodsDTO;
import com.steam.discount.model.PageResult;
import com.steam.discount.model.ResultVO;
import com.steam.discount.redis.RedisConstant;
import com.steam.discount.redis.RedissonService;
import com.steam.discount.request.DiscountRequest;
import com.steam.discount.request.Query163Request;
import com.steam.discount.service.BuffJsonService;
import com.steam.discount.util.BigDecimalUtil;
import com.steam.discount.util.EnumUtil;
import com.steam.discount.util.JsonUtil;
import com.steam.discount.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.Executor;

/**
 * steam_discount
 * BuffJsonServiceImpl
 *
 * @author yoake
 * @date 2021/5/18 9:35
 */
@Service
@Slf4j
public class BuffJsonServiceImpl implements BuffJsonService {
    @Value("${save.file.path}")
    private String filePath;

    private final Executor getJsonThreadPool;

    private final BuffClient buffClient;

    private final RedissonService redissonService;

    public BuffJsonServiceImpl(Executor getJsonThreadPool, BuffClient buffClient, RedissonService redissonService) {
        this.getJsonThreadPool = getJsonThreadPool;
        this.buffClient = buffClient;
        this.redissonService = redissonService;
    }


    @Async
    @Override
    public void getGoods(DiscountRequest request) {
        try {
            int pageNum = 1;
            request.setPageNum(pageNum);

            PageResult<GoodsDTO> firstPage = getGoodsDTOPageResult(request);
            int totalPage = firstPage.getTotalPage();
            Set<GoodsDTO> dtoSet = new TreeSet<>(firstPage.getItems());

            long sleepTime = 1000L;
            for (int i = 2; i <= totalPage; i++) {
                if (request.getPageTotal() != 0 && i > request.getPageTotal()) {
                    break;
                }

                log.info("-------- 第{}/{}页，间隔{}ms --------", i, totalPage, sleepTime);
                request.setPageNum(i);

                PageResult<GoodsDTO> page = fetchPageWithRetry(request);

                dtoSet.addAll(page.getItems());
                Thread.sleep(sleepTime);
                sleepTime = Math.max(500, sleepTime - 200);
            }

            redissonService.saveSetExpire(RedisConstant.SAVE_FILE_SET, dtoSet, Duration.ofHours(10));
            log.info("抓取完成，共 {} 条唯一记录", dtoSet.size());
        } catch (Exception e) {
            log.error("商品抓取异常", e);
        }
        log.info("-------- END --------");
    }

    private PageResult<GoodsDTO> fetchPageWithRetry(DiscountRequest request) throws InterruptedException {
        int retryTimes = 2;
        while (retryTimes-- > 0) {
            try {
                return getGoodsDTOPageResult(request);
            } catch (Exception e) {
                log.warn("第{}次获取失败：{}", (3 - retryTimes), e.getMessage());
                Thread.sleep(4000);
            }
        }
        // 最后一次失败，让异常直接抛出去
        return getGoodsDTOPageResult(request);
    }


    public void saveFile(Set<GoodsDTO> sets) {
        List<GoodsDTO> lists = new ArrayList<>(sets);
        List<GoodsDTO> result = lists.parallelStream().filter(l -> l.getBugNum() > 20 && l.getSellNum() > 20).toList();
        String resultText = JsonUtil.toJson(result);
        fileToLocal(resultText);
        log.info("总{}条,过滤剩余{}条", sets.size(), result.size());
    }

    private PageResult<GoodsDTO> getGoodsDTOPageResult(DiscountRequest request) {
        Query163Request query163Request = getQuery163Request(request);
        ResultVO<PageResult<GoodsDTO>> resultVO = buffClient.getGoods(query163Request);
        PageResult<GoodsDTO> page = ResponseUtil.getDataOrThrow(resultVO);
        List<GoodsDTO> list = page.getItems().parallelStream().filter(
                g -> g.getSellNum() >= 10 && g.getBugNum() >= 10 &&
                        g.getSellMinPrice() - (g.getSellMinPrice() * 0.2) < g.getBuyMaxPrice()
        ).toList();
        list.parallelStream().forEach(goods -> {
            double steamPriceCny = goods.getGoodsInfo().getSteamPriceCny();
            double discount = BigDecimalUtil.evalDiscount(goods.getSellMinPrice(), goods.getGoodsInfo().getSteamPriceCny());
            double steamBalanceAfterTax = BigDecimalUtil.evalPrice(steamPriceCny * 0.8695);
            double discountAfterTax = BigDecimalUtil.evalDiscount(goods.getSellMinPrice(), steamBalanceAfterTax);
            double steamPriceSellThird = BigDecimalUtil.evalPrice(steamBalanceAfterTax * request.getCustomDiscount());
            double discountSellThird = BigDecimalUtil.evalDiscount(goods.getSellMinPrice(), steamPriceSellThird);
            goods.setSteamPriceCnyProfit(BigDecimalUtil.evalPrice(steamPriceCny - goods.getSellMinPrice()))
                    .setDiscount(discount)
                    .setDiscountAfterTax(discountAfterTax)
                    .setSteamBalanceAfterTax(steamBalanceAfterTax)
                    .setSteamBalanceAfterTaxProfit(BigDecimalUtil.evalPrice(steamBalanceAfterTax - goods.getSellMinPrice()))
                    .setDiscountSellThird(discountSellThird)
                    .setSteamPriceSellThird(steamPriceSellThird)
                    .setSteamPriceSellThirdProfit(BigDecimalUtil.evalPrice(steamPriceSellThird - goods.getSellMinPrice()))
                    .setBuffUrl(Constants.URL.formatted(goods.getId()));
        });
        Set<GoodsDTO> sets = new TreeSet<>(list);
        redissonService.saveSetExpire(RedisConstant.RESULT_SET, sets, Duration.ofDays(10));
        return page;
    }

    private static Query163Request getQuery163Request(DiscountRequest request) {
        Query163Request query163Request = new Query163Request();
        query163Request.setGame("csgo");
        query163Request.setPage_num(request.getPageNum());
        if (Objects.equals(request.getCategoryGroup(), 10)) {
            query163Request.setCategory(EnumUtil.getDesc(request.getCategoryGroup(), CategoryGroupEnum.class));
        } else {
            query163Request.setCategory_group(EnumUtil.getDesc(request.getCategoryGroup(), CategoryGroupEnum.class));
        }
        query163Request.setRarity(EnumUtil.getDesc(request.getRarity(), RarityEnum.class));
        query163Request.setQuality(EnumUtil.getDesc(request.getQuality(), QualityEnum.class));
        if (StringUtils.hasText(EnumUtil.getDesc(request.getExterior(), ExteriorEnum.class)))
            query163Request.setExterior(EnumUtil.getDesc(request.getExterior(), ExteriorEnum.class));
        query163Request.setTab("selling");
        query163Request.setMin_price(request.getMinPrice());
        query163Request.setMax_price(request.getMaxPrice());
        return query163Request;
    }

    private void fileToLocal(String resultText) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(resultText);
        } catch (IOException e) {
            log.error("写入失败");
        }
        log.info("写入完毕");
    }
}
