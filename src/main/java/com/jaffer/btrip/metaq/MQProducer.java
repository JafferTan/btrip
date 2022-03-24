package com.jaffer.btrip.metaq;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class MQProducer {
    public static String add = "101.33.236.202:9876";

    public static void main(String[] args) {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("btrip");
        defaultMQProducer.setNamesrvAddr(add);

        try {
            defaultMQProducer.start();
            for (int i = 0; i < 10; i++) {
                String text = "hello world " + i ;
                Message msg = new Message("topicForTest", "tag-a", text.getBytes());
                SendResult send = defaultMQProducer.send(msg);
                System.out.println("发送结果："+ JSON.toJSONString(send));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            defaultMQProducer.shutdown();
        }
    }
}
