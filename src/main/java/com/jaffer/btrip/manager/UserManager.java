package com.jaffer.btrip.manager;

import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.beans.entity.UserPOExample;
import com.jaffer.btrip.enums.RowStatusEnum;
import com.jaffer.btrip.mapper.UserPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class UserManager {

    @Autowired
    private UserPOMapper userPOMapper;

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

    public Long countUserByDeptIdList(List<Long> deptIdList) {
        UserPOExample userPOExample = new UserPOExample();
        UserPOExample.Criteria criteria = userPOExample.createCriteria().andDeptIdIn(deptIdList).andStatusEqualTo(RowStatusEnum.NORMAL.getStatus());
        return userPOMapper.countByExample(userPOExample);
    }
}
