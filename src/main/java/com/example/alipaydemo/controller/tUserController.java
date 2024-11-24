package com.example.alipaydemo.controller;

import com.example.alipaydemo.common.Res;
import com.example.alipaydemo.service.tUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hsl
 * @version 1.0
 * @date 2024-08-20 01:15
 */


@RestController
@RequestMapping("/tUser")
public class tUserController {

    @Resource
    private tUserService tUserService;

    @GetMapping("/list")
    public Object list(){
        return tUserService.listData();
    }



    @GetMapping("/page")
    public Res page(@RequestParam String username){
        return Res.success(tUserService.getPage(username));
    }
}
