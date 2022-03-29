package com.jaffer.btrip.metaq;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;


public interface BtripConsumer {


    /**
     * 构造函数
     */
    void init();

    /**
     * 消费消息
     * @param msg 消息体
     * @return 发送消息状态
     */
    ConsumeConcurrentlyStatus processMsg (MessageExt msg);


    /**
     * producer的析构函数
     */
    void destroy();
}
