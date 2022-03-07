package com.jaffer.btrip.message;

import com.jaffer.btrip.util.BtripResult;

import java.util.Map;

/**
 * 消息服务接口
 */
public interface MessageService {
    BtripResult<Boolean> sendMobileMessage(String telePhone, Map<String,Object> templateMap);
}
