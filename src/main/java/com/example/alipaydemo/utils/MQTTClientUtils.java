package com.example.alipaydemo.utils;

import com.example.alipaydemo.config.MQTTConfigBuilder;
import com.example.alipaydemo.consumer.MessageCallbackListener;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;

@Slf4j
@Configuration
public class MQTTClientUtils {

    @Autowired
    private MQTTConfigBuilder mqttConfig;

    private MqttClient mqttClient;

    public MQTTClientUtils createDevOpsMQTTClient() {
        this.createMQTTClient();
        return this;
    }

    private MQTTClientUtils connect() {
        try {
            this.mqttClient.connect(mqttConfig.getOptions());
            log.info("MQTTClient连接成功！");
        }catch (MqttException mqttException){
            mqttException.printStackTrace();
            log.error("MQTTClient连接失败！");
        }
        return this;
    }

    private MqttClient createMQTTClient() {
        try{
            this.mqttClient = new MqttClient( mqttConfig.getHost(), mqttConfig.getClientId());
            log.info("MQTTClient创建成功！");
            return this.mqttClient;
        }catch (MqttException exception){
            exception.printStackTrace();
            log.error("MQTTClient创建失败！");
            return null;
        }
    }

    /**
     * 消息发送
     * @param topicName
     * @param message
     * @return
     */
    public boolean publish(String topicName, String message) {
        log.info("订阅主题名:{}, message:{}", topicName, message);
        MqttMessage mqttMessage = new MqttMessage(message.getBytes(StandardCharsets.UTF_8));
        try {
            this.mqttClient.publish(topicName, mqttMessage);
            return true;
        }catch (MqttException exception){
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * 消息发送 : retained 默认为 false
     * "retained message" 指的是 Broker 会保留的最后一条发布到某个主题的消息。
     * 当新的订阅者连接到该主题时，Broker 会将这条保留消息立即发送给订阅者，即使在订阅者订阅时该消息并未被重新发布。
     * 这对于一些需要初始状态或者最后一次已知状态的应用场景非常有用。
     * @param topicName
     * @param message
     * @param qos
     * @return
     */

    /**
     *
     * QoS 0 - 最多一次（At most once）
     *
     * 消息会被尽力发送，但没有确认机制，也没有重发策略。这意味着消息可能会丢失，但不会重复传送。
     * 适用于对消息传递可靠性要求不高的场景。
     * 这种模式是最快的，因为它不需要额外的确认消息。
     * java
     * this.mqttClient.publish(topicName, mqttMessage.getPayload(), 0, false);
     * QoS 1 - 至少一次（At least once）
     *
     * 消息至少会被传送一次，但可能会重复。这意味着消息可能会被发送多次（重复），但绝不会丢失。
     * 适用于需要确保消息不会丢失但可以接受消息重复的场景。
     * 这个级别涉及到请求和确认机制，保证消息最终会到达，但可能会重复接收。
     * java
     * this.mqttClient.publish(topicName, mqttMessage.getPayload(), 1, false);
     * QoS 2 - 仅一次（Exactly once）
     *
     * 消息会被确保只送达一次，这意味着无论什么情况，消息不会丢失也不会重复。
     * 适用于需要确保消息传递的最高可靠性且避免重复的场景。
     * 这个级别涉及到四步握手过程来确保消息传递的唯一性和准确性，是最复杂也是最耗时的。
     *
     * 默认值  0
     */
    public boolean publish(String topicName, int qos, String message) {
        log.info("主题名:{}, qos:{}, message:{}", topicName, qos, message);
        MqttMessage mqttMessage = new MqttMessage(message.getBytes(StandardCharsets.UTF_8));
        try {
            this.mqttClient.publish(topicName, mqttMessage.getPayload(), qos, false);
            return true;
        }catch (MqttException exception){
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * 订阅某个主题
     *
     * @param topicName
     * @param qos
     */
    public void subscribe(String topicName, int qos) {
        log.info("订阅主题名:{}, qos:{}", topicName, qos);
        try {
            this.mqttClient.subscribe(topicName, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订阅某个主题
     *
     * @param topicName
     * @param qos
     */
    public void subscribe(String topicName, int qos, IMqttMessageListener messageListener) {
        log.info("订阅主题名:{}, qos:{}, Listener类:{}", topicName, qos, messageListener.getClass());
        try {
            this.mqttClient.subscribe(topicName, qos, messageListener);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消订阅主题
     * @param topicName 主题名称
     */
    public void cleanTopic(String topicName) {
        log.info("取消订阅主题名:{}", topicName);
        try {
            this.mqttClient.unsubscribe(topicName);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

//这里是初始化方法
    @PostConstruct
    public void initMqttClient(){
        //创建连接
        MQTTClientUtils mqttClientUtils = this.createDevOpsMQTTClient().connect();
        //这里主要是项目启动时订阅一些主题。看个人需要使用
        //mqttClientUtils.subscribe("test/#", 2, new HeartBeatListener());
        //MessageCallbackListener订阅主题，接受到该主题消息后交给MessageCallbackListener去处理
         mqttClientUtils.subscribe("message/call/back", 2, new MessageCallbackListener());
        //需要注意的是new MessageCallbackListener()虽然会接收到消息，但这么做不对。
        //举个简单列子：就是你有切面对MessageCallbackListener中重写的方法做一些其他操作，
        //那么接收到消息后该切面并不会生效，所以不建议这么做,以下是修改过后的。
      	//@Resource
    	//private MessageCallbackListener messageCallbackListener;
    	//上面两句放到外面好吧!!!评论的大哥想的有点多，还要写个上下文获取bean...
    	//mqttClientUtils.subscribe("message/call/back", 2, messageCallbackListener);
    }
}

