package com.jaffer.btrip.helper;

import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.enums.RowStatusEnum;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class UserServiceHelper {

    /**
     * 构建userPO 用于新建用户
     * @param userName
     * @param phoneNumber
     * @param corpId
     * @param deptId
     * @return
     */
    public UserPO buildUserPO(String userName, String phoneNumber, String corpId, Long deptId) {
        UserPO userPO = new UserPO();
        userPO.setGmtCreate(new Date());
        userPO.setGmtModified(new Date());
        userPO.setUserId("btrip" + UUID.randomUUID().toString().replace("-",""));
        userPO.setCorpId(corpId);
        userPO.setPhone(phoneNumber);
        userPO.setStatus(RowStatusEnum.NORMAL.getStatus());
        userPO.setUserName(userName);
        userPO.setDeptId(deptId);
        return userPO;
    }
}
