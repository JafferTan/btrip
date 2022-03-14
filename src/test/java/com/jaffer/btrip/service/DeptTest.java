package com.jaffer.btrip.service;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.AbsTest;
import com.jaffer.btrip.beans.entity.DeptMaintainRQ;
import com.jaffer.btrip.beans.entity.DeptPO;
import com.jaffer.btrip.service.DeptService;
import com.jaffer.btrip.util.BtripResult;
import javafx.application.Application;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AbsTest.class)
public class DeptTest extends AbsTest {

    public static final String testCorpId = "btrip2ad044b586ce4a7bbfc9d152e7e54392";

    @Autowired
    private DeptService deptService;

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
        deptMaintainRQ.setDeptName("测试部门2");
        deptMaintainRQ.setDeptPid(1L);
        BtripResult<Boolean> orEditDept = deptService.createOrEditDept(deptMaintainRQ);
        System.out.println(JSON.toJSONString(orEditDept));
    }

    @Test
    public void testDeleteDept(){
        BtripResult<Boolean> result = deptService.deleteDept(testCorpId, 3L);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testGetDeptManagerIds() {
        BtripResult<Set<String>> deptManagerIds = deptService.getDeptManagerIds(testCorpId, 3L);
        System.out.println(JSON.toJSONString(deptManagerIds));
    }


    @Test
    public void testEditDeptInfo() {
        DeptMaintainRQ deptMaintainRQ = new DeptMaintainRQ();
        deptMaintainRQ.setCorpId(testCorpId);
        deptMaintainRQ.setDeptId(3L);
        deptMaintainRQ.setManagerIds("btripf0cabfb533fb4189a48f06db14a2e400|btrip019afb987be645f9b4d2bf9e11954d88");
        BtripResult<Boolean> orEditDept = deptService.createOrEditDept(deptMaintainRQ);
        System.out.println(JSON.toJSONString(orEditDept));
    }

    @Test
    public void testGetUserPhoneNumber() {
        BtripResult<Set<String>> deptManagerIds = deptService.getDeptManagerIds(testCorpId, 3L);

        List<String> strings = new ArrayList<>(deptManagerIds.getModule());

        BtripResult<Map<String, String>> userPhoneNumber = userService.getUserPhoneNumber(testCorpId, strings);

        System.out.println(JSON.toJSONString(userPhoneNumber));
    }

}
