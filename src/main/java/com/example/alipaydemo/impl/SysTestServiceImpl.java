package com.example.alipaydemo.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.alipaydemo.entity.SysTest;
import com.example.alipaydemo.mapper.SysTestMapper;
import com.example.alipaydemo.service.SysTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author hsl
 * @version 1.0
 * @date 2024-09-02 11:47
 */

@Service
@Slf4j
public class SysTestServiceImpl extends ServiceImpl<SysTestMapper, SysTest> implements SysTestService {
}
