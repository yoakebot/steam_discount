package com.steam_discount.steam_discount;

import com.steam_discount.steam_discount.service.BuffJsonService;
import com.steam_discount.steam_discount.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SteamDiscountApplication {

    public static void main(String[] args) {
        SpringApplication.run(SteamDiscountApplication.class, args);
        BuffJsonService buffJsonService = SpringUtil.getBean(BuffJsonService.class);
        buffJsonService.writeJson(0.8);
    }

}
