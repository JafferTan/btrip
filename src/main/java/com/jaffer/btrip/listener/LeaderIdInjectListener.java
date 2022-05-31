package com.jaffer.btrip.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import java.util.Map;

public class LeaderIdInjectListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        Map<String, Object> variables = delegateTask.getVariables();
        Integer level = (Integer) variables.get("level");
        String corpId = (String) variables.get("corpId");
        String userId = (String) variables.get("userId");

    }
}
