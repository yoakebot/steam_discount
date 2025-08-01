package com.steam.discount.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MQSendService {

    private final AmqpTemplate amqpTemplate;

    public MQSendService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public <T> void send(String exchange, String routingKey, T msg) {
        amqpTemplate.convertAndSend(exchange, routingKey, msg);
    }

    public <T> void sendDelay(String exchange, String routingKey, T msg, String ttlMillis) {
        MessagePostProcessor processor = message -> {
            message.getMessageProperties().setExpiration(ttlMillis);
            return message;
        };
        amqpTemplate.convertAndSend(exchange, routingKey, msg, processor);
    }
}
