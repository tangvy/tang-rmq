package com.tangv.rmq.config;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author:   tangwei
 * Date:     2020/12/11 17:35
 * Description:
 */
@Configuration
public class RocketmqConfig {

    @Bean
    public DefaultMQProducer defaultMQProducer() {
        DefaultMQProducer producer = new DefaultMQProducer("tangv_producer");
        producer.setNamesrvAddr("192.168.247.128:9876");
        return producer;
    }

    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("tangv_consumer");
        consumer.setNamesrvAddr("192.168.247.128:9876");
        return consumer;
    }

}