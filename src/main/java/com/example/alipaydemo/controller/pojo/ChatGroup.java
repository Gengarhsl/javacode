package com.example.alipaydemo.controller.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author hsl
 * @version 1.0
 * @date 2024-09-14 17:07
 */


@Data
@Accessors(chain = true)
@TableName(value = "chat_group")
@ApiModel("群聊表")
public class ChatGroup {

    @ApiModelProperty("群聊id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("群聊名称")
    @TableField(value = "group_name")
    private String GroupName;

    @ApiModelProperty("创建人")
    @TableField(value = "creator",fill = FieldFill.INSERT)
    private String creator;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改人")
    @TableField(value = "updater",fill = FieldFill.INSERT_UPDATE)
    private String updater;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


    @ApiModelProperty("群聊描述")
    @TableField(value = "group_desc")
    private String groupDesc;

    @ApiModelProperty("群聊人数")
    @TableField(value = "group_number")
    private Integer groupNumber;

    @ApiModelProperty("群聊头像")
    @TableField(value = "group_avatar")
    private String groupAvatar;

    @ApiModelProperty("群聊公告")
    @TableField(value = "group_notice")
    private String groupNotice;

    @ApiModelProperty("删除标记 0_未删除 1_已删除")
    @TableLogic
    @TableField(value = "del_flag")
    private Integer delFlag;

}
