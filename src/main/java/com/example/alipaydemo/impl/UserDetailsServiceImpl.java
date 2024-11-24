package com.example.alipaydemo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private tUserServiceImpl userServiceImpl;

    //根据用户名加载用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.example.alipaydemo.controller.pojo.tUser user = userServiceImpl.getById(username);
        //1. 模拟从数据库查询用户
        if(user.getUsername().isEmpty()){//如果不是admin，那么就表示不是我数据库中的用户，抛出异常或进行逻辑处理
            // 用户不存在的逻辑，我这里直接抛个异常表示一下
            throw new UsernameNotFoundException("用户不存在！！！");
        }
        //2. 模拟，从数据库查询到了用户信息，用户存在的逻辑
        //2.1 封装密码
        String password = user.getPassword();//将查询到达密码封装

        //对密码加密,其实，我们一般在存储用户到数据库的时候，保存密码，直接保存这个加密后的
        String encodePassword = passwordEncoder.encode(password);
        System.out.println(password+"加密后-------------"+encodePassword);

        //判断源字符加密后和内容是否匹配
        boolean matches = passwordEncoder.matches(password, encodePassword);
        System.out.println("原密码和加密后是否匹配-------"+matches);

        //2.2 封装权限，假设这个用户查到了两个权限字符，admin1和admin2
        List<String> list = new ArrayList<>();
        list.add("admin1");
        list.add("admin2");
        //2.3 拼接权限
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i<list.size();i++){
            //拼接成这样 "admin1,admin2"
            if(i==(list.size()-1)){//当权限字符是最后一个，不加逗号
                stringBuilder.append(list.get(i));
            }else{
                stringBuilder.append(list.get(i)+",");
            }
        }
        System.out.println(stringBuilder.toString());
        //2.4 因为权限字符拼接非常麻烦，所以spring security提供了AuthorityUtils工具类，帮我们封装，参数就是权限字符通过逗号分隔，比如"admin1,admin2"
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(stringBuilder.toString());
        //3. 返回UserDetails
        UserDetails userDetails = new User(username, encodePassword, grantedAuthorities);

        return userDetails;
    }
}
