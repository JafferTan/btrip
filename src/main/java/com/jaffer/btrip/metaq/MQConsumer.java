package com.jaffer.btrip.metaq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Component
public class MQConsumer implements BtripConsumer {

    @Value("${spring.application.name}")
    private String producerGroup;

    @Value("${rocketmq.name-server}")
    private String nameSrvAddr;

    private DefaultMQPushConsumer consumer = null;


    @Override
    @PostConstruct
    public void init() {
        try {
            consumer = new DefaultMQPushConsumer(producerGroup);
            consumer.setNamesrvAddr(nameSrvAddr);
            consumer.subscribe("topicForTest", "*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    try {
                        MessageExt messageExt = list.get(0);
                        return processMsg(messageExt);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ConsumeConcurrentlyStatus processMsg(MessageExt msg) {
        try {
            String bodyString = new String(msg.getBody(), StandardCharsets.UTF_8);
            System.out.println(bodyString);
        } catch (Exception e) {
          e.printStackTrace();
          return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @Override
    @PreDestroy
    public void destroy() {
        if (Objects.nonNull(consumer)) {
            consumer.shutdown();
        }
    }
}
