package com.steam.discount.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
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
        config.setCodec(new JsonJacksonCodec());
        return Redisson.create(config);
    }
}
