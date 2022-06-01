package com.jaffer.btrip.servicetask;

import com.jaffer.btrip.enums.WorkFlowKeyWordConstants;
import com.jaffer.btrip.service.DeptService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.delegate.ActivityBehavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component("tripFormFinishSerTask")
public class TripFormFinishSerTask implements JavaDelegate {

    @Autowired
    private DeptService deptService;

    @Override
    public void execute(DelegateExecution delegateExecution) {

        String processInstanceId = delegateExecution.getProcessInstanceId();

        System.out.println("流程结束! 流程实例id :" + processInstanceId);

        Map<String, Object> variables = delegateExecution.getVariables();

        String corpId  = (String) variables.get(WorkFlowKeyWordConstants.CORP_ID);
        System.out.println("企业ID:" + corpId);

        String userId  = (String) variables.get(WorkFlowKeyWordConstants.USER_ID);
        System.out.println("用户ID" + userId);

        Integer approval = (Integer) variables.get(WorkFlowKeyWordConstants.APPROVAL);
        if (Objects.equals(approval, 1)) {
            System.out.println("流程结束! 同意审批");
        } else {
            System.out.println("流程结束! 拒绝审批");
        }
    }
}
