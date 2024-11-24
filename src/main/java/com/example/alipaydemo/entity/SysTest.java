package com.example.alipaydemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @author hsl
 * @version 1.0
 * @date 2024-09-02 11:39
 */

@Data
@ApiModel("sys_test")
@TableName("sys_test")
public class SysTest {

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    @ApiModelProperty("id")
    private String id;

    @TableField(value = "test")
    @ApiModelProperty("test")
    private String test;

    @TableField(value = "tenantId")
    @ApiModelProperty("tenantId")
    private String tenantId;
}
