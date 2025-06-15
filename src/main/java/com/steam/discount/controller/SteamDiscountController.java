package com.steam.discount.controller;

import com.steam.discount.model.GoodsDTO;
import com.steam.discount.redis.RedisConstant;
import com.steam.discount.redis.RedissonService;
import com.steam.discount.request.DiscountRequest;
import com.steam.discount.service.BuffJsonService;
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
@RestController("discount")
public class SteamDiscountController {

    @Resource
    private BuffJsonService buffJsonService;

    @Resource
    private RedissonService redissonService;

    @Resource
    private RedisTemplate<String, List<GoodsDTO>> redisTemplate;

    @Operation(summary = "获取折扣")
    @GetMapping("/get")
    @Cacheable(cacheNames = "getDiscount", key = "#request.toString()")
    public Set<GoodsDTO> getDiscount(@ParameterObject DiscountRequest request) {
        redissonService.saveList(RedisConstant.COOKIES, List.of(request.getCsrfToken(), request.getSession()));
        Set<GoodsDTO> goods = buffJsonService.getGoods(request);
        redissonService.saveSet(RedisConstant.SAVE_FILE, goods);
        return goods;
    }

    @Operation(summary = "缓存保存")
    @GetMapping("/saveFile")
    public void saveFile() {
        Set<GoodsDTO> sets = redissonService.getSet(RedisConstant.SAVE_FILE);
        buffJsonService.saveFile(sets);
    }

    @GetMapping("/rank")
    public List<GoodsDTO> getRank() {
        return redisTemplate.opsForValue().get(RedisConstant.SAVE_FILE);
    }
}
