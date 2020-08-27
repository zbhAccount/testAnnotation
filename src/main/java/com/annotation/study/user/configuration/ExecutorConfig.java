package com.annotation.study.user.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@Slf4j
// @PropertySource(value = "classpath:config/executor.properties")
public class ExecutorConfig {
//    @Value("${corePoolSize}")
//    private int corePoolSize;
//    @Value("${maxPoolSize}")
//    private int maxPoolSize;
//    @Value("${queueCapacity}")
//    private int queueCapacity;
//    @Value("${threadNamePrefix}")
//    private String threadNamePrefix;
//    @Value("${allowCoreThreadTimeOut}")
//    private boolean allowCoreThreadTimeOut;
//    private RejectedExecutionHandler rejectedExecutionHandler;
//    @Value("${keepAliveSeconds}")
//    private int keepAliveSeconds;

    @Bean(name="createServiceExecutor")
    public Executor createServiceExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setAllowCoreThreadTimeOut(true);
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("girl-service");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        return executor;
    }

    @Bean(name="updateServiceExecutor")
    public Executor updateServiceExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setAllowCoreThreadTimeOut(true);
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(5000);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("girl-service");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        return executor;
    }

    @Bean(name="asyncServiceExecutor")
    public Executor asyncServiceExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setAllowCoreThreadTimeOut(true);
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(2000);
        executor.setThreadNamePrefix("girl-service");
        executor.initialize();
        return executor;
    }
}
