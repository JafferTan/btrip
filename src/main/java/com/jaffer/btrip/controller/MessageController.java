package com.jaffer.btrip.controller;

import com.google.common.collect.Maps;
import com.jaffer.btrip.metaq.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessageController {

    @Autowired
    private MQProducer mqProducer;

    @RequestMapping(value = "/mq")
    public String hello(){
        return "mq";
    }

    @PostMapping("/sendMsg")
    public String sendMsg(String msgBody) {
        SendResult sendResult = mqProducer.produceMsg("topicForTest", "tagA", msgBody, Maps.newHashMap());
        return "mq";
    }
}
