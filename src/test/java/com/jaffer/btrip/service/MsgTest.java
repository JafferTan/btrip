package com.jaffer.btrip.service;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.AbsTest;
import com.jaffer.btrip.util.BtripResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AbsTest.class)
public class MsgTest extends AbsTest{

    @Autowired
    private MessageService messageService;

    @Test
    public void testSendSms() {

        String templateCode = "SMS_154950909";

        String phoneNumber= "18857888804";

        Map<String, Object> map = new HashMap<>();
        map.put("code","1234");

        BtripResult<Boolean> result = messageService.sendMobileMessage(phoneNumber, map, templateCode);
        System.out.println(JSON.toJSONString(result));

    }

}
