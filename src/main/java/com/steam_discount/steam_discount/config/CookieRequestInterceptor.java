package com.steam_discount.steam_discount.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookieRequestInterceptor implements RequestInterceptor {

    @Resource
    private RedissonService redissonService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String cookieHeader = String.join("; ", redissonService.getList(RedisConstant.COOKIES));
        requestTemplate.header("Cookie", cookieHeader);
    }
}
