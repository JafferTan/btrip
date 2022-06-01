package com.jaffer.btrip.service;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.AbsTest;
import com.jaffer.btrip.beans.entity.DeptMaintainRQ;
import com.jaffer.btrip.beans.entity.DeptPO;
import com.jaffer.btrip.manager.DeptManager;
import com.jaffer.btrip.util.BtripResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AbsTest.class)
public class DeptTest extends AbsTest {

    public static final String testCorpId = "btrip31979f0b54204e64856d057054f9e1ce";

    @Autowired
    private DeptService deptService;

    @Autowired
    private DeptManager deptManager;

    @Autowired
    private UserService userService;

    @Test
    public void testGetDeptDetailByDeptId(){
        BtripResult<DeptPO> deptDetailByDeptId = deptService.getDeptDetailByDeptId(testCorpId, 3L);
        System.out.println(JSON.toJSONString(deptDetailByDeptId));
    }

    @Test
    public void testCreateDept(){
        DeptMaintainRQ deptMaintainRQ = new DeptMaintainRQ();
        deptMaintainRQ.setCorpId(testCorpId);
        deptMaintainRQ.setDeptName("测试部门4");
        deptMaintainRQ.setDeptPid(6L);
        BtripResult<Boolean> orEditDept = deptService.createOrEditDept(deptMaintainRQ);
        System.out.println(JSON.toJSONString(orEditDept));
    }

    @Test
    public void testDeleteDept(){
        BtripResult<Boolean> result = deptService.deleteDept(testCorpId, 3L);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testGetDeptManagerId() {
        BtripResult<String> deptManagerId = deptService.getDeptManagerId(testCorpId, 3L);
        System.out.println(JSON.toJSONString(deptManagerId));
    }


    @Test
    public void testEditDeptInfo() {
        DeptMaintainRQ deptMaintainRQ = new DeptMaintainRQ();
        deptMaintainRQ.setCorpId(testCorpId);
        deptMaintainRQ.setDeptId(3L);
        deptMaintainRQ.setManagerId("btripf0cabfb533fb4189a48f06db14a2e400|btrip019afb987be645f9b4d2bf9e11954d88");
        BtripResult<Boolean> orEditDept = deptService.createOrEditDept(deptMaintainRQ);
        System.out.println(JSON.toJSONString(orEditDept));
    }

    @Test
    public void testGetUserPhoneNumber() {
        BtripResult<String> deptManagerId = deptService.getDeptManagerId(testCorpId, 3L);

        List<String> strings = new ArrayList<>();
        strings.add(deptManagerId.getModule());

        BtripResult<Map<String, String>> userPhoneNumber = userService.getUserPhoneNumber(testCorpId, strings);

        System.out.println(JSON.toJSONString(userPhoneNumber));
    }

    @Test
    public void getPDeptManagerId() {
        String parentDeptManagerId = deptManager.getParentDeptManagerId("btrip31979f0b54204e64856d057054f9e1ce", "btripdba7660ecec94ac192671ba41ae2e0b4", 1);
        System.out.println(parentDeptManagerId);
    }

}
