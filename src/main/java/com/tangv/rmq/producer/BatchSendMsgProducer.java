package com.tangv.rmq.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.List;

/**
 * author:   tangwei
 * Date:     2020/12/14 13:40
 * Description: 批量发送消息
 * 如果您每次只发送不超过4MB的消息，则很容易使用批处理
 * 复杂度只有当你发送大批量时才会增长，你可能不确定它是否超过了大小限制（4MB）。这时候你最好把你的消息列表分割一下
 */
public class BatchSendMsgProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("tangv_producer");
        producer.setNamesrvAddr("192.168.247.128:9876");
        try {
            producer.start();
            List<Message> msgList = new ArrayList<>();
            msgList.add(new Message("batch_send_topic","tag_batch_send_msg","OrderId001","批量发送消息001".getBytes()));
            msgList.add(new Message("batch_send_topic","tag_batch_send_msg","OrderId002","批量发送消息002".getBytes()));
            msgList.add(new Message("batch_send_topic","tag_batch_send_msg","OrderId003","批量发送消息003".getBytes()));
            producer.send(msgList);
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