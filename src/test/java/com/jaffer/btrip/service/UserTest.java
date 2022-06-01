package com.jaffer.btrip.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.odps.udf.JSONTuple;
import com.jaffer.btrip.AbsTest;
import com.jaffer.btrip.beans.entity.UserMaintainRQ;
import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.enums.WorkFlowKeyWordConstants;
import com.jaffer.btrip.util.BtripResult;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ExecutionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AbsTest.class)
public class UserTest extends AbsTest{

    public static final String testCorpId = "btrip31979f0b54204e64856d057054f9e1ce";

    @Autowired
    private UserService userService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;


    @Test
    public void testCreateUser() {
        UserMaintainRQ rq = new UserMaintainRQ();
        rq.setCorpId(testCorpId);
        rq.setPhoneNumber("18857458804");
        rq.setUserName("用户信息test1");
        rq.setDeptId(6L);
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

    @Test
    public void testProcessEngine() {
        //部署流程文件
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService service = processEngine.getRepositoryService();
        Deployment deploy = service.createDeployment()
                .addClasspathResource("bpmn/evection.bpmn")
                .addClasspathResource("bpmn/evection.png")
                .name("出差demo")
                .key("evection")
                .deploy();
        System.out.println("部署id:" + deploy.getId());
        System.out.println("部署名称" + deploy.getName());
    }

    @Test
    public void queryDefinition() {
        //查询这个流程图的新版本的流程定义
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService service = processEngine.getRepositoryService();
        ProcessDefinitionQuery query = service.createProcessDefinitionQuery();
        query.processDefinitionKey("evection");
        query.latestVersion();
        ProcessDefinition definition = query.singleResult();
        System.out.println("流程定义id:" + definition.getId());
    }


    @Test
    public void ttestProcessEngineInstance() {
        //创建一个审批单，将这个审批单的id挂到某个人身上
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String key = "evection";
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put(WorkFlowKeyWordConstants.CORP_ID, "btrip31979f0b54204e64856d057054f9e1ce");
        hashMap.put(WorkFlowKeyWordConstants.USER_ID, "btripdba7660ecec94ac192671ba41ae2e0b4");
        hashMap.put(WorkFlowKeyWordConstants.LEVEL, 0);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, hashMap);
    }

    @Test
    public void queryInstanceStep() {
        String id = "372c24de-dfe4-11ec-ac5a-12876bd2949f";
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(id).singleResult();
        if (processInstance == null) {
            System.out.println("任务已经完成");
        } else {
            System.out.println("流程实例id:" + processInstance.getId());
            System.out.println("当前流程所处位置:" + processInstance.getActivityId());
        }
    }

    @Test
    public void queryTask() {
        String corpId = "btrip31979f0b54204e64856d057054f9e1ce";
        String userId = "btripf88c89a118294621a9e47af4586aaef6";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee(corpId + "-" + userId)
                .list();
        for (Task task : tasks) {
            System.out.println(JSON.toJSONString(task.getProcessVariables()));
        }
    }

    @Test
    public void complete() {
        String corpId = "btrip31979f0b54204e64856d057054f9e1ce";
        String userId = "btripf88c89a118294621a9e47af4586aaef6";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee(corpId + "-" + userId)
                .list();
        for (Task task : tasks) {
            taskService.setVariable(task.getId(), WorkFlowKeyWordConstants.APPROVAL, 1);
            taskService.complete(task.getId());
        }
    }

    @Test
    public void refuse() {
        String id = "372c24de-dfe4-11ec-ac5a-12876bd2949f";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processInstanceId(id)
                .singleResult();
    }

    @Test
    public void claim() {
        String id = "dd616aca-dfe0-11ec-9105-12876bd2949f";
        Task task = taskService.createTaskQuery()
                .processInstanceId(id)
                .singleResult();
        taskService.claim(task.getId(), "abc");
    }

    @Test
    public void testStartInstance() {
        //1.获取表单的填写内容，请假时间、请假事由、fromData
        //2.fromData 写入业务表
        //3.把业务数据与activiti7数据进行关联
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("evection");
        System.out.println("流程定义的id:" + processInstance.getProcessInstanceId());
        System.out.println("流程实例的id:" + processInstance.getId());
        System.out.println("当前活动的id:" + processInstance.getActivityId());

    }

    @Test
    public void actProcessInstance() {
        String id = "ccc30798-dfde-11ec-a998-12876bd2949f";
        runtimeService.activateProcessInstanceById(id);
    }
}
