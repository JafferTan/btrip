package com.jaffer.btrip.listener;

import com.alibaba.fastjson.JSON;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang.StringUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CreateTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        Map<String, Object> variables = delegateTask.getVariables();
        String corpId = (String) variables.get("corpId");
        String userId = (String) variables.get("userId");

    }
}