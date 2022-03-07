package com.jaffer.btrip.manager;

import com.jaffer.btrip.beans.entity.CorpAdminPO;
import com.jaffer.btrip.beans.entity.CorpPO;
import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.enums.BtripSpecialDeptEnum;
import com.jaffer.btrip.enums.CorpAdminEnum;
import com.jaffer.btrip.exception.BizException;
import com.jaffer.btrip.helper.CorpAdminServiceHelper;
import com.jaffer.btrip.helper.CorpServiceHelper;
import com.jaffer.btrip.helper.UserServiceHelper;
import com.jaffer.btrip.mapper.CorpAdminPOMapper;
import com.jaffer.btrip.mapper.CorpPOMapper;
import com.jaffer.btrip.mapper.UserPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class CorpManager {

    @Resource
    private CorpPOMapper corpPOMapper;

    @Resource
    private UserPOMapper userPOMapper;

    @Autowired
    private CorpServiceHelper corpServiceHelper;

    @Resource
    private UserServiceHelper userServiceHelper;

    @Resource
    private CorpAdminServiceHelper corpAdminServiceHelper;

    @Resource
    private CorpAdminPOMapper corpAdminPOMapper;

    /**
     * 注册企业
     * @param corpName
     * @param phoneNumber
     * @param userName
     * @return
     */
    @Transactional
    public Boolean registerCorp(String corpName, String phoneNumber, String userName){
        CorpPO corpPO = corpServiceHelper.buildCorpPO(corpName);
        int insertCorpRes = corpPOMapper.insert(corpPO);
        if (insertCorpRes < 1) {
            throw new BizException("注册企业失败");
        }

        UserPO userPO = userServiceHelper.buildUserPO(userName, phoneNumber, corpPO.getCorpId(), BtripSpecialDeptEnum.ROOT_DEPT.getDeptId());
        int insertUserRes = userPOMapper.insert(userPO);
        if (insertUserRes < 1) {
            throw new BizException("插入用户失败");
        }

        CorpAdminPO corpAdminPO = corpAdminServiceHelper.buildCorpAdminPO(corpPO.getCorpId(), userPO.getUserId(), CorpAdminEnum.SUPER_ADMIN);
        int insertSuperAdmin = corpAdminPOMapper.insert(corpAdminPO);
        if (insertSuperAdmin < 1) {
            throw new BizException("插入超级管理员失败");
        }

        return true;
    }

}
