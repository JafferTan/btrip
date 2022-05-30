package com.jaffer.btrip.listener;

import com.alibaba.fastjson.JSON;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang.StringUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Component;

@Component
public class CreateTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println(JSON.toJSONString(delegateTask));
        if (StringUtils.equals("创建请假单", delegateTask.getName()) && "create".equals(delegateTask.getEventName()) ) {
            delegateTask.setAssignee("zhangsan");
        }
    }
}
