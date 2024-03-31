package com.whxiaoyu.cloud.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步线程池配置
 * @author jinxiaoyu
 */
@Configuration
@EnableAsync
public class AsyncTaskExecutorConfig {

    @Bean
    public TaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        taskExecutor.setCorePoolSize(2);
        //配置最大核心线程数
        taskExecutor.setMaxPoolSize(4);
        //配置队列容量
        taskExecutor.setQueueCapacity(200);
        //设置线程活跃时间
        taskExecutor.setKeepAliveSeconds(60);
        //设置线程名
        taskExecutor.setThreadNamePrefix("customizeAsyncTask-");
        //设置拒绝策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }
}
