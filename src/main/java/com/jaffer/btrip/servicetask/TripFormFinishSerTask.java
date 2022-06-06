package com.jaffer.btrip.servicetask;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.beans.entity.TripFormDO;
import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.enums.WorkFlowKeyWordConstants;
import com.jaffer.btrip.manager.TripInfoManager;
import com.jaffer.btrip.manager.UserManager;
import com.jaffer.btrip.service.DeptService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.delegate.ActivityBehavior;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component("tripFormFinishSerTask")
public class TripFormFinishSerTask implements JavaDelegate {

    @Autowired
    private DeptService deptService;

    @Autowired
    private UserManager userManager;

    @Autowired
    private TripInfoManager tripInfoManager;

    @Override
    public void execute(DelegateExecution delegateExecution) {

        String processInstanceId = delegateExecution.getProcessInstanceId();

        System.out.println("流程结束! 流程实例id :" + processInstanceId);

        Map<String, Object> variables = delegateExecution.getVariables();

        String corpId  = (String) variables.get(WorkFlowKeyWordConstants.CORP_ID);
        String userId  = (String) variables.get(WorkFlowKeyWordConstants.USER_ID);
        String jsonString = (String) variables.get(WorkFlowKeyWordConstants.FORM_DATA_JSON);
        TripFormDO tripFormDO = JSON.parseObject(jsonString, TripFormDO.class);
        Integer approval = (Integer) variables.get(WorkFlowKeyWordConstants.APPROVAL);
        if (Objects.equals(approval, 1)) {
            System.out.println("流程结束! 同意审批");

            UserPO userByUserId = userManager.findUserByUserId(corpId, userId);
            System.out.println("给审批人:" + userId + "的手机号:" + userByUserId.getPhone() + "发审批通过短信");

//            tripInfoManager.addTripInfos(corpId, userId, processInstanceId, tripFormDO.getTripInfoList());

        } else {
            System.out.println("流程结束! 拒绝审批");
        }
    }
}
