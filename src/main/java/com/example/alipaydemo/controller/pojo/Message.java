package com.example.alipaydemo.controller.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "messages")
@ApiModel("即时通信消息类")
public class Message {

    @ApiModelProperty("即时通信消息主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("发送人id")
    @TableField(value = "sender_id")
    private String senderId;

    @ApiModelProperty("接收人id")
    @TableField(value = "receiver_id")
    private String receiverId;

    @ApiModelProperty("消息内容")
    @TableField(value = "content")
    private String content;

    @ApiModelProperty("消息内容类型")
    @TableField(value = "content_type")
    private String contentType;

    @ApiModelProperty("聊天类型")
    @TableField(value = "chat_type")
    private String chatType;

    @ApiModelProperty("群聊id")
    @TableField(value = "group_id")
    private String groupId;

    @ApiModelProperty("发送时间")
    @TableField(value = "time_stamp")
    private long timestamp;

    @ApiModelProperty("阅读标识")
    @TableField(value = "is_read")
    private boolean isRead;

    @ApiModelProperty("发送标识")
    @TableField(value = "is_Sent")
    private boolean isSent;

    // Getters and Setters

}