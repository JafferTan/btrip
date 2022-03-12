package com.jaffer.btrip.service.impl;

import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.manager.CorpAdminManager;
import com.jaffer.btrip.service.CorpAdminService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 企业的管理员的变更、查询类
 */
@Service
public class CorpAdminServiceImpl implements CorpAdminService {

    @Autowired
    private CorpAdminManager corpAdminManager;

    @Override
    public BtripResult<UserPO> getCorpSuperAdminInfo(String corpId) {
        try {
            UserPO corpSuperAdmin = corpAdminManager.getCorpSuperAdmin(corpId);
            if (Objects.isNull(corpSuperAdmin)) {
                return BtripResultUtils.returnFailMsg("企业超级管理员不存在");
            }

            return BtripResultUtils.returnSuccess(corpSuperAdmin);
        } catch (Exception e) {
            return BtripResultUtils.returnFailMsg("查询企业管理员失败，失败原因：{}" + e.getMessage());
        }
    }
}
