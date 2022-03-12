package com.jaffer.btrip.service.impl;

import com.jaffer.btrip.message.MessageService;
import com.jaffer.btrip.util.BtripResult;

import java.util.Map;

/**
 * 发送短信服务
 */
public class MessageServiceImpl implements MessageService {
    @Override
    public BtripResult<Boolean> sendMobileMessage(String phoneNumber, Map<String, Object> templateMap) {
        return null;
    }
}
