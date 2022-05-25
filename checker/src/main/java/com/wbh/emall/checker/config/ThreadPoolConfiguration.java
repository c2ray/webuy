package com.wbh.emall.checker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author WBH
 * @since 2021/12/21
 */
@Configuration(proxyBeanMethods = false)
@EnableAsync

public class ThreadPoolConfiguration implements AsyncConfigurer {
    
    @Override
    @Bean("checkExecutor")
    public Executor getAsyncExecutor() {
        int processorNumber = Runtime.getRuntime().availableProcessors();
        // new ThreadPoolExecutor()
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数：线程池创建时候初始化的线程数
        executor.setCorePoolSize(processorNumber / 2 + 1);
        // 最大线程数：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(processorNumber  + 1);
        // 缓冲队列：用来缓冲执行任务的队列
        executor.setQueueCapacity(0);
        // 允许线程的空闲时间60秒：当超过了核心线程之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(60);
        // 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        executor.setThreadNamePrefix("checker");
        // 缓冲队列满了之后的拒绝策略：由调用线程处理（一般是调用者线程）
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
    
}
