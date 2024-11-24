package com.example.alipaydemo.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.alipaydemo.controller.pojo.Message;
import com.example.alipaydemo.mapper.MessageMapper;
import com.example.alipaydemo.service.MassageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hsl
 * @version 1.0
 * @date 2024-09-13 13:39
 */


@Service
@Slf4j
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MassageService {

    @Resource
    private MessageMapper messageMapper;


}
