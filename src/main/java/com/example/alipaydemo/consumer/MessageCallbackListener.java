package com.example.alipaydemo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author by Guoshun
 * @version 1.0.0
 * @description 消息回调返回
 * @date 2023/12/12 17:27
 */
@Component
@Slf4j
public class MessageCallbackListener implements IMqttMessageListener {

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String messageBody = new String(message.getPayload(), StandardCharsets.UTF_8);
        log.info("收到消息：{}, 消息内容是：{}",topic,messageBody);
    }
}

