package com.example.alipaydemo.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.alipaydemo.annotations.AuthCheck;
import com.example.alipaydemo.common.PageResult;
import com.example.alipaydemo.common.Res;
import com.example.alipaydemo.controller.pojo.tUser;
import com.example.alipaydemo.controller.pojo.vo.UserConvert;
import com.example.alipaydemo.controller.pojo.vo.UserVo;
import com.example.alipaydemo.mapper.tUserMapper;
import com.example.alipaydemo.service.tUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class tUserServiceImpl extends ServiceImpl<tUserMapper, tUser> implements tUserService {

    @Resource
    private tUserMapper tUserMapper;

    @Override
    public tUser getById(String username) {
        LambdaQueryWrapper<tUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(tUser::getUsername,username);
        return tUserMapper.selectOne(wrapper);
    }

    @Override
    public IPage getPage(String username){
        IPage page = new Page<>(1,10);
        LambdaQueryWrapper<tUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(tUser::getUsername,username);
        page = page(page,wrapper);
        return page;
    }

    public static void main(String[] args) {
        tUser tUser = new tUser();
        tUser.setId(100);
        tUser.setUsername("xixi");
        tUser.setPassword("haha");

        UserVo userVo = UserConvert.INSTANCE.userToUserVO(tUser);

        System.out.println(userVo);

    }


    /**
     *  返回JSON数组
     * @return JSON数据数组
     * */
    @Override
    @AuthCheck(authId = "获取用户列表")
    public Object listData(){

        List<Map<String , Object>> mapList = new ArrayList<>();

        Map<String , Object> dataMapOne = new HashMap<>();
        dataMapOne.put("name","张三");
        dataMapOne.put("age",25);
        dataMapOne.put("sex",0);

        Map<String , Object> dataMapTwo = new HashMap<>();
        dataMapTwo.put("name","李四");
        dataMapTwo.put("age",23);
        dataMapTwo.put("sex",1);

        mapList.add(dataMapOne);
        mapList.add(dataMapTwo);

        return JSON.toJSON(mapList);
    }




}
