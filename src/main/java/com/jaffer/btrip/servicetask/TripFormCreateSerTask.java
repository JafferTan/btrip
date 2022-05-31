package com.jaffer.btrip.servicetask;

import org.activiti.bpmn.model.ServiceTask;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class TripFormCreateSerTask implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        String processDefinitionId = delegateExecution.getProcessDefinitionId();
        
    }
}
