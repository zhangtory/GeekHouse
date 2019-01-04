package com.zhangtory.geekhouse.Mqtt;

import com.zhangtory.geekhouse.Utils.CMD;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MqttServer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private MqttClient mqttClient;

    public MqttServer(@Value("${mqtt.server}") String server,
                      @Value("${mqtt.clientid}") String clientid,
                      @Value("${mqtt.username}") String username,
                      @Value("${mqtt.password}") String password) throws MqttException {
        logger.info("start new MqttServer:"+server+" "+clientid);
        this.mqttClient = new MqttClient(server, clientid);
        this.mqttClient.setCallback(new MsgCallBack());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        this.mqttClient.connect(options);

        subscribe(CMD.LIGHT_COMMAND_TOPIC);
    }

    public void reconnect() throws MqttException {
        logger.warn("mqtt server reconnect...");
        this.mqttClient.reconnect();
    }

    public void subscribe(String topic) throws MqttException {
        logger.info("subscribe topic: [{}]", topic);
        this.mqttClient.subscribe(topic);
    }

    public void publish(String topic, byte[] payload) throws MqttException {
        logger.info("public topic[{}], and payload:[{}]", topic, payload);
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(0);
        mqttMessage.setRetained(false);
        mqttMessage.setPayload(payload);
        MqttTopic mqttTopic = this.mqttClient.getTopic(topic);
        MqttDeliveryToken mqttDeliveryToken = mqttTopic.publish(mqttMessage);
        mqttDeliveryToken.waitForCompletion();
    }

}
