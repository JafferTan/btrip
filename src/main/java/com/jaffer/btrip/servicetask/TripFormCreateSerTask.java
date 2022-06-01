package com.jaffer.btrip.servicetask;

import com.jaffer.btrip.enums.WorkFlowKeyWordConstants;
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

    @Override
    public void execute(DelegateExecution delegateExecution) {
        String processDefinitionId = delegateExecution.getProcessDefinitionId();

        System.out.println("流程启动! 流程实例id :" + processDefinitionId);

        Map<String, Object> variables = delegateExecution.getVariables();

        String corpId  = (String) variables.get(WorkFlowKeyWordConstants.CORP_ID);
        System.out.println("企业ID:" + corpId);

        String userId  = (String) variables.get(WorkFlowKeyWordConstants.USER_ID);
        System.out.println("用户ID:" + userId);

        Integer level = (Integer) variables.get(WorkFlowKeyWordConstants.LEVEL);
        System.out.println("目前是第" + level + "级主管审批");

    }


}
