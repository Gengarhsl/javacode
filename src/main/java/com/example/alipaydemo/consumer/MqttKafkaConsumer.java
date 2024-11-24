package com.example.alipaydemo.consumer;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author hsl
 * @version 1.0
 * @date 2024-08-19 18:07
 */

@Log4j2
@Component
public class MqttKafkaConsumer {

    @KafkaListener(topics = "mqtt-topic")
    public void mqttTest(ConsumerRecord<?, ?> consumer){
        log.info("topic名称:{},key:{},分区位置:{},下标:{},value:{}", consumer.topic(), consumer.key(),consumer.partition(), consumer.offset(), consumer.value());
    }
}
