package com.steam.discount.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("api")
                        .version("1.0.0")
                        .description("这是一个使用 Swagger 3 的 Spring Boot API 文档示例")
                );
    }
}
