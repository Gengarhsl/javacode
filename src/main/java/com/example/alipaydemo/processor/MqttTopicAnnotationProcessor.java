package com.example.alipaydemo.processor;

import com.example.alipaydemo.annotations.MqttTopicListener;
import com.example.alipaydemo.utils.MQTTClientUtils;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * method.invoke(objectWithAnnotations, mqttTopic, message);
 * 上面这种方式绕过了 Spring 的 AOP 代理，也就是说，这个调用并不会触发 Spring AOP 的切面逻辑。
 * 也就是说直接使用 objectWithAnnotations 不会经过 Spring 容器，导致 AOP 切面无法拦截和处理这个调用。
 * 改用手动代理方式
 * Object targetObject = applicationContext.getBean(objectWithAnnotations.getClass());
 * method.invoke(targetObject, mqttTopic, message);
 * 或者采用消息适配器
 */
@Component
public class MqttTopicAnnotationProcessor {

    private final MQTTClientUtils mqttClientUtils;

    private final ApplicationContext applicationContext;

    public MqttTopicAnnotationProcessor(MQTTClientUtils mqttClientUtils,ApplicationContext applicationContext) {
        this.mqttClientUtils = mqttClientUtils;
        this.applicationContext = applicationContext;
    }

    public void processAnnotations(Object objectWithAnnotations) {
        Class<?> clazz = objectWithAnnotations.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(MqttTopicListener.class)) {
                MqttTopicListener annotation = method.getAnnotation(MqttTopicListener.class);
                String topic = annotation.value();
                int qos = annotation.qos();
                IMqttMessageListener listener = new IMqttMessageListener() {
                    @Override
                    public void messageArrived(String mqttTopic, MqttMessage message) throws Exception {
                        Object targetObject = applicationContext.getBean(objectWithAnnotations.getClass());
                        method.invoke(targetObject, mqttTopic, message);
//                        method.invoke(objectWithAnnotations, mqttTopic, message);如果采用这种方式targetObject 没有被代理
                    }
                };
                mqttClientUtils.subscribe(topic, qos, listener);
            }
        }
    }
}
