package com.example.alipaydemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hsl
 * @version 1.0
 * @date 2024-09-14 17:28
 */


@Data
@ApiModel("群聊人员表")
@TableName(value = "chat_group_user")
public class ChatGroupUser {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty("id")
    private String id;

    @TableField(value = "group_id")
    @ApiModelProperty("群聊id")
    private String groupId;

    @TableField(value = "user_id")
    @ApiModelProperty("用户id")
    private String userId;

    @TableField(value = "is_admin")
    @ApiModelProperty("是否为管理员")
    private Boolean isAdmin;

    @TableField(value = "del_flag")
    @TableLogic
    @ApiModelProperty("是否删除 0_未删除 1_已删除")
    private Integer delFlag;

    @ApiModelProperty("创建人")
    @TableField(value = "creator",fill = FieldFill.INSERT)
    private String creator;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("修改人")
    @TableField(value = "updater",fill = FieldFill.INSERT_UPDATE)
    private String updater;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("修改时间")
    private String updateTime;

    @TableField(value = "is_read")
    @ApiModelProperty("是否已读")
    private Boolean isRead;

    @TableField(value = "is_join")
    @ApiModelProperty("是否已加入")
    private Boolean isJoin;

    @TableField(value = "is_quit")
    @ApiModelProperty("是否已退出")
    private Boolean isQuit;

    @TableField(value = "is_ban")
    @ApiModelProperty("是否被禁言")
    private Boolean isBan;

}
