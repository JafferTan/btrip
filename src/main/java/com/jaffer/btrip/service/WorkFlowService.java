package com.jaffer.btrip.service;

import com.jaffer.btrip.util.BtripResult;
import org.activiti.engine.task.Task;

public interface WorkFlowService {

    BtripResult<Boolean> completeTaskByUserId(String corpId, String userId);

    BtripResult<Boolean> completeTaskByTaskId(String taskId);

    BtripResult<Task> queryTask(String processInstanceId);

    BtripResult<Boolean> refuseTaskByByUserId(String corpId, String userId);

    BtripResult<Boolean> refuseTaskByTaskId(String taskId);
}
