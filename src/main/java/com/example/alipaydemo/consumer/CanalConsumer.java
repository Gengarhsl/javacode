package com.example.alipaydemo.consumer;


import com.example.alipaydemo.service.RedisSync;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Log4j2
@Component
public class CanalConsumer {
    //日志记录
    private static Logger logger = LoggerFactory.getLogger(CanalConsumer.class);
    //redis操作工具类
    @Resource
    private RedisSync redisSync;

    //监听的队列名称为：canaltopic
    @KafkaListener(topics = "canal-topic")
    public void receive(ConsumerRecord<?, ?> consumer) {
        String value = (String) consumer.value();
        log.info("topic名称:{},key:{},分区位置:{},下标:{},value:{}", consumer.topic(), consumer.key(),consumer.partition(), consumer.offset(), value);
        redisSync.dataSync(value);
        log.info("数据同步到redis成功");
    }
}