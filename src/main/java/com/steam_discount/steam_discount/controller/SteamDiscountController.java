package com.steam_discount.steam_discount.controller;

import com.steam_discount.steam_discount.config.RedisConstant;
import com.steam_discount.steam_discount.config.RedissonService;
import com.steam_discount.steam_discount.model.Goods;
import com.steam_discount.steam_discount.model.GoodsDTO;
import com.steam_discount.steam_discount.model.ResultVO;
import com.steam_discount.steam_discount.request.DiscountRequest;
import com.steam_discount.steam_discount.service.BuffJsonService;
import com.steam_discount.steam_discount.util.ResultVoUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * steam_discount
 * SteamDiscountController
 *
 * @author yoake
 * @date 2021/5/18 14:56
 */
@Tag(name = "折扣获取")
@Slf4j
@RestController
public class SteamDiscountController {

    @Resource
    private BuffJsonService buffJsonService;

    @Resource
    private RedissonService redissonService;

    @Resource
    private RedisTemplate<String, List<Goods>> redisTemplate;

    @Operation(summary = "获取折扣")
    @GetMapping("/get/discount")
    @Cacheable(cacheNames = "getDiscount", key = "#request.toString()")
    public ResultVO<Set<GoodsDTO>> getDiscount(@ParameterObject DiscountRequest request) {
        redissonService.saveList(RedisConstant.COOKIES, List.of(request.getCsrfToken(), request.getSession()));
        Set<GoodsDTO> goods = buffJsonService.getGoods(request);
        redissonService.saveSet(RedisConstant.SAVE_FILE, goods);
        return ResultVoUtil.success(goods);
    }

    @Operation(summary = "缓存保存")
    @GetMapping("/saveFile")
    public ResultVO<Void> saveFile() {
        Set<GoodsDTO> sets = redissonService.getSet(RedisConstant.SAVE_FILE);
        buffJsonService.saveFile(sets);
        return ResultVoUtil.success();
    }

    @GetMapping("/getRank")
    public ResultVO<List<Goods>> getRank() {
        List<Goods> goods = redisTemplate.opsForValue().get("GOODS:RANK");
        return ResultVoUtil.success(goods);
    }
}
