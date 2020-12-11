package com.tangv.rmq.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * author:   tangwei
 * Date:     2020/12/11 15:27
 * Description: 异步消息生产者
 */
public class AsyncProducer {

    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("tangv_producer");
        producer.setNamesrvAddr("192.168.247.128:9876");
        try {
            producer.start();
            producer.setRetryTimesWhenSendAsyncFailed(0);
            int msgCount = 10;
            final CountDownLatch2 countDownLatch2 = new CountDownLatch2(msgCount);
            for (int i=0;i<msgCount;i++) {
                final int index = i;
                Message msg = new Message("async_topic","tagA","orderID"+100*i*i,"HelloWorld".getBytes(RemotingHelper.DEFAULT_CHARSET));
                producer.send(msg, new SendCallback() {
                    public void onSuccess(SendResult sendResult) {
                        System.out.printf("%-10d OK %s %n", index,
                                sendResult.getMsgId());
                    }

                    public void onException(Throwable throwable) {
                        System.out.printf("%-10d Exception %s %n", index, throwable);
                        throwable.printStackTrace();
                    }
                });
            }
            countDownLatch2.await(5, TimeUnit.SECONDS);
            producer.shutdown();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}