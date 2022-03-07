package com.jaffer.btrip.controller;

import com.jaffer.btrip.service.CorpService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class CorpController {

    @Autowired
    private CorpService corpService;

    @PostMapping("/registerCorpJson")
    @ResponseBody
    public BtripResult<Boolean> registerCorp(String corpName, String phoneNumber, String userName) {

        if (StringUtils.isEmpty(corpName) || StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(userName)) {
            return BtripResultUtils.returnFailMsg("非法入参");
        }

        try {
            BtripResult<Boolean> result = corpService.registerCorp(corpName, phoneNumber, userName);
            if (result == null || BooleanUtils.isFalse(result.getSuccess())) {
                return BtripResultUtils.returnFailMsg("注册企业失败，失败原因");
            }
            return result;
        } catch (Exception e) {
            log.error("registerCorp error, corpName:{}, phoneNumber:{}, userName:{}", corpName, phoneNumber, userName,e);
            return BtripResultUtils.returnFailMsg("注册企业异常,异常原因 :" + e.getMessage());
        }
    }
}
