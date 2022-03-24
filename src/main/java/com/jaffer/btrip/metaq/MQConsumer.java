package com.jaffer.btrip.metaq;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class MQConsumer {
    public static String add = "101.33.236.202:9876";
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("alibaba");
        consumer.setNamesrvAddr(add);
        try{
            consumer.subscribe("topicForTest", "*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    try {
                        for (MessageExt messageExt : list) {
                            System.out.println(JSON.toJSONString(messageExt));
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            long l = System.currentTimeMillis();
            System.out.println(l);
            consumer.start();
            System.out.println("开始消费");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.shutdown();
        }
    }
}
