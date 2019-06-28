package com.sdsoon.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created By Chr on 2019/4/17/0017.
 */
@Component
public class RabbitSender {

    @Autowired
    private DirectExchange directExchange;
    @Autowired
    private TopicExchange topicExchange;

    @Value("${com.sdsoon.teacher}")
    private String topicRoutingKey1;

    @Value("com.sdsoon.student")
    private String topicRoutingKey2;

    //自定义的模板，所有的消息都会转换为JSON发送
    @Autowired
    AmqpTemplate amqpTemplate;


    public void send() {
        amqpTemplate.convertAndSend(directExchange, directRoutingKey, merchant);
    }
}
