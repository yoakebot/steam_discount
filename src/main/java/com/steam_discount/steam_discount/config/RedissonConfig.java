package com.steam_discount.steam_discount.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${user.redisson.url}")
    private String url;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(url)
                .setConnectionPoolSize(64)
                .setConnectionMinimumIdleSize(10)
                .setTimeout(10000);
        return Redisson.create(config);
    }
}
