package com.jaffer.btrip.service;

import com.jaffer.btrip.AbsTest;
import com.jaffer.btrip.util.BtripResult;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AbsTest.class)
public class WorkFlowTest extends AbsTest{

    @Autowired
    private WorkFlowService workFlowService;

    @Test
    public void completeTaskByUserId() {
        String corpId = "btrip31979f0b54204e64856d057054f9e1ce";
        String userId = "btripf88c89a118294621a9e47af4586aaef6";
        BtripResult<Boolean> result = workFlowService.completeTaskByUserId (corpId, userId);
        System.out.println(result);
    }

    @Test
    public void testQueryByProcessInstanceId(){
        String processInstanceId = "372c24de-dfe4-11ec-ac5a-12876bd2949f";
        BtripResult<Task> result = workFlowService.queryTask(processInstanceId);
        System.out.println(result);
    }
}
