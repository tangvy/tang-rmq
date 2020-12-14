package com.tangv.rmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * author:   tangwei
 * Date:     2020/12/11 19:43
 * Description: 顺序消费
 * @author 2381
 */
public class OrderlyConsumer {

    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("tangv_consumer");
        consumer.setNamesrvAddr("192.168.247.128:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        try {
            consumer.subscribe("order_topic","tagI || tagII || tagIII");
            consumer.registerMessageListener(new MessageListenerOrderly() {
                Random random = new Random();
                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                    consumeOrderlyContext.setAutoCommit(Boolean.TRUE);
                    for (MessageExt msg : list) {
                        System.out.println("消费线程："+Thread.currentThread().getName()+"，queueId = "+msg.getQueueId()+"消息内容：" + new String(msg.getBody()));
                    }

                    try {
                        //模拟业务逻辑处理中......
                        TimeUnit.SECONDS.sleep(random.nextInt(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return ConsumeOrderlyStatus.SUCCESS;
                }
            });
            consumer.start();
            System.out.println("Consumer started ......");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

}