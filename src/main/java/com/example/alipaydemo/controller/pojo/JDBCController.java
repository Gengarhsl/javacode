package com.example.alipaydemo.controller.pojo;

import com.example.alipaydemo.service.MQTTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class JDBCController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/testconnection")
    public String testConnection() {
        try {
            jdbcTemplate.execute("SELECT 1"); // 执行一条简单的 SQL 查询以测试连接
            return "Connection to SQL Server is successful!";
        } catch (Exception e) {
            return "Failed to connect to SQL Server: " + e.getMessage();
        }
    }


    @Resource
    private MQTTService mqttService;

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;


    @GetMapping("haha")
    public String test(){
        mqttService.sendMessage("test","xixihaha");
        return "ok";
    }

    @GetMapping("xixi")
    public String testKafka(){
        kafkaTemplate.send("mqtt-topic","xixinihao");
        return "ok";
    }
}
