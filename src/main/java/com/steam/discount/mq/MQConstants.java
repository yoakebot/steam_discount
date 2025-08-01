package com.steam.discount.mq;

public class MQConstants {


    // 交换机名称
    public static final String EXCHANGE_DELAY = "exchange.delay";
    public static final String EXCHANGE_DEAD_LETTER = "exchange.dead";
    public static final String EXCHANGE_TEST = "exchange.test";

    // 队列名称
    public static final String QUEUE_DELAY = "queue.delay";
    public static final String QUEUE_DEAD_LETTER = "queue.dead";
    public static final String QUEUE_TEST = "queue.test";

    // Routing Key
    public static final String ROUTING_KEY_DELAY = "routing.delay";
    public static final String ROUTING_KEY_DEAD_LETTER = "routing.dead";
    public static final String ROUTING_TEST = "routing.test";
}
