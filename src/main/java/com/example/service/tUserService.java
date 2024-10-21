package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.tUser;

public interface tUserService extends IService<tUser> {
    public tUser get(String username);
}
