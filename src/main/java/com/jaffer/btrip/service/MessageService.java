package com.jaffer.btrip.service;

import com.jaffer.btrip.util.BtripResult;

import java.util.Map;

/**
 * 消息服务接口
 */
public interface MessageService {
    /**
     * 发送短信服务
     * @param phoneNumber
     * @param templateMap
     * @param templateCode
     * @return
     */
    BtripResult<Boolean> sendMobileMessage(String phoneNumber, Map<String, Object> templateMap, String templateCode);
}
