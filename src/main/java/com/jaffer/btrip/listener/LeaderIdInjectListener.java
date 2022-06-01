package com.jaffer.btrip.listener;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.enums.WorkFlowKeyWordConstants;
import com.jaffer.btrip.service.DeptService;
import com.jaffer.btrip.util.BtripResult;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("leaderIdInjectListener")
public class LeaderIdInjectListener implements TaskListener{

    @Autowired
    private DeptService deptService;

    @Autowired
    private TaskService taskService;

    @Override
    public void notify(DelegateTask delegateTask) {
        Map<String, Object> variables = delegateTask.getVariables();
        System.out.println(JSON.toJSONString("流程map:" + variables));

        Integer level = (Integer) variables.get(WorkFlowKeyWordConstants.LEVEL);
        level++;
        System.out.println("目前是第" + level  + "级主管审批");
        String corpId = (String) variables.get(WorkFlowKeyWordConstants.CORP_ID);
        System.out.println("corpId:" + corpId);
        String userId = (String) variables.get(WorkFlowKeyWordConstants.USER_ID);
        System.out.println("userId:" + userId);

        BtripResult<String> parentDeptManagerId = deptService.getParentDeptManagerId(corpId, userId, level);

        if (parentDeptManagerId != null && parentDeptManagerId.getSuccess() && StringUtils.isNotEmpty(parentDeptManagerId.getModule())) {
            taskService.setVariable(delegateTask.getId(), WorkFlowKeyWordConstants.LEVEL, level);
            System.out.println("parentDeptManagerId:" + parentDeptManagerId.getModule());
            delegateTask.setAssignee(corpId + "-" + parentDeptManagerId.getModule());
        } else {
            System.out.println("该级别没有主管，自动通过审批");
            variables.put(WorkFlowKeyWordConstants.LEVEL, level);
            variables.put(WorkFlowKeyWordConstants.APPROVAL, 1);
            String taskId = delegateTask.getId();
            taskService.complete(taskId, variables);
        }
    }
}
