package com.jaffer.btrip.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.jaffer.btrip.beans.entity.UserMaintainRQ;
import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.manager.UserManager;
import com.jaffer.btrip.service.UserService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserManager userManager;

    @Override
    public BtripResult<Boolean> createOrEditUser(UserMaintainRQ rq) {
        try {
            if (StringUtils.isEmpty(rq.getUserId())) {
                userManager.createUser(rq);
            } else {
                userManager.editUser(rq);
            }
            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            log.error("createOrEditUser fail, rq：{}",rq, e);
            return BtripResultUtils.returnFailMsg("维护部门人员信息失败,失败原因:"  + e.getMessage());
        }

    }

    @Override
    public BtripResult<UserPO> getUserDetailByUserId(String corpId, String userId) {
        try {
            UserPO userByUserId = userManager.getUserByUserId(corpId, userId);
            if (Objects.isNull(userByUserId)) {
                return BtripResultUtils.returnFailMsg("用户不存在");
            }
            return BtripResultUtils.returnSuccess(userByUserId);
        } catch (Exception e) {
            log.error("createOrEditUser fail, corpId：{}, userId:{}",corpId,userId, e);
            return BtripResultUtils.returnFailMsg("维护部门人员信息失败,失败原因:"  + e.getMessage());
        }
    }

    @Override
    public BtripResult<Boolean> deleteUserByUserId(String corpId, String userId) {
        try {
            userManager.deleteUserByUserId(corpId, userId);

            return BtripResultUtils.returnSuccess(true);

        } catch (Exception e) {
            log.error("deleteUserByUserId fail, corpId：{}, userId:{}",corpId,userId, e);
            return BtripResultUtils.returnFailMsg("删除用户失败,失败原因:"  + e.getMessage());
        }
    }

    @Override
    public BtripResult<Map<String, String>> getUserPhoneNumber(String corpId, List<String> userIds) {

        try {
            Map<String,String> phoneMap = Maps.newHashMap();
            List<UserPO> userByUserIdList = userManager.getUserByUserIdList(corpId, userIds);

            for (UserPO userPO : userByUserIdList) {
                phoneMap.put(userPO.getUserId(), userPO.getPhone());
            }

            return BtripResultUtils.returnSuccess(phoneMap);

        } catch (Exception e) {
            log.error("getUserPhoneNumber fail, corpId:{}, userIds:{}", corpId, JSON.toJSONString(userIds), e);
            return BtripResultUtils.returnFailMsg("获取用户手机号失败,失败原因:" + e.getMessage());
        }

    }
}
