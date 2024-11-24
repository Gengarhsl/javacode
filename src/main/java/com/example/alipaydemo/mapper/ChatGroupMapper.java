package com.example.alipaydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.alipaydemo.controller.pojo.ChatGroup;
import org.apache.ibatis.annotations.Mapper;

/**
 * 群聊表 mapper
 *
 * @author hsl
 * @version 1.0
 * @date 2024/9/14 17:08
 * @description 群聊表
 */
@Mapper
public interface ChatGroupMapper extends BaseMapper<ChatGroup> {
}
