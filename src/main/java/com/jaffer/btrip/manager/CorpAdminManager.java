package com.jaffer.btrip.manager;

import com.jaffer.btrip.beans.entity.CorpAdminPO;
import com.jaffer.btrip.beans.entity.CorpAdminPOExample;
import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.enums.CorpAdminEnum;
import com.jaffer.btrip.enums.RowStatusEnum;
import com.jaffer.btrip.mapper.CorpAdminPOMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class CorpAdminManager {

    @Autowired
    private CorpAdminPOMapper corpAdminPOMapper;

    @Autowired
    private UserManager userManager;


    /**
     * 查询企业的超级管理员userId
     *
     * @param corpId
     * @return
     */
    public String getCorpSuperAdminUserId(String corpId) {
        CorpAdminPOExample corpAdminPOExample = new CorpAdminPOExample();
        corpAdminPOExample.createCriteria().
                andCorpIdEqualTo(corpId).andStatusEqualTo(RowStatusEnum.NORMAL.getStatus()).andAdminTypeEqualTo(CorpAdminEnum.SUPER_ADMIN.getAdminType());
        List<CorpAdminPO> corpAdminPOS = corpAdminPOMapper.selectByExample(corpAdminPOExample);
        if (CollectionUtils.isEmpty(corpAdminPOS)) {
            return null;
        }
        return corpAdminPOS.get(0).getUserId();
    }


    /**
     * 查询企业的超级管理员信息
     *
     * @param corpId
     * @return
     */
    public UserPO getCorpSuperAdmin(String corpId) {
        String userId = this.getCorpSuperAdminUserId(corpId);
        if (StringUtils.isEmpty(userId)) {
            return null;
        }

        UserPO userByUserId = userManager.getUserByUserId(corpId, userId);

        return userByUserId;

    }
}
