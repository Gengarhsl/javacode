package com.example.alipaydemo.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.example.alipaydemo.handler.MyTenantLineHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        /**
         * 多租户配置
         */
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new MyTenantLineHandler()));

        /**
         * 分页插件
         */
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}