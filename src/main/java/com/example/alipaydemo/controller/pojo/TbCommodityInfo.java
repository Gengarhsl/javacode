package com.example.alipaydemo.controller.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author hsl
 * @version 1.0
 * @date 2024-08-16 16:35
 */

@Data
@TableName("tb_commodity_info")
public class TbCommodityInfo {

    @TableField("id")
    private String id;

    @TableField("commodity_name")
    private String commodityName;

    @TableField("commodity_price")
    private String commodityPrice;

    @TableField("number")
    private Integer number;

    @TableField("description")
    private String description;
}
