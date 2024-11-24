package com.example.alipaydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties
@MapperScan("com.example.alipaydemo.mapper")
public class AlipayDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlipayDemoApplication.class, args);
    }

}
