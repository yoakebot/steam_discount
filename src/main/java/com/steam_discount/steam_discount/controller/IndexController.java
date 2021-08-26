package com.steam_discount.steam_discount.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * steam_discount
 * IndexController
 *
 * @author yoake
 * @date 2021/5/18 16:14
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
