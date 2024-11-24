package com.example.alipaydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.alipaydemo.entity.ChatGroupUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 群聊人员表 Mapper 接口
 *
 * @author hsl
 * @since 2024-07-25
 */
@Mapper
public interface ChatGroupUserMapper extends BaseMapper<ChatGroupUser> {
}
