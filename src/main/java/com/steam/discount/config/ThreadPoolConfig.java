package com.steam.discount.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * steam_discount
 * ThreadPoolConfig
 *
 * @author yoake
 * @date 2021/5/18 22:36
 */
@Slf4j
@Configuration
public class ThreadPoolConfig {

//    @Bean(name = "getJsonThreadPool")
//    public Executor getJsonThreadPool() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(3);
//        executor.setMaxPoolSize(6);
//        executor.setQueueCapacity(8);
//        executor.setThreadNamePrefix("jsonThread");
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        executor.initialize();
//        log.info("线程池初始化完毕");
//        return executor;
//    }

    @Bean(name = "getJsonThreadPool")
    public Executor getJsonThreadPool() {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        log.info("虚拟线程池初始化完毕");
        return executor;
    }
}
