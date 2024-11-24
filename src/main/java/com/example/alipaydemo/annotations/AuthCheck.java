package com.example.alipaydemo.annotations;

import java.lang.annotation.*;
 
/**
 * 作用在方法上，在运行时通过反射获取信息、将注解加到javadoc中、允许子类继承
 * @author 秋枫艳梦
 * @date 2019-07-18
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AuthCheck {
    //所操作的业务对应的权限ID
    String authId();

}