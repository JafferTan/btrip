package com.jaffer.btrip.manager;

import com.jaffer.btrip.beans.entity.*;
import com.jaffer.btrip.enums.BtripSpecialDeptEnum;
import com.jaffer.btrip.enums.RowStatusEnum;
import com.jaffer.btrip.exception.BizException;
import com.jaffer.btrip.mapper.UserPOMapper;
import com.mysql.cj.result.Row;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class UserManager {

    @Resource
    private UserPOMapper userPOMapper;

    @Resource
    private CorpAdminManager corpAdminManager;

    @Resource
    private DeptManager deptManager;

    /**
     * 根据手机号查询用户信息
     * @param phoneNumber
     * @return
     */
    public UserPO findUserByPhoneNumber(String phoneNumber) {
        UserPOExample userPOExample = new UserPOExample();
        UserPOExample.Criteria criteria = userPOExample.createCriteria().andPhoneEqualTo(phoneNumber).andStatusEqualTo(RowStatusEnum.NORMAL.getStatus());
        List<UserPO> userPOS = userPOMapper.selectByExample(userPOExample);
        if (CollectionUtils.isEmpty(userPOS)) {
            return null;
        }
        return userPOS.get(0);
    }

    /**
     * 根据手机号查询用户信息
     * @param phoneNumber
     * @return
     */
    public UserPO findUserByPhoneNumber(String corpId, String phoneNumber) {
        UserPOExample userPOExample = new UserPOExample();
        UserPOExample.Criteria criteria = userPOExample.createCriteria().andPhoneEqualTo(phoneNumber).andStatusEqualTo(RowStatusEnum.NORMAL.getStatus()).andCorpIdEqualTo(corpId);
        List<UserPO> userPOS = userPOMapper.selectByExample(userPOExample);
        if (CollectionUtils.isEmpty(userPOS)) {
            return null;
        }
        return userPOS.get(0);
    }

    public Long countUserByDeptIdList(List<Long> deptIdList) {
        UserPOExample userPOExample = new UserPOExample();
        UserPOExample.Criteria criteria = userPOExample.createCriteria().andDeptIdIn(deptIdList).andStatusEqualTo(RowStatusEnum.NORMAL.getStatus());
        return userPOMapper.countByExample(userPOExample);
    }

    /**
     * 创建用户
     *
     * @param rq
     * @return
     */
    @Transactional
    public Boolean createUser(UserMaintainRQ rq) {

        if (!Objects.equals(rq.getDeptId(), BtripSpecialDeptEnum.ROOT_DEPT.getDeptId())) {
            DeptPO dept = deptManager.getDeptByDeptId(rq.getCorpId(), rq.getDeptId());
            if (Objects.isNull(dept)) {
                throw new BizException("部门不存在");
            }
        }

        UserPO userByPhoneNumber = this.findUserByPhoneNumber(rq.getCorpId(), rq.getPhoneNumber());
        if (!Objects.isNull(userByPhoneNumber)) {
            throw new BizException("该公司已有相同手机号的用户");
        }


        UserPO userPO = this.convert2UserPO(rq);
        userPO.setGmtCreate(new Date());
        userPO.setUserId("btrip" + UUID.randomUUID().toString().replace("-",""));
        userPO.setGmtCreate(new Date());

        int insertRes = userPOMapper.insert(userPO);
        if (insertRes <= 0) {
            throw new BizException("新建用户失败");
        }
        return true;
    }

    private UserPO convert2UserPO(UserMaintainRQ rq) {
        UserPO userPO = new UserPO();
        userPO.setUserName(rq.getUserName());
        userPO.setCorpId(rq.getCorpId());
        userPO.setGmtModified(new Date());
        userPO.setPhone(rq.getPhoneNumber());
        userPO.setDeptId(rq.getDeptId());
        userPO.setStatus(RowStatusEnum.NORMAL.getStatus());
        return userPO;
    }

    /**
     * 编辑用户信息
     *
     * @param rq
     * @return
     */
    @Transactional
    public Boolean editUser(UserMaintainRQ rq) {

        UserPO userByUserId = this.getUserByUserId(rq.getCorpId(), rq.getUserId());
        if (Objects.isNull(userByUserId)) {
            throw new BizException("用户不存在");
        }

        UserPO userByPhoneNumber = this.findUserByPhoneNumber(rq.getCorpId(), rq.getPhoneNumber());
        if (!Objects.isNull(userByPhoneNumber) && !StringUtils.equals(userByPhoneNumber.getUserId(), rq.getUserId())) {
            throw new BizException("该公司已有相同手机号的用户");
        }

        userByUserId.setGmtModified(new Date());
        userByUserId.setUserName(rq.getUserName());
        userByUserId.setPhone(rq.getPhoneNumber());

        int updateRes = userPOMapper.updateByPrimaryKeySelective(userByUserId);
        if (updateRes <= 0) {
            throw new BizException("编辑用户信息失败");
        }
        return true;
    }

    /**
     * 根据用户id查询用户
     * @param corpId
     * @param userId
     * @return
     */
    public UserPO getUserByUserId(String corpId, String userId) {

        UserPOExample userPOExample = new UserPOExample();
        UserPOExample.Criteria criteria = userPOExample.
                createCriteria().andCorpIdEqualTo(corpId).andUserIdEqualTo(userId).andStatusEqualTo(RowStatusEnum.NORMAL.getStatus());
        List<UserPO> userPOS = userPOMapper.selectByExample(userPOExample);
        if (CollectionUtils.isEmpty(userPOS)) {
            return null;
        }

        return userPOS.get(0);
    }

    @Transactional
    public Boolean deleteUserByUserId(String corpId, String userId) {

        UserPO userByUserId = getUserByUserId(corpId, userId);
        if (Objects.isNull(userByUserId)) {
            throw new BizException("该用户不存在");
        }

        String corpSuperAdminUserId = corpAdminManager.getCorpSuperAdminUserId(corpId);
        if (StringUtils.equals(corpSuperAdminUserId, userId)) {
            throw new BizException("企业内的超级管理员不能直接离职，请先转移超级管理员权限");
        }

        userByUserId.setStatus(RowStatusEnum.DELETE.getStatus());
        int i = userPOMapper.updateByPrimaryKeySelective(userByUserId);
        if (i < 0) {
            throw new BizException("删除用户失败");
        }

        return true;
    }


    /**
     * 根据用户idList查询用户
     * @param corpId
     * @param userIdList
     * @return
     */
    public List<UserPO> getUserByUserIdList(String corpId, List<String> userIdList) {

        UserPOExample userPOExample = new UserPOExample();
        UserPOExample.Criteria criteria = userPOExample.
                createCriteria().andCorpIdEqualTo(corpId).andUserIdIn(userIdList).andStatusEqualTo(RowStatusEnum.NORMAL.getStatus());
        return userPOMapper.selectByExample(userPOExample);
    }
}
