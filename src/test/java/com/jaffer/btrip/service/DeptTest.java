package com.jaffer.btrip.service;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.AbsTest;
import com.jaffer.btrip.beans.entity.DeptMaintainRQ;
import com.jaffer.btrip.beans.entity.DeptPO;
import com.jaffer.btrip.service.DeptService;
import com.jaffer.btrip.util.BtripResult;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AbsTest.class)
public class DeptTest extends AbsTest {

    public static final String testCorpId = "btrip2ad044b586ce4a7bbfc9d152e7e54392";

    @Autowired
    private DeptService deptService;

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

}
