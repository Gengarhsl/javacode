package com.example.controller;

import com.example.entity.tUser;
import com.example.service.tUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("tUser")
public class tUserController {

    @Autowired
    private tUserService tuserService;

    @GetMapping("get")
    public tUser get( String username){
        return tuserService.get(username);
    }


    @GetMapping("/book")
    public String book(){
        return "book";
    }
}
