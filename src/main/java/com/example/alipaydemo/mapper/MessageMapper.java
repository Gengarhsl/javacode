package com.example.alipaydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.alipaydemo.controller.pojo.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * 聊天消息 mapper
 *
 * @author hsl
 * @date 2021/7/26 16:08
 * 消息
 */

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
