package com.jaffer.btrip.service;

import com.jaffer.btrip.util.BtripResult;
import org.activiti.engine.task.Task;

public interface WorkFlowService {

    BtripResult<Boolean> completeTask(String processInstanceId);

    BtripResult<Task> queryTask(String processInstanceId);
}
