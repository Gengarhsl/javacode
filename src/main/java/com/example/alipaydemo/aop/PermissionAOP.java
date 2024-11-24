package com.example.alipaydemo.aop;

import com.alibaba.fastjson.JSON;
import com.example.alipaydemo.annotations.AuthCheck;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
 
import javax.servlet.http.HttpServletRequest;
 
/**
 *  AOP权限拦截器
 * @author 秋枫艳梦
 * @date 2019-07-18
 * */
@Aspect
@Component
public class PermissionAOP {
 
    //拦截所有被注解AuthCheck标注的方法
    @Pointcut("@annotation(com.example.alipaydemo.annotations.AuthCheck)")
    private void pointAll(){}
 
    /**
     *  环绕增强，验证权限
     * @param joinPoint 目标对象
     * @param authCheck 自定义的注解，Around必须这样写，否则自定义的注解无法传入
     * */
    @Around("pointAll() && @annotation(authCheck)")
    public Object before(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        //先拿到Request请求体
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("此次请求的路径："+request.getRequestURL());
        System.out.println("此次业务操作的权限ID："+authCheck.authId());
        //真实环境下应该是从session或Redis中拿到登录的用户信息，再去与权限ID做匹配
        System.out.println("模拟查询数据库或其他存储介质，验证当前用户是否有权限");
 
        //如果传的参数值不是admin，则驳回请求
        if (!request.getParameter("message").equals("admin")){
            return JSON.parseObject("{\"message\":\"no auth\",\"code\":403}");
        }
        return joinPoint.proceed();
    }
}
