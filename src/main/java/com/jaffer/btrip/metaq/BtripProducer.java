package com.jaffer.btrip.metaq;

import org.apache.rocketmq.client.producer.SendResult;

import java.util.Map;

/**
 * 抽象生产者类
 */
public interface BtripProducer {

    /**
     * 发送并发消息 - 同步发送
     * @param topic 消息主题
     * @param tag 消息tag
     * @param messageBody 消息内容体
     * @param properties properties
     * @return
     */
    SendResult produceMsg(String topic, String tag, Object messageBody, Map<String, String> properties);


    /**
     * producer的构造函数
     */
    void init();

    /**
     * producer的析构函数
     */
    void destroy();
}
