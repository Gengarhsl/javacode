package com.example.alipaydemo.controller;

import com.example.alipaydemo.common.Res;
import com.example.alipaydemo.service.SysTestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hsl
 * @version 1.0
 * @date 2024-09-02 11:48
 */


@RestController
@RequestMapping("/sysTest")
public class SysTestController {

    @Resource
    private SysTestService sysTestService;


    /**
     * 测试多租户
     */

    @GetMapping("/get")
    public Res get(){
        return Res.success(sysTestService.getById("1111"));
    }
}
