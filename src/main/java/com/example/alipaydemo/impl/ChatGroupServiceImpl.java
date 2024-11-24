package com.example.alipaydemo.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.alipaydemo.controller.pojo.ChatGroup;
import com.example.alipaydemo.mapper.ChatGroupMapper;
import com.example.alipaydemo.service.ChatGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hsl
 * @version 1.0
 * @date 2024-09-14 17:25
 */

@Slf4j
@Service
public class ChatGroupServiceImpl extends ServiceImpl<ChatGroupMapper, ChatGroup> implements ChatGroupService {

    @Resource
    private ChatGroupMapper chatGroupMapper;
}
