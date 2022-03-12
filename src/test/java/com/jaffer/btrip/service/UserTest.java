package com.jaffer.btrip.service;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.AbsTest;
import com.jaffer.btrip.beans.entity.UserMaintainRQ;
import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.util.BtripResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AbsTest.class)
public class UserTest extends AbsTest{

    public static final String testCorpId = "btrip2ad044b586ce4a7bbfc9d152e7e54392";

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        UserMaintainRQ rq = new UserMaintainRQ();
        rq.setCorpId(testCorpId);
        rq.setPhoneNumber("18857888804");
        rq.setUserName("用户信息test1");
        rq.setDeptId(1L);
        BtripResult<Boolean> orEditUser = userService.createOrEditUser(rq);
        System.out.println(JSON.toJSONString(orEditUser));
    }


    @Test
    public void testEditUser() {
        UserMaintainRQ rq = new UserMaintainRQ();
        rq.setCorpId(testCorpId);
        rq.setUserId("btripc27b797389fb4693b9124f96e1a8386d");
        rq.setUserName("修改用户姓名22222");
        BtripResult<Boolean> orEditUser = userService.createOrEditUser(rq);
        System.out.println(JSON.toJSONString(orEditUser));
    }

    @Test
    public void testDelteUser() {
        BtripResult<Boolean> result = userService.deleteUserByUserId(testCorpId, "btripc27b797389fb4693b9124f96e1a8386d");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testGetUserInfo() {
        BtripResult<UserPO> result = userService.getUserDetailByUserId(testCorpId, "btripc27b797389fb4693b9124f96e1a8386d");
        System.out.println(JSON.toJSONString(result));
    }

}
