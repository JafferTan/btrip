package com.jaffer.btrip.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.jaffer.btrip.service.MessageService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 发送短信服务
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    private static final String REGION_ID = "cn-hangzhou";

    private static final String ACCESS_KEY = "accessKeyId";

    private static final String ACCESS_KEY_SECRET = "accessKeySecret";

    private static final String SIGN_NAME = "阿里云短信测试";


    @Override
    public BtripResult<Boolean> sendMobileMessage(String phoneNumber, Map<String, Object> templateMap, String templateCode) {
        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, ACCESS_KEY, ACCESS_KEY_SECRET);

        IAcsClient client = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        //接收短信的手机号码
        request.setPhoneNumbers(phoneNumber);
        //短信签名名称
        request.setSignName(SIGN_NAME);
        //短信模板code
        request.setTemplateCode(templateCode);
        //短信模板变量对应的实际值
        request.setTemplateParam(JSON.toJSONString(templateMap));

        SendSmsResponse response = null;
        try {
            response = client.getAcsResponse(request);
            log.info(JSON.toJSONString(response));

            if (!StringUtils.equals(response.getCode(), "OK")) {
                return BtripResultUtils.returnFailMsg("发送短信失败, 失败原因:" + response.getMessage());
            }

            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            log.error("sendSms fail, phoneNumber:{}, ", phoneNumber, e);
            return BtripResultUtils.returnFailMsg("发送短信失败, 异常信息:" + e.getMessage());
        }
    }

}
