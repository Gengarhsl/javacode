package com.example.alipaydemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import com.example.alipaydemo.controller.pojo.tUser;


public interface tUserService extends IService<tUser> {
    public tUser getById(String username);

    IPage getPage(String username);


    Object listData();
}
