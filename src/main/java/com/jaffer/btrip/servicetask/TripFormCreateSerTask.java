package com.jaffer.btrip.servicetask;

import com.google.common.collect.Lists;
import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.enums.WorkFlowKeyWordConstants;
import com.jaffer.btrip.manager.UserManager;
import com.jaffer.btrip.service.DeptService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.delegate.ActivityBehavior;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("tripFormCreateSerTask")
public class TripFormCreateSerTask implements JavaDelegate {

    @Autowired
    private DeptService deptService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserManager userManager;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        String processInstanceId = delegateExecution.getProcessInstanceId();
        System.out.println("流程启动! 流程实例id :" + processInstanceId);
        Map<String, Object> variables = delegateExecution.getVariables();
        String corpId  = (String) variables.get(WorkFlowKeyWordConstants.CORP_ID);;
        String userId  = (String) variables.get(WorkFlowKeyWordConstants.USER_ID);
        UserPO userByUserId = userManager.findUserByUserId(corpId, userId);
        System.out.println("给审批人:" + userId + "的手机号:" + userByUserId.getPhone() + "发审批短信");
        delegateExecution.setVariable("assigneeList", Lists.newArrayList("abc","edf","ddd"));
    }


}
