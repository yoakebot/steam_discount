package com.steam.discount.controller;

import com.steam.discount.model.Boy;
import com.steam.discount.model.ResultVO;
import com.steam.discount.mq.MQConstants;
import com.steam.discount.mq.MQSendService;
import com.steam.discount.util.ResultVoUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * steam_discount
 * IndexController
 *
 * @author yoake
 * @date 2021/5/18 16:14
 */
@Tag(name = "测试")
@RestController
public class IndexController {

    private final Environment environment;
    private final MQSendService mqSendService;

    public IndexController(Environment environment, MQSendService mqSendService) {
        this.environment = environment;
        this.mqSendService = mqSendService;
    }

    @GetMapping("/index")
    public Object index() {
        return Arrays.toString(environment.getActiveProfiles());
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
    public Object post(@PathVariable(required = false) String id) {
        return id + "success";
    }

    @Operation(summary = "测试mq")
    @PostMapping({"/send"})
    public Object send() {
        mqSendService.send(MQConstants.EXCHANGE_TEST, MQConstants.ROUTING_TEST, new Boy("1", "2", "3", LocalDateTime.now()));
        return "success";
    }

    @Operation(summary = "测试mq sendDelay")
    @PostMapping({"/sendDelay"})
    public Object sendDelay() {
        mqSendService.sendDelay(MQConstants.EXCHANGE_DELAY, MQConstants.ROUTING_KEY_DELAY, new Boy("1", "2", "3", LocalDateTime.now()), "5000");
        return "success";
    }
}
