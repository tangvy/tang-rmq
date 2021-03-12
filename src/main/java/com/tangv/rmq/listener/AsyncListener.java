package com.tangv.rmq.listener;

import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * author:   tangwei
 * Date:     2021/3/11 16:25
 * Description:
 */
@Service
@RocketMQMessageListener(topic = "async_topic", consumerGroup = "tangv_consumer", nameServer = "192.168.247.128:9876")
public class AsyncListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String o) {
        System.out.println(o.toString());
    }
}