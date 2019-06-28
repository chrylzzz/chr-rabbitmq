package com.sdsoon.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * Created By Chr on 2019/4/17/0017.
 */

@Configuration
@PropertySource("classpath:chr.properties")
public class RabbitConfig {

    //queue
    @Value("${CHR_FIRST_QUEUE}")
    private String firstQueue;

    @Value("${CHR_SECOND_QUEUE}")
    private String secondQueue;

    @Value("${CHR_THIRD_QUEUE}")
    private String thirdQueue;

    @Value("${CHR_FOURTH_QUEUE}")
    private String fourthQueue;

    //exchange
    @Value("${CHR_DIRECT_EXCHANGE}")
    private String directExchange;
    @Value("${CHR_TOPIC_EXCHANGE}")
    private String topicExchange;
    @Value("${CHR_FANOUT_EXCHANGE}")
    private String fanoutExchange;

    //创建四个队列
    @Bean(name = "firstQueue")
    public Queue getFirstQueue() {
        return new Queue(firstQueue);
    }

    @Bean(name = "secondQueue")
    public Queue getSecondQueue() {
        return new Queue(secondQueue);
    }

    @Bean(name = "thirdQueue")
    public Queue getThirdQueue() {
        return new Queue(thirdQueue);
    }

    @Bean(name = "fourthQueue")
    public Queue getFourthQueue() {
        return new Queue(fourthQueue);
    }


    //创建三个交换机
    @Bean(name = "directExchange")
    public DirectExchange getDirectExchange() {
        return new DirectExchange(directExchange);
    }

    @Bean(name = "topicExchange")
    public TopicExchange getTopicExchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean(name = "fanoutExchange")
    public FanoutExchange getFanoutExchange() {
        return new FanoutExchange(fanoutExchange);
    }

    //定义四个绑定关系
    @Bean
    public Binding bindFirst(@Qualifier("firstQueue") Queue queue,//
                             @Qualifier("directExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("chr.best");
    }

    @Bean
    public Binding bindSecond(@Qualifier("secondQueue") Queue queue,//
                              @Qualifier("topicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("*.chr.*");
    }

    @Bean
    public Binding bindThird(@Qualifier("thirdQueue") Queue queue,//
                             @Qualifier("fanoutExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    /**
     * 消费端转换JSON消息
     * 教廷类加上containerFactory属性
     */
//    @Bean
//    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory){
//
//    }

}
