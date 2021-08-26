package com.steam_discount.steam_discount.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * steam_discount
 * RestTemplateConfig
 *
 * @author yoake
 * @date 2021/5/18 10:00
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate getRestTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(120000);
        factory.setConnectTimeout(240000);
        return factory;
    }
}
