package com.example.alipaydemo.controller.pojo.vo;

import lombok.Data;

/**
 * @author hsl
 * @version 1.0
 * @date 2024-08-02 16:40
 */

@Data
public class UserVo {

    private Integer id;

    //姓名
    private String username;
    //密码
    private String passWord;
}
