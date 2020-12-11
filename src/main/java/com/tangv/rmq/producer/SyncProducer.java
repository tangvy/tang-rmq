package com.tangv.rmq.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * author:   tangwei
 * Date:     2020/12/11 15:07
 * Description: 同步消息生产者
 */

public class SyncProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("tangv_producer");
        producer.setNamesrvAddr("192.168.247.128:9876");
        try {
            producer.start();
            for (int i = 0;i<10;i++) {
                Message msg = new Message("tangv_topic","tagB",("想你的第"+i+"天" ).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult send = producer.send(msg);
                System.out.printf("%s%n",send);
            }
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}