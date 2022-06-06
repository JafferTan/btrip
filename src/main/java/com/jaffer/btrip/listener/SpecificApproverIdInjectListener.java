package com.jaffer.btrip.listener;

import com.google.common.collect.Lists;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("specificApproverIdInjectListener")
public class SpecificApproverIdInjectListener implements TaskListener {


    @Autowired
    private TaskService taskService;

    @Override
    public void notify(DelegateTask delegateTask) {
        String assignee = delegateTask.getAssignee();
        System.out.println("当前的审批人为" + assignee);
    }
}
