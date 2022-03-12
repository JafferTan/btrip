package com.jaffer.btrip.manager;

import com.google.common.collect.Lists;
import com.jaffer.btrip.beans.entity.CorpAdminPO;
import com.jaffer.btrip.beans.entity.CorpAdminPOExample;
import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.enums.CorpAdminEnum;
import com.jaffer.btrip.enums.RowStatusEnum;
import com.jaffer.btrip.exception.BizException;
import com.jaffer.btrip.helper.CorpAdminServiceHelper;
import com.jaffer.btrip.mapper.CorpAdminPOMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CorpAdminManager {

    @Resource
    private CorpAdminPOMapper corpAdminPOMapper;

    @Resource
    private UserManager userManager;

    @Autowired
    private CorpAdminServiceHelper corpAdminServiceHelper;


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

    /**
     * 获取企业的管理员列表 - 不包含超级管理员
     * @param corpId
     * @return
     */
    public List<UserPO> getCorpAdminList(String corpId) {
        List<UserPO> res = Lists.newArrayList();
        CorpAdminPOExample corpAdminPOExample = new CorpAdminPOExample();
        corpAdminPOExample.createCriteria().
                andCorpIdEqualTo(corpId).andStatusEqualTo(RowStatusEnum.NORMAL.getStatus()).andAdminTypeEqualTo(CorpAdminEnum.ADMIN.getAdminType());
        List<CorpAdminPO> corpAdminPOS = corpAdminPOMapper.selectByExample(corpAdminPOExample);

        if (CollectionUtils.isEmpty(corpAdminPOS)) {
            return res;
        }

        List<String> corpAdminList = corpAdminPOS.stream().map(CorpAdminPO::getUserId).collect(Collectors.toList());

        return userManager.getUserByUserIdList(corpId, corpAdminList);
    }

    /**
     * 获取企业的管理员列表 - 包含超级管理员
     * @param corpId
     * @return
     */
    public List<UserPO> getCorpAllAdminList(String corpId) {
        List<UserPO> res = Lists.newArrayList();
        CorpAdminPOExample corpAdminPOExample = new CorpAdminPOExample();
        corpAdminPOExample.createCriteria().
                andCorpIdEqualTo(corpId).andStatusEqualTo(RowStatusEnum.NORMAL.getStatus());
        List<CorpAdminPO> corpAdminPOS = corpAdminPOMapper.selectByExample(corpAdminPOExample);
        if (CollectionUtils.isEmpty(corpAdminPOS)) {
            return res;
        }
        List<String> collect = corpAdminPOS.stream().map(CorpAdminPO::getUserId).collect(Collectors.toList());

        List<UserPO> userByUserIdList = userManager.getUserByUserIdList(corpId, collect);

        return userByUserIdList;

    }

    /**
     * 将某个管理员升级成超级管理员
     * @param corpId
     * @param userId
     * @return
     */
    @Transactional
    public Boolean changeAdminToSuperAdmin(String corpId, String userId, String oldUserId) {

        UserPO userByUserId = userManager.getUserByUserId(corpId, userId);
        if (Objects.isNull(userByUserId)) {
            throw new BizException("该用户不存在,请先创建用户再转让超级管理员权限");
        }

        CorpAdminPOExample corpAdminPOExample1 = new CorpAdminPOExample();
        corpAdminPOExample1.createCriteria().
                andCorpIdEqualTo(corpId).andStatusEqualTo(RowStatusEnum.NORMAL.getStatus()).andUserIdEqualTo(oldUserId).andAdminTypeEqualTo(CorpAdminEnum.SUPER_ADMIN.getAdminType());
        CorpAdminPO corpAdminPO = new CorpAdminPO();
        corpAdminPO.setGmtModified(new Date());
        corpAdminPO.setStatus(RowStatusEnum.DELETE.getStatus());

        int oldAdmin = corpAdminPOMapper.updateByExampleSelective(corpAdminPO, corpAdminPOExample1);
        if (oldAdmin <= 0) {
            throw new BizException("回收超级管理员权限失败");
        }

        CorpAdminPOExample corpAdminPOExample2 = new CorpAdminPOExample();
        corpAdminPOExample2.createCriteria().
                andCorpIdEqualTo(corpId).andStatusEqualTo(RowStatusEnum.NORMAL.getStatus()).andUserIdEqualTo(userId).andAdminTypeEqualTo(CorpAdminEnum.ADMIN.getAdminType());
        corpAdminPO = new CorpAdminPO();
        corpAdminPO.setGmtModified(new Date());
        corpAdminPO.setAdminType(CorpAdminEnum.SUPER_ADMIN.getAdminType());

        int newAdmin = corpAdminPOMapper.updateByExampleSelective(corpAdminPO, corpAdminPOExample2);
        if (newAdmin <= 0) {
            throw new BizException("转移超级管理员权限失败");
        }

        return true;
    }

    @Transactional
    public Boolean addCorpAdmin(String corpId, String userId) {
        UserPO userByUserId = userManager.getUserByUserId(corpId, userId);
        if (Objects.isNull(userByUserId)) {
            throw new BizException("该用户不存在,请先创建用户再转让超级管理员权限");
        }

        CorpAdminPO corpAdminPO = corpAdminServiceHelper.buildCorpAdminPO(corpId, userId, CorpAdminEnum.ADMIN);
        int insert = corpAdminPOMapper.insert(corpAdminPO);
        if (insert <= 0) {
            throw new BizException("创建管理员信息失败");
        }

        return true;
    }

    @Transactional
    public Boolean addCorpSuperAdmin(String corpId, String userId) {
        UserPO userByUserId = userManager.getUserByUserId(corpId, userId);
        if (Objects.isNull(userByUserId)) {
            throw new BizException("该用户不存在,请先创建用户再转让超级管理员权限");
        }

        CorpAdminPO corpAdminPO = corpAdminServiceHelper.buildCorpAdminPO(corpId, userId, CorpAdminEnum.SUPER_ADMIN);
        int insert = corpAdminPOMapper.insert(corpAdminPO);
        if (insert <= 0) {
            throw new BizException("创建超级管理员信息失败");
        }

        return true;
    }

    @Transactional
    public Boolean changeUserToSuperAdmin(String corpId, String userId, String oldUserId) {
        UserPO userByUserId = userManager.getUserByUserId(corpId, userId);
        if (Objects.isNull(userByUserId)) {
            throw new BizException("该用户不存在,请先创建用户再转让超级管理员权限");
        }

        CorpAdminPOExample corpAdminPOExample = new CorpAdminPOExample();
        corpAdminPOExample.createCriteria().
                andCorpIdEqualTo(corpId).andStatusEqualTo(RowStatusEnum.NORMAL.getStatus()).andUserIdEqualTo(oldUserId).andAdminTypeEqualTo(CorpAdminEnum.SUPER_ADMIN.getAdminType());
        CorpAdminPO corpAdminPO = new CorpAdminPO();
        corpAdminPO.setGmtModified(new Date());
        corpAdminPO.setStatus(RowStatusEnum.DELETE.getStatus());

        int oldAdmin = corpAdminPOMapper.updateByExampleSelective(corpAdminPO, corpAdminPOExample);
        if (oldAdmin <= 0) {
            throw new BizException("回收超级管理员权限失败");
        }
        addCorpSuperAdmin(corpId, userId);

        return true;
    }


    public Boolean deleteCorpAdmin(String corpId, String userId) {
        CorpAdminPOExample corpAdminPOExample = new CorpAdminPOExample();
        corpAdminPOExample.createCriteria().
                andCorpIdEqualTo(corpId).andStatusEqualTo(RowStatusEnum.NORMAL.getStatus()).andUserIdEqualTo(userId).andAdminTypeEqualTo(CorpAdminEnum.ADMIN.getAdminType());
        CorpAdminPO corpAdminPO = new CorpAdminPO();
        corpAdminPO.setGmtModified(new Date());
        corpAdminPO.setStatus(RowStatusEnum.DELETE.getStatus());

        int oldAdmin = corpAdminPOMapper.updateByExampleSelective(corpAdminPO, corpAdminPOExample);
        if (oldAdmin <= 0) {
            throw new BizException("回收管理员权限失败");
        }
        return true;
    }
}
