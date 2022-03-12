package com.jaffer.btrip.service.impl;

import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.manager.CorpAdminManager;
import com.jaffer.btrip.manager.UserManager;
import com.jaffer.btrip.service.CorpAdminService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import com.jaffer.btrip.util.RedisLockUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 企业的管理员的变更、查询类
 */
@Service
@Slf4j
public class CorpAdminServiceImpl implements CorpAdminService {

    private final static String LOCK_KEY_CORP_ADMIN_MAINTAIN = "LOCK_KEY_CORP_ADMIN_MAINTAIN_%s";

    @Autowired
    private CorpAdminManager corpAdminManager;

    @Autowired
    private UserManager userManager;

    @Override
    public BtripResult<UserPO> getCorpSuperAdminInfo(String corpId) {
        try {
            UserPO corpSuperAdmin = corpAdminManager.getCorpSuperAdmin(corpId);
            if (Objects.isNull(corpSuperAdmin)) {
                return BtripResultUtils.returnFailMsg("企业超级管理员不存在");
            }

            return BtripResultUtils.returnSuccess(corpSuperAdmin);
        } catch (Exception e) {
            log.error("getCorpSuperAdminInfo fail, corpId:{}",corpId, e);
            return BtripResultUtils.returnFailMsg("查询企业管理员失败，失败原因：{}" + e.getMessage());
        }
    }

    @Override
    public BtripResult<Boolean> changeCorpSuperAdmin(String corpId, String newCorpAdminUserId, String operator) {

        String lockKey = String.format(LOCK_KEY_CORP_ADMIN_MAINTAIN, corpId);
        try {
            boolean lock = RedisLockUtils.tryLock(lockKey);
            if (BooleanUtils.isFalse(lock)) {
                return BtripResultUtils.returnFailMsg("多个用户正在维护企业管理员信息，请稍后重试");
            }


            String corpSuperAdminUserId = corpAdminManager.getCorpSuperAdminUserId(corpId);
            if (!StringUtils.equals(operator, corpSuperAdminUserId)) {
                return BtripResultUtils.returnFailMsg("非企业超级管理员不允许转让管理员权限");
            }
            Set<String> adminUserIdSet = corpAdminManager.getCorpAllAdminList(corpId).stream().map(UserPO::getUserId).collect(Collectors.toSet());

            if (adminUserIdSet.contains(newCorpAdminUserId)) {
                corpAdminManager.changeAdminToSuperAdmin(corpId, newCorpAdminUserId, operator);
            } else {
                corpAdminManager.changeUserToSuperAdmin(corpId, newCorpAdminUserId, operator);
            }

            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            log.error("changeCorpSuperAdminInfo fail, corpId:{}, newCorpAdminUserId:{}, operator:{}",corpId, newCorpAdminUserId, operator, e);
            return BtripResultUtils.returnFailMsg("转让超级管理员权限失败，失败原因:" + e.getMessage());
        } finally {
            RedisLockUtils.unlock(lockKey);
        }
    }


    @Override
    public BtripResult<Boolean> addCorpAdmin(String corpId, String userId) {
        String lockKey = String.format(LOCK_KEY_CORP_ADMIN_MAINTAIN, corpId);
        try {
            boolean lock = RedisLockUtils.tryLock(lockKey);
            if (BooleanUtils.isFalse(lock)) {
                return BtripResultUtils.returnFailMsg("多个用户正在维护企业管理员信息，请稍后重试");
            }

            Set<String> adminUserIdSet = corpAdminManager.getCorpAllAdminList(corpId).stream().map(UserPO::getUserId).collect(Collectors.toSet());
            if (adminUserIdSet.contains(userId)) {
                return BtripResultUtils.returnFailMsg("该用户已是企业管理员");
            }
            if (adminUserIdSet.size() > 11) {
                //1个固定的超级管理员 + 10个普通管理员 超过的话就不能添加了
                return BtripResultUtils.returnFailMsg("企业管理员已达到上限");
            }

            corpAdminManager.addCorpAdmin(corpId, userId);
            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            log.error("addCorpAdmin fail, corpId:{}, userId:{}",corpId, userId, e);
            return BtripResultUtils.returnFailMsg("新增管理员失败，失败原因:" + e.getMessage());
        } finally {
            RedisLockUtils.unlock(lockKey);
        }
    }

    @Override
    public BtripResult<Boolean> deleteCorpAdmin(String corpId, String userId) {
        String lockKey = String.format(LOCK_KEY_CORP_ADMIN_MAINTAIN, corpId);
        try {
            boolean lock = RedisLockUtils.tryLock(lockKey);
            if (BooleanUtils.isFalse(lock)) {
                return BtripResultUtils.returnFailMsg("多个用户正在维护企业管理员信息，请稍后重试");
            }

            Set<String> adminUserIdSet = corpAdminManager.getCorpAllAdminList(corpId).stream().map(UserPO::getUserId).collect(Collectors.toSet());
            if (!adminUserIdSet.contains(userId)) {
                return BtripResultUtils.returnFailMsg("该用户不是企业管理员");
            }

            corpAdminManager.deleteCorpAdmin(corpId, userId);
            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            log.error("addCorpAdmin fail, corpId:{}, userId:{}",corpId, userId, e);
            return BtripResultUtils.returnFailMsg("回收管理员权限失败，失败原因:" + e.getMessage());
        } finally {
            RedisLockUtils.unlock(lockKey);
        }
    }


    @Override
    public BtripResult<List<UserPO>> getCorpAllAdminInfo(String corpId) {
        try {
            List<UserPO> corpAllAdminList = corpAdminManager.getCorpAllAdminList(corpId);
            if (CollectionUtils.isEmpty(corpAllAdminList)) {
                return BtripResultUtils.returnFailMsg("企业内不存在任何管理员");
            }
            return BtripResultUtils.returnSuccess(corpAllAdminList);
        } catch (Exception e) {
            log.error("getCorpAllAdminInfo fail, corpId:{}, userId:{}",corpId, e);
            return BtripResultUtils.returnFailMsg("查询企业管理员信息失败，失败原因:" + e.getMessage());
        }
    }
}
