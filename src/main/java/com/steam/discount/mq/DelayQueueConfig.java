package com.steam.discount.mq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DelayQueueConfig {

    @Bean
    public Queue delayQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", MQConstants.EXCHANGE_DEAD_LETTER);
        args.put("x-dead-letter-routing-key", MQConstants.ROUTING_KEY_DEAD_LETTER);
        return new Queue(MQConstants.QUEUE_DELAY, true, false, false, args);
    }

    @Bean
    public Exchange delayExchange() {
        return ExchangeBuilder.directExchange(MQConstants.EXCHANGE_DELAY).durable(true).build();
    }

    @Bean
    public Binding delayBinding(@Qualifier("delayQueue") Queue delayQueue,
                                @Qualifier("delayExchange") Exchange delayExchange) {
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(MQConstants.ROUTING_KEY_DELAY).noargs();
    }
}
