package com.jaffer.btrip.listener;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.enums.EvectionFormOwnType;
import com.jaffer.btrip.enums.WorkFlowKeyWordConstants;
import com.jaffer.btrip.manager.TripFormMappingManager;
import com.jaffer.btrip.manager.UserManager;
import com.jaffer.btrip.service.DeptService;
import com.jaffer.btrip.util.BtripResult;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("leaderIdInjectListener")
public class LeaderIdInjectListener implements TaskListener{

    @Autowired
    private DeptService deptService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TripFormMappingManager tripFormMappingManager;

    @Autowired
    private UserManager userManager;

    @Override
    public void notify(DelegateTask delegateTask) {
        Map<String, Object> variables = delegateTask.getVariables();
        System.out.println(JSON.toJSONString("流程map:" + variables));

        String processInstanceId = delegateTask.getProcessInstanceId();
        Integer level = (Integer) variables.get(WorkFlowKeyWordConstants.LEVEL);
        level++;
        System.out.println("目前是第" + level  + "级主管审批");
        String corpId = (String) variables.get(WorkFlowKeyWordConstants.CORP_ID);
        String userId = (String) variables.get(WorkFlowKeyWordConstants.USER_ID);


        BtripResult<String> parentDeptManagerId = deptService.getParentDeptManagerId(corpId, userId, level);

        if (parentDeptManagerId != null && parentDeptManagerId.getSuccess() && StringUtils.isNotEmpty(parentDeptManagerId.getModule())) {
            taskService.setVariable(delegateTask.getId(), WorkFlowKeyWordConstants.LEVEL, level);
            System.out.println("parentDeptManagerId:" + parentDeptManagerId.getModule());

            tripFormMappingManager.addTripFormMapping(corpId, parentDeptManagerId.getModule(), processInstanceId, EvectionFormOwnType.APPROVER);

            UserPO userByUserId = userManager.findUserByUserId(corpId, parentDeptManagerId.getModule());
            System.out.println("给主管:" + parentDeptManagerId.getModule() + "的手机号:" + userByUserId.getPhone() + "发审批短信");

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
