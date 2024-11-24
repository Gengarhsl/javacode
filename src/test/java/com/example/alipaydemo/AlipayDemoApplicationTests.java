package com.example.alipaydemo;

import com.example.alipaydemo.service.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class AlipayDemoApplicationTests {

    @Test
    void contextLoads() {
    }


    @Resource
    private NettyServer nettyServer;
    @Test
    public void test(){
        nettyServer.start();
        log.info("启动成功");
    }

}
