package com.example.alipaydemo.config;

import com.example.alipaydemo.config.properties.SingleServerConfig;
import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RedissonProperties {


    @Bean
    @ConfigurationProperties(prefix = "spring.redis.redisson")
    public SingleServerConfig templateProperties(){
        return new SingleServerConfig();
    }
}