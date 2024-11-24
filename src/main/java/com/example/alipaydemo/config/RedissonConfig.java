package com.example.alipaydemo.config;

import com.example.alipaydemo.config.properties.SingleServerConfig;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RedissonConfig {

    @Autowired
    private SingleServerConfig singleServerConfig;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.71.134:6379");
//        config.useClusterServers().addNodeAddress("redis://192.168.71.134:6379");
        return Redisson.create(config);
    }
}