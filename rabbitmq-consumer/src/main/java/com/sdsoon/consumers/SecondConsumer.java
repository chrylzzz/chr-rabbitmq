package com.sdsoon.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created By Chr on 2019/4/17/0017.
 */
@Component
@PropertySource("classpath:chr.properties")
@RabbitListener(queues = "${com.chr.secondQueue}", containerFactory = "simpleRabbitListenerContainerFactory")
public class SecondConsumer {

    @RabbitHandler
    public void process(@Payload Merchant merchant) {
        System.out.println("first queue" + merchant.xxx);
        //业务逻辑
        
    }
}
