package com.steam_discount.steam_discount.controller;

import com.steam_discount.steam_discount.model.ResultVO;
import com.steam_discount.steam_discount.util.ResultVoUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/index")
    public String index() {
        return "index";
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
