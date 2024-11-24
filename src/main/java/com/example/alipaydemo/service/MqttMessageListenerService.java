package com.example.alipaydemo.service;

import com.alibaba.fastjson.JSON;
import com.example.alipaydemo.annotations.MqttTopicListener;
import com.example.alipaydemo.processor.MqttTopicAnnotationProcessor;
import com.example.alipaydemo.utils.MQTTClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

/**
 * @author hsl
 */
@Slf4j
@Component
public class MqttMessageListenerService extends AbstractMqttMessageListenerService {

    public MqttMessageListenerService(MqttTopicAnnotationProcessor mqttTopicAnnotationProcessor) {
        super(mqttTopicAnnotationProcessor);
    }

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;
	
	/**
     * 测试
     *
     * @param topic
     * @param message
     */
    @MqttTopicListener(value = "test", qos = 2)
    public void testMessage(String topic, MqttMessage message) {
        String messageBody= new String(message.getPayload(), StandardCharsets.UTF_8);
        log.info("收到{}主题消息:messageBody:{}", topic, messageBody);
        String data = topic + ":"  + messageBody;
        try {
            kafkaTemplate.send("mqtt-topic", data);
        } catch (Exception e) {
            log.error("发送消息到 Kafka 失败", e);
        }

        log.info("发送消息到 Kafka, topic: mqtt-topic, data: {}", data);

//        try {
//            kafkaTemplate.send("mqtt-topic",data).get();//等待发送结果
//            log.info("消息成功发送到Kafka, topic: mqtt-topic");
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt(); // 保持中断状态
//            log.error("发送消息时被中断: {}", e.getMessage());
//        } catch (ExecutionException e) {
//            log.error("发送消息时发生错误: {}", e.getCause().getMessage());
//            // 进一步的处理逻辑，例如重试或记录详细信息
//        } catch (KafkaException e) {
//            log.error("Kafka异常: {}", e.getMessage());
//            // 根据需要进行处理
//        } catch (Exception e) {
//            log.error("未知异常: {}", e.getMessage());
//        }
    }



}


