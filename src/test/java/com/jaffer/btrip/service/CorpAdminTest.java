package com.jaffer.btrip.service;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.AbsTest;
import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.util.BtripResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AbsTest.class)
public class CorpAdminTest extends AbsTest {

    public static final String testCorpId = "btrip2ad044b586ce4a7bbfc9d152e7e54392";

    @Autowired
    private CorpAdminService corpAdminService;

    @Test
    public void testGetSuperAdmin(){
        BtripResult<UserPO> corpSuperAdminInfo = corpAdminService.getCorpSuperAdminInfo(testCorpId);

        System.out.println(JSON.toJSONString(corpSuperAdminInfo));
    }

    @Test
    public void testGetAllAdmin(){
        BtripResult<List<UserPO>> corpAllAdminInfo = corpAdminService.getCorpAllAdminInfo(testCorpId);
        System.out.println(JSON.toJSONString(corpAllAdminInfo));
    }

    @Test
    public void testAddAdmin() {
        String userId = "btripf0cabfb533fb4189a48f06db14a2e400";
        BtripResult<Boolean> result = corpAdminService.addCorpAdmin(testCorpId, userId);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testChangeSuperAdminFromAdmin() {
        String userId = "btrip019afb987be645f9b4d2bf9e11954d88";
        String oldUserId = "btripf0cabfb533fb4189a48f06db14a2e400";
        BtripResult<Boolean> result = corpAdminService.changeCorpSuperAdmin(testCorpId, userId, oldUserId);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testDeleteAdmin(){
        String oldUserId = "btripf0cabfb533fb4189a48f06db14a2e400";
        BtripResult<Boolean> result = corpAdminService.deleteCorpAdmin(testCorpId, oldUserId);
        System.out.println(JSON.toJSONString(result));
    }

}
