package com.jaffer.btrip.service.impl;

import com.jaffer.btrip.beans.entity.CorpPO;
import com.jaffer.btrip.manager.CorpManager;
import com.jaffer.btrip.service.CorpService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
@Slf4j
public class CorpServiceImpl implements CorpService {
    @Resource
    private CorpManager corpManager;

    @Override
    public BtripResult<Boolean> registerCorp(String corpName, String phoneNumber, String userName) {
        try {
            Boolean registerCorp = corpManager.registerCorp(corpName, phoneNumber, userName);
            if (BooleanUtils.isFalse(registerCorp)) {
                return BtripResultUtils.returnFailMsg("创建企业失败");
            }
            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            log.error("registerCorp error, corpName:{}, phoneNumber:{}, userName:{}", corpName, phoneNumber, userName,e);
            return BtripResultUtils.returnFailMsg("创建企业失败,失败原因 :" + e.getMessage());
        }
    }

    @Override
    public BtripResult<CorpPO> getCorpDetailByCorpId(String corpId) {
        try {
            CorpPO corp = corpManager.getCorpDetailByCorpId(corpId);
            if (Objects.isNull(corp)) {
                return BtripResultUtils.returnFailMsg("查询企业失败");
            }

            return BtripResultUtils.returnSuccess(corp);
        } catch (Exception e) {
            log.error("getCorpDetailByCorpId error, corpId:{}", corpId,e);
            return BtripResultUtils.returnFailMsg("查询企业失败,失败原因 :" + e.getMessage());
        }
    }

    @Override
    public BtripResult<Boolean> deleteCorpByCorpId(String corpId) {
        try {

            Boolean deleteRes = corpManager.deleteCorpByCorpId(corpId);

            if (BooleanUtils.isFalse(deleteRes)) {
                return BtripResultUtils.returnFailMsg("删除企业失败");
            }

            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            log.error("deleteCorpByCorpId error, corpId:{}", corpId,e);
            return BtripResultUtils.returnFailMsg("删除企业失败,失败原因 :" + e.getMessage());
        }
    }
}
