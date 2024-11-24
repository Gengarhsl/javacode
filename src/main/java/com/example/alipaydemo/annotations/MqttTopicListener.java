package com.example.alipaydemo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author hsl
 * mqtt注解
 */
@Target(ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface MqttTopicListener {

    String value(); // 主题名称

    int qos() default 0; // QoS级别，默认为0
    
    //boolean hookFlag() default false; //钩子标记
}
