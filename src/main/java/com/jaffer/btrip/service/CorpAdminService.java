package com.jaffer.btrip.service;

import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.util.BtripResult;

import java.util.List;


public interface CorpAdminService {

    /**
     * 获取企业的超级管理员信息
     * @param corpId
     * @return
     */
    BtripResult<UserPO> getCorpSuperAdminInfo(String corpId);

    /**
     * 更改企业的超级管理员
     * @param corpId
     * @param newCorpAdminUserId
     * @param operator
     * @return
     */
    BtripResult<Boolean> changeCorpSuperAdmin(String corpId, String newCorpAdminUserId, String operator);


    /**
     * 新增企业的普通管理员
     * @param corpId
     * @param userId
     * @return
     */
    BtripResult<Boolean> addCorpAdmin(String corpId, String userId);


    /**
     * 回收企业的管理员权限
     * @param corpId
     * @param userId
     * @return
     */
    BtripResult<Boolean> deleteCorpAdmin(String corpId, String userId);


    /**
     * 查询企业的所有管理员信息 - 包括企业的超级管理员与普通管理员
     * @param corpId
     * @return
     */
    BtripResult<List<UserPO>> getCorpAllAdminInfo(String corpId);

}
