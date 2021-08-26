package com.steam_discount.steam_discount.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * steam_discount
 * RedisTemplateConfig
 *
 * @author yoake
 * @date 2021/5/18 15:14
 */
@Configuration
public class RedisTemplateConfig {

    @Bean
    public RedisTemplate<Object, Object> setRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new GenericFastJsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericFastJsonRedisSerializer());
        return redisTemplate;
    }
}
