package com.jaffer.btrip.service;

import com.jaffer.btrip.beans.entity.UserMaintainRQ;
import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.util.BtripResult;

import java.util.List;
import java.util.Map;

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


    /**
     * 获取用户的手机号
     * @param corpId
     * @param userIds
     * @return key:userId, value:对应的手机号
     */
    BtripResult<Map<String,String>> getUserPhoneNumber(String corpId, List<String> userIds);


    /**
     * 根据手机号获取用户信息
     * @param corpId
     * @param phoneNumber
     * @return
     */
    BtripResult<UserPO> getUserDetailByPhoneNumber(String corpId, String phoneNumber);


    /**
     * 获取某个部门下的所有人员
     * @param corpId
     * @param deptId
     * @return
     */
    BtripResult<List<UserPO>> getDeptStaff(String corpId, Long deptId);

}
