package com.steam.discount.controller;

import com.steam.discount.model.ResultVO;
import com.steam.discount.util.ResultVoUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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

    public IndexController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/index")
    public String index() {
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
    public String post(@PathVariable(required = false) String id) {
        return id;
    }


}
