package com.example.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.tUser;
import com.example.mapper.tUserMapper;
import com.example.service.tUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class tUserServiceImpl extends ServiceImpl<tUserMapper, tUser> implements tUserService {

    @Autowired
    private tUserMapper tuserMapper;

    @Override
    public tUser get(String username){
        LambdaQueryWrapper<tUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(tUser::getUsername,username);
        return tuserMapper.selectOne(wrapper);
    }
}
