package com.steam.discount.controller;

import com.steam.discount.model.Boy;
import com.steam.discount.model.ResultVO;
import com.steam.discount.mq.MQConstants;
import com.steam.discount.mq.MQSendService;
import com.steam.discount.service.TestService;
import com.steam.discount.util.ResultVoUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private final TestService testService;

    public IndexController(Environment environment, MQSendService mqSendService, TestService testService) {
        this.environment = environment;
        this.mqSendService = mqSendService;
        this.testService = testService;
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

    @Operation(summary = "cache")
    @GetMapping("/cache")
    public Object cache(@RequestParam("id") Integer id) {
        return testService.cache(new Boy(id, id+ "", id+ ""));
    }

    @Operation(summary = "del")
    @PostMapping({"/del"})
    public Object del(@RequestParam(required = false, name = "id") Integer id) {
        return testService.del(id);
    }

    @Operation(summary = "测试mq")
    @PostMapping({"/send"})
    public Object send() {
        mqSendService.send(MQConstants.EXCHANGE_TEST, MQConstants.ROUTING_TEST, new Boy(1, "2", "3", LocalDateTime.now()));
        return "success";
    }

    @Operation(summary = "测试mq sendDelay")
    @PostMapping({"/sendDelay"})
    public Object sendDelay() {
        mqSendService.sendDelay(MQConstants.EXCHANGE_DELAY, MQConstants.ROUTING_KEY_DELAY, new Boy(1, "2", "3", LocalDateTime.now()), "5000");
        return "success";
    }
}
