package com.jaffer.btrip.service;

import com.jaffer.btrip.beans.entity.UserMaintainRQ;
import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.util.BtripResult;

public interface UserService {
    /**
     * 创建或编辑部门人员
     * @param rq
     * @return
     */
    BtripResult<Boolean> createOrEditUser(UserMaintainRQ rq);

    /**
     * 根据用户id获取用户信息
     * @param corpId
     * @param userId
     * @return
     */
    BtripResult<UserPO> getUserDetailByUserId(String corpId, String userId);

    /**
     * 根据用户id删除用户
     * @param corpId
     * @param userId
     * @return
     */
    BtripResult<Boolean> deleteUserByUserId(String corpId, String userId);

}
