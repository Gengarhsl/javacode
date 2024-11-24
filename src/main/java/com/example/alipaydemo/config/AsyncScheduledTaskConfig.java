package com.example.alipaydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  线程池 配置
 * @author hpkitty
 * @version 1.0
 * @date 2022/4/24
 * @project clouds-work
 */
@Component
public class AsyncScheduledTaskConfig {

    /**
     * 线程池 前缀
     */
    private final static String CANAL_SYNC="canal-sync";

    /**
     * 配置 线程池
     * @return
     */
    @Bean
    public Executor  myAsync(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //最大线程数
        executor.setMaxPoolSize(10);
        //核心线程数
        executor.setCorePoolSize(5);
        //任务队列的大小
        executor.setQueueCapacity(10);
        //线程前缀名
        executor.setThreadNamePrefix(CANAL_SYNC);
        //线程存活时间
        executor.setKeepAliveSeconds(30);
        /**
         * 拒绝处理策略
         * CallerRunsPolicy()：交由调用方线程运行，比如 main 线程。
         * AbortPolicy()：丢弃任务并抛出 RejectedExecutionException 异常。
         * DiscardPolicy()：直接丢弃。
         * DiscardOldestPolicy()：丢弃队列中最老的任务。
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        //线程初始化
        executor.initialize();
        return executor;
    }



}