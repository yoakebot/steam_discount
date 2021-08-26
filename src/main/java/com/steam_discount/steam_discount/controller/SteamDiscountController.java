package com.steam_discount.steam_discount.controller;

import com.steam_discount.steam_discount.model.Goods;
import com.steam_discount.steam_discount.model.Msg;
import com.steam_discount.steam_discount.service.BuffJsonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * steam_discount
 * SteamDiscountController
 *
 * @author yoake
 * @date 2021/5/18 14:56
 */
@Slf4j
@RestController
public class SteamDiscountController {

    @Resource
    private BuffJsonService buffJsonService;

    @Resource
    private RedisTemplate<String, List<Goods>> redisTemplate;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Msg hello() {
        return Msg.success();
    }

    @RequestMapping(value = "/addCache", method = RequestMethod.GET)
    public Msg addCache() {
        try {
            buffJsonService.writeJson(0.8);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail("error");
        }
        return Msg.success();
    }

    @RequestMapping(value = "/getRank", method = RequestMethod.GET)
    public Msg getRank() {
        List<Goods> goods = redisTemplate.opsForValue().get("GOODS:RANK");
        return Msg.success().add("goods", goods);
    }

    @RequestMapping(value = "/writeFile", method = RequestMethod.GET)
    public Msg writeFile() {
        buffJsonService.writeJson(0.8);
        return Msg.success();
    }
}
