package com.steam_discount.steam_discount.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.steam_discount.steam_discount.model.Goods;
import com.steam_discount.steam_discount.service.BuffJsonService;
import com.steam_discount.steam_discount.util.BigDecimalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
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
    private final static String session = "session=1-dUoMjaofClt14Sz-alZ0U5Ef-h9HcMZax7_MxyZdvCJh2046405441; HttpOnly; Path=/";
    private final static String csrfToken = "csrf_token=ImYzOTkyZjJkN2YzZGMyNmVjOTdlMGI2OTMwMjU1YzJlOGJhNWUwYTEi.Z9Z_RQ.XuE22-ApvMeS8_hMhOTdwawGaNc; Path=/";

    private final static Object[] prices = {50, 300};
//    private final static String quality = "&quality=normal";
    private final static String quality = "&quality=normal";
    private final static String rarity = "&rarity=ancient_weapon";
    private final static String categoryGroup = "&category_group=rifle";

    private final RestTemplate restTemplate;

    private final Executor getJsonThreadPool;

    public BuffJsonServiceImpl(RestTemplate restTemplate, Executor getJsonThreadPool) {
        this.restTemplate = restTemplate;
        this.getJsonThreadPool = getJsonThreadPool;
    }

    @Override
    public void writeJson(double customDiscount) {
        int pageNum = 1;

        String str = "https://buff.163.com/api/market/goods?game=csgo&min_price=%s&max_price=%s&sort_by=price.asc&_=1623511969929&pageSize=80"
                + quality + rarity +
//                categoryGroup +
                "&page_num=";
        String urlBase = String.format(str, prices);

        Set<Goods> sets = new TreeSet<>();
        int totalPage = getJsonResult(pageNum, sets, urlBase, customDiscount);
        log.info("共{}页", totalPage);
        CountDownLatch latch = new CountDownLatch(totalPage - 1);
        for (pageNum = 2; pageNum <= totalPage; pageNum++) {
            int finalPageNum = pageNum;
            getJsonThreadPool.execute(() -> {
                try {
                    if (finalPageNum > 10) {
                        Thread.sleep(1500);
                    }
                    getJsonResult(finalPageNum, sets, urlBase, customDiscount);
                } catch (Exception e) {
                    log.error(e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        generateResult(sets);
    }

    private void generateResult(Set<Goods> sets) {
        List<Goods> lists = new ArrayList<>(sets);
        List<Goods> result = lists.subList(0, Math.min(lists.size(), 50));
        String resultText = JSON.toJSONString(result);
        fileToLocal(resultText);
        log.info("总{}条,过滤剩余{}条", sets.size(), result.size());
    }

    private void fileToLocal(String resultText) {
        String filePath = "E:\\Downloads\\steam.json";
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath);
            writer.write(resultText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        log.info("写入完毕");
    }

    private int getJsonResult(int pageNum, Set<Goods> sets, String urlBase, double customDiscount) {
        String url = urlBase + pageNum;
        HttpHeaders headers = new HttpHeaders();

        List<String> cookies = new ArrayList<>();
        /* 登录获取Cookie 这里是直接给Cookie，可使用下方的login方法拿到Cookie给入*/
        //在 header 中存入cookies
        cookies.add(session);
        cookies.add(csrfToken);
        headers.put(HttpHeaders.COOKIE, cookies);

        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String s = response.getBody();
        JSONObject result = (JSONObject) JSON.parse(s);
        assert result != null;
        log.info("当前第{}页", pageNum);
        JSONObject data = result.getJSONObject("data");
        Integer totalPage = data.getObject("total_page", Integer.class);
        addList(sets, data, customDiscount);
        return totalPage;
    }

    private void addList(Set<Goods> sets, JSONObject data, double customDiscount) {
        JSONArray items = data.getJSONArray("items");
        for (int i = 0; i < items.size(); i++) {
            JSONObject result = items.getJSONObject(i);
            double sellMinPrice = result.getObject("sell_min_price", Double.class);
            String id = result.getObject("id", String.class);
            String game = result.getObject("game", String.class);
            String name = result.getObject("name", String.class);
            double buyMaxPrice = result.getObject("buy_max_price", Double.class);
            int bugNum = result.getObject("buy_num", Integer.class);
            double quickPrice = result.getObject("quick_price", Double.class);
            int sellNum = result.getObject("sell_num", Integer.class);
            JSONObject goodsInfo = result.getJSONObject("goods_info");
            double steamPriceCny = goodsInfo.getObject("steam_price_cny", Double.class);
            double discount = BigDecimalUtil.evalDiscount(sellMinPrice / steamPriceCny);
            double steamBalanceAfterTax = BigDecimalUtil.evalPrice(steamPriceCny * 0.8695);
            double steamPriceSellThird = BigDecimalUtil.evalPrice(steamBalanceAfterTax * customDiscount);
            double discountAfterTax = BigDecimalUtil.evalDiscount(sellMinPrice / steamBalanceAfterTax);
            double discountSellThird = BigDecimalUtil.evalDiscount(sellMinPrice / steamPriceSellThird);
            if (bugNum < 5 || discount >= 0.75) {
                continue;
            }
            String url = "https://buff.163.com/market/goods?goods_id=" + id + "&from=market#tab=selling";
            Goods goods = new Goods()
                    .setId(id)
                    .setName(name)
                    .setGame(game)
                    .setBuyMaxPrice(buyMaxPrice)
                    .setBugNum(bugNum)
                    .setQuickPrice(quickPrice)
                    .setSellMinPrice(sellMinPrice)
                    .setSellNum(sellNum)
                    .setSteamPriceCny(steamPriceCny)
                    .setSteamPriceCnyProfit(BigDecimalUtil.evalPrice(steamPriceCny - sellMinPrice))
                    .setDiscount(discount)
                    .setDiscountAfterTax(discountAfterTax)
                    .setSteamBalanceAfterTax(steamBalanceAfterTax)
                    .setSteamBalanceAfterTaxProfit(BigDecimalUtil.evalPrice(steamBalanceAfterTax - sellMinPrice))
                    .setDiscountSellThird(discountSellThird)
                    .setSteamPriceSellThird(steamPriceSellThird)
                    .setSteamPriceSellThirdProfit(BigDecimalUtil.evalPrice(steamPriceSellThird - sellMinPrice))
                    .setBuffUrl(url);
            sets.add(goods);
        }
    }
}
