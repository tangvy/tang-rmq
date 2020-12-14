package com.tangv.rmq.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * author:   tangwei
 * Date:     2020/12/14 14:10
 * Description: 发送过滤消息
 */
public class FilterProducer {

    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("tangv_producer");
        producer.setNamesrvAddr("192.168.247.128:9876");
        try {
            producer.start();
            for (int i=0;i<10;i++) {
                Message msg = new Message("tangv_topic","filter_msg_tag",("发送过滤MQ消息："+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                msg.putUserProperty("condition",String.valueOf(i));
                SendResult send = producer.send(msg);
                System.out.printf("%s%n",send);
            }
            producer.shutdown();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }
    }

}