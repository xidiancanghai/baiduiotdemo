package com.example.baiduiotdemo.conf;

import com.example.baiduiotdemo.util.BdLog;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Configuration
public class RabbitMQInit {

    public static final String NORMAL_EXCHANGE = "exchange.normal";

    private static final String NORMAL_QUEUE = "queue.normal";

    private static final String DLX_EXCHANGE = "exchange.dlx";

    private static final String DLX_QUEUE = "queue.dlx";

    public static final String ROUTTING_KEY = "send_msg_to_iotplatform";

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 5672;

    private static final String USER_NAME = "root";

    private static final String PASS_WORD = "root";

    private static final int MAX_TTL = 24*3600000;

    @Bean
    public Channel initChannel() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USER_NAME);
        factory.setPassword(PASS_WORD);
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(NORMAL_EXCHANGE, "direct", true);
            channel.exchangeDeclare(DLX_EXCHANGE, "fanout", true);
            Map<String, Object> args = new HashMap<>();
            args.put("x-message-ttl", MAX_TTL);
            args.put("x-dead-letter-exchange", DLX_EXCHANGE);
            args.put("x-dead-letter-routing-key", ROUTTING_KEY);
            channel.queueDeclare(NORMAL_QUEUE, true, false, false, args);
            channel.queueBind(NORMAL_QUEUE, NORMAL_EXCHANGE, ROUTTING_KEY);
            channel.queueDeclare(DLX_QUEUE, true, false, false, null);
            channel.queueBind(DLX_QUEUE, DLX_EXCHANGE, ROUTTING_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            BdLog.error("error_msg" + e.getMessage());
        }
        return channel;
    }

}
