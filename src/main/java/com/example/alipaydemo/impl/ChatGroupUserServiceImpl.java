package com.example.alipaydemo.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.alipaydemo.entity.ChatGroupUser;
import com.example.alipaydemo.mapper.ChatGroupMapper;
import com.example.alipaydemo.mapper.ChatGroupUserMapper;
import com.example.alipaydemo.service.ChatGroupUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hsl
 * @version 1.0
 * @date 2024-09-14 18:06
 */

@Slf4j
@Service
public class ChatGroupUserServiceImpl extends ServiceImpl<ChatGroupUserMapper, ChatGroupUser> implements ChatGroupUserService {

    @Resource
    private ChatGroupUserMapper chatGroupUserMapper;
}
