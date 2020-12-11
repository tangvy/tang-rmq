package com.tangv.rmq.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * author:   tangwei
 * Date:     2020/12/11 16:16
 * Description: 单向发送消息生产者
 */
public class OneWayProducer {

    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("tangv_producer");
        producer.setNamesrvAddr("192.168.247.128:9876");
        try {
            producer.start();
            for (int i=0;i<10;i++) {
                Message msg = new Message("tangv_topic","tagB",("第"+i+1+"次欢迎你！").getBytes(RemotingHelper.DEFAULT_CHARSET));
                producer.sendOneway(msg);
            }
            producer.shutdown();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        }
    }

}