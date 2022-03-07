package com.jaffer.btrip.impl;

import com.jaffer.btrip.manager.CorpManager;
import com.jaffer.btrip.service.CorpService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class CorpServiceImpl implements CorpService {
    @Resource
    private CorpManager corpManager;

    @Override
    public BtripResult<Boolean> registerCorp(String corpName, String phoneNumber, String userName) {
        try {
            Boolean registerCorp = corpManager.registerCorp(corpName, phoneNumber, userName);
            if (BooleanUtils.isTrue(registerCorp)) {
                return BtripResultUtils.returnSuccess(true);
            } else {
                return BtripResultUtils.returnFailMsg("创建企业失败");
            }
        } catch (Exception e) {
            log.error("registerCorp error, corpName:{}, phoneNumber:{}, userName:{}", corpName, phoneNumber, userName,e);
            return BtripResultUtils.returnFailMsg("创建企业失败,失败原因 :" + e.getMessage());
        }
    }
}
