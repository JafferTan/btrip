package com.jaffer.btrip.impl;

import com.jaffer.btrip.message.MessageService;
import com.jaffer.btrip.util.BtripResult;

import java.util.Map;

public class MessageServiceImpl implements MessageService {
    @Override
    public BtripResult<Boolean> sendMobileMessage(String telePhone, Map<String, Object> templateMap) {
        return null;
    }
}
