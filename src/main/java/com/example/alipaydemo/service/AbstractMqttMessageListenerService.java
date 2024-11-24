package com.example.alipaydemo.service;

import com.example.alipaydemo.processor.MqttTopicAnnotationProcessor;

import javax.annotation.PostConstruct;

public abstract class AbstractMqttMessageListenerService {

    protected MqttTopicAnnotationProcessor mqttTopicAnnotationProcessor;

    public AbstractMqttMessageListenerService(MqttTopicAnnotationProcessor mqttTopicAnnotationProcessor) {
        this.mqttTopicAnnotationProcessor = mqttTopicAnnotationProcessor;
    }

    @PostConstruct
    public void initialize() {
        mqttTopicAnnotationProcessor.processAnnotations(this);
    }
}
