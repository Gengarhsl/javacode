package com.example.alipaydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    /**
     * 配置密码解析Bean实例
     * 加载到IOC容器
     * 这是Spring Security 自定义登录逻辑的硬性要求
     * @return
     */
    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
