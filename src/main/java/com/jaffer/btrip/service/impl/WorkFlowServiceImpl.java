package com.jaffer.btrip.service.impl;

import com.jaffer.btrip.enums.WorkFlowKeyWordConstants;
import com.jaffer.btrip.service.WorkFlowService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class WorkFlowServiceImpl implements WorkFlowService {

    @Autowired
    private TaskService taskService;

    @Override
    public BtripResult<Task> queryTask(String processInstanceId) {
        if (StringUtils.isEmpty(processInstanceId)) {
            return BtripResultUtils.returnFailMsg("taskId is null");
        }
        try {
            Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
            return BtripResultUtils.returnSuccess(task);
        } catch (Exception e) {
            log.error("WorkFlowService.queryTask occurred Exception, taskId:{}", processInstanceId, e);
            return BtripResultUtils.returnFailMsg("查询任务出现异常");
        }
    }


    @Override
    public BtripResult<Boolean> completeTaskByTaskId(String taskId) {
        try {
            taskService.setVariable(taskId, WorkFlowKeyWordConstants.APPROVAL, 1);
            taskService.complete(taskId);
            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            return BtripResultUtils.returnFailMsg("出现异常");
        }
    }

    @Override
    public BtripResult<Boolean> completeTaskByUserId(String corpId, String userId) {
        try {
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
            TaskService taskService = processEngine.getTaskService();
            List<Task> tasks = taskService.createTaskQuery()
                    .taskAssignee(corpId + "-" + userId)
                    .list();
            for (Task task : tasks) {
                taskService.setVariable(task.getId(), WorkFlowKeyWordConstants.APPROVAL, 1);
                taskService.complete(task.getId());
            }
            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            return BtripResultUtils.returnFailMsg("出现异常");

        }
    }

    @Override
    public BtripResult<Boolean> refuseTaskByByUserId(String corpId, String userId) {
        try {
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
            TaskService taskService = processEngine.getTaskService();
            List<Task> tasks = taskService.createTaskQuery()
                    .taskAssignee(corpId + "-" + userId)
                    .list();
            for (Task task : tasks) {
                taskService.setVariable(task.getId(), WorkFlowKeyWordConstants.APPROVAL, 0);
                taskService.complete(task.getId());
            }
            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            return BtripResultUtils.returnFailMsg("出现异常");

        }
    }

    @Override
    public BtripResult<Boolean> refuseTaskByTaskId(String taskId) {
        try {
            taskService.setVariable(taskId, WorkFlowKeyWordConstants.APPROVAL, 0);
            taskService.complete(taskId);
            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            return BtripResultUtils.returnFailMsg("出现异常");
        }
    }
}
