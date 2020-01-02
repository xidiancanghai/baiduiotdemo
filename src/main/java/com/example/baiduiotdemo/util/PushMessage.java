package com.example.baiduiotdemo.util;

import com.alibaba.fastjson.JSONObject;
import com.example.baiduiotdemo.conf.RabbitMQInit;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PushMessage {

    @Autowired
    Channel channel;

    public PushMessage() {
        this.channel = new RabbitMQInit().initChannel();
    }

    public boolean SendMessageTTL(String exchage, String order, String routeKey, int ttl) {
        JSONObject jsonObject = formatMessage(exchage, order);
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        builder.deliveryMode(2);
        builder.expiration(String.valueOf(ttl));
        AMQP.BasicProperties properties = builder.build();
        boolean res = true;
        try {
            channel.basicPublish(exchage, routeKey, properties,
                    jsonObject.toJSONString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            BdLog.error("error_msg=" + e.getMessage());
            res = false;
        }
        return  res;
    }

    private static JSONObject formatMessage(String exchage, String message) {
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("message", message);
        map.put("createTime", createTime);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId);
        jsonObject.put("message", message);
        jsonObject.put("create_time", createTime);
        return jsonObject;
    }

}
