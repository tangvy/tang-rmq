package com.tangv.rmq.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * author:   tangwei
 * Date:     2020/12/14 10:51
 * Description: 发送延时消息
 */
public class ScheduledMessageProducer {

    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("tangv_producer");
        producer.setNamesrvAddr("192.168.247.128:9876");
        try {
            producer.start();
            int totalMessagesToSend = 10;
            for (int i = 0;i < 10;i++) {
                Message msg = new Message("tangv_topic","scheduled_tag",("HELLO 延时消息："+i).getBytes());
                msg.setDelayTimeLevel(3);
                producer.send(msg);
            }
            producer.shutdown();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }

    }

}