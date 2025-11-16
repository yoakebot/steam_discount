package com.steam.discount.mq;

import com.steam.discount.model.Boy;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {

//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = MQConstants.QUEUE_TEST, durable = "true"),
//            exchange = @Exchange(
//                    value = MQConstants.EXCHANGE_TEST
//            ),
//            key = MQConstants.ROUTING_TEST),
//            ackMode = "AUTO"
//    )
    public void receiveTest(@Payload Boy boy, @Header(AmqpHeaders.CONSUMER_QUEUE) String queue) {
        System.out.println("【收到延迟消息】ID：" + boy.time() + "，来源队列=" + queue);
    }

//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = MQConstants.QUEUE_DEAD_LETTER, durable = "true"),
//            exchange = @Exchange(value = MQConstants.EXCHANGE_DEAD_LETTER, type = ExchangeTypes.DIRECT, durable = "true"),
//            key = MQConstants.ROUTING_KEY_DEAD_LETTER),
//            ackMode = "AUTO"
//    )
    public void receiveDead(@Payload Boy boy, @Header(AmqpHeaders.CONSUMER_QUEUE) String queue) {
        System.out.println("【收到延迟消息】ID：" + boy.time() + "，来源队列=" + queue);
    }
}
