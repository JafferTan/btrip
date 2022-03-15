package com.jaffer.btrip.service.impl;

import com.jaffer.btrip.beans.entity.CorpPO;
import com.jaffer.btrip.manager.CorpManager;
import com.jaffer.btrip.service.CorpService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import com.jaffer.btrip.util.RedisLockUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
@Slf4j
public class CorpServiceImpl implements CorpService {

    private static final String LOCK_KEY_REGISTER_CORP = "LOCK_KEY_REGISTER_CORP_%s";

    private static final String LOCK_KEY_MAINTAIN_CORP = "LOCK_KEY_MAINTAIN_CORP_%s";

    @Resource
    private CorpManager corpManager;

    @Override
    public BtripResult<String> registerCorp(String corpName, String phoneNumber, String userName) {
        String lockKey = String.format(LOCK_KEY_REGISTER_CORP, corpName);
        try {
            boolean lock = RedisLockUtils.tryLock(lockKey);
            if (BooleanUtils.isFalse(lock)) {
                return BtripResultUtils.returnFailMsg("重复注册，请稍后重试");
            }

            String corpId = corpManager.registerCorp(corpName, phoneNumber, userName);
            return BtripResultUtils.returnSuccess(corpId);
        } catch (Exception e) {
            log.error("registerCorp error, corpName:{}, phoneNumber:{}, userName:{}", corpName, phoneNumber, userName,e);
            return BtripResultUtils.returnFailMsg("创建企业失败,失败原因 :" + e.getMessage());
        } finally {
            RedisLockUtils.releaseLock(lockKey);
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
        String lockKey = String.format(LOCK_KEY_MAINTAIN_CORP, corpId);
        try {
            boolean lock = RedisLockUtils.tryLock(lockKey);
            if (BooleanUtils.isFalse(lock)) {
                return BtripResultUtils.returnFailMsg("多个用户正在维护企业信息，请稍后重试");
            }

            Boolean deleteRes = corpManager.deleteCorpByCorpId(corpId);

            if (BooleanUtils.isFalse(deleteRes)) {
                return BtripResultUtils.returnFailMsg("删除企业失败");
            }

            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            log.error("deleteCorpByCorpId error, corpId:{}", corpId,e);
            return BtripResultUtils.returnFailMsg("删除企业失败,失败原因 :" + e.getMessage());
        } finally {
            RedisLockUtils.releaseLock(lockKey);
        }
    }
}
