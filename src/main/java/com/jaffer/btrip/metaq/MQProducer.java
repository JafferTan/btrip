package com.jaffer.btrip.metaq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MQProducer implements BtripProducer{

    @Value("${spring.application.name}")
    private String producerGroup;

    @Value("${rocketmq.name-server}")
    private String nameSrvAddr;

    private DefaultMQProducer producer = null;


    /**
     * 消息队列的生产者的构造函数
     */
    @Override
    @PostConstruct
    public void init() {
        try {
            producer = new DefaultMQProducer(producerGroup);
            producer.setNamesrvAddr(nameSrvAddr);
            producer.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 消息队列的消费者的析构函数
     */
    @Override
    @PreDestroy
    public void destroy(){
        try {
            if (Objects.nonNull(producer)){
                producer.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SendResult produceMsg(String topic, String tag, Object messageBody, Map<String, String> properties) {
        String payloadJson = JSON.toJSONString(messageBody);
        String uniqueId = UUID.randomUUID().toString().replace("-","");
        SendResult sendResult = null;
        try {
            byte[] messageBodyByteArr = payloadJson.getBytes(StandardCharsets.UTF_8);
            Message msg = new Message(topic, tag, uniqueId, messageBodyByteArr);

            sendResult = producer.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult;
    }
}
