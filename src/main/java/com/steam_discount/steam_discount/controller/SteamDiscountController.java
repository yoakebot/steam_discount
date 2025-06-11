package com.steam_discount.steam_discount.controller;

import com.steam_discount.steam_discount.model.Boy;
import com.steam_discount.steam_discount.model.Goods;
import com.steam_discount.steam_discount.model.ResultVO;
import com.steam_discount.steam_discount.service.BuffJsonService;
import com.steam_discount.steam_discount.util.ResultVoUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * steam_discount
 * SteamDiscountController
 *
 * @author yoake
 * @date 2021/5/18 14:56
 */
@Tag(name = "测试")
@Slf4j
@RestController
public class SteamDiscountController {

    @Resource
    private BuffJsonService buffJsonService;

    @Resource
    private RedisTemplate<String, List<Goods>> redisTemplate;

    @GetMapping("/hello")
    public ResultVO<Boy> hello(@ModelAttribute Boy boy) {
        return ResultVoUtil.success(boy);
    }

    @GetMapping("/addCache")
    public ResultVO<String> addCache() {
        try {
            buffJsonService.writeJson(0.8);
        } catch (Exception e) {
            log.error("", e);
            return ResultVoUtil.fail("error");
        }
        return ResultVoUtil.success();
    }

    @GetMapping("/getRank")
    public ResultVO<List<Goods>> getRank() {
        List<Goods> goods = redisTemplate.opsForValue().get("GOODS:RANK");
        return ResultVoUtil.success(goods);
    }

    @GetMapping("/writeFile")
    public ResultVO<Void> writeFile() {
        buffJsonService.writeJson(0.8);
        return ResultVoUtil.success();
    }

    @Operation(summary = "测试get")
    @GetMapping("/get")
    public ResultVO<List<String>> get(@RequestParam(required = false) List<String> id) {
        if (CollectionUtils.isEmpty(id)) {
            return ResultVoUtil.fail("失败");
        }
        return ResultVoUtil.success(id);
    }

    @Operation(summary = "测试post")
    @PostMapping({"/post", "/post/{id}"})
    public ResultVO<String> post(@PathVariable(required = false) String id) {
        return ResultVoUtil.success(id);
    }


}
