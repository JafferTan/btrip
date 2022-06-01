package com.jaffer.btrip.controller;

import com.jaffer.btrip.beans.entity.LoginInfo;
import com.jaffer.btrip.enums.WorkFlowKeyWordConstants;
import com.jaffer.btrip.service.WorkFlowService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripSessionUtils;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class WorkFlowController {

    @Autowired
    private WorkFlowService workFlowService;

    @Autowired
    private RuntimeService runtimeService;

    @RequestMapping(value = "/workflow")
    public String hello(){
        return "workflow";
    }

    @PostMapping("/completeTask")
    @ResponseBody
    public ModelAndView completeTask(ModelAndView modelAndView, @RequestParam("processInstanceId") String processInstanceId) {
        Map<String, Object> model = modelAndView.getModel();
        try {
            BtripResult<Boolean> result = workFlowService.completeTask(processInstanceId);

            if (result == null || !result.getSuccess()) {
                model.put("response", "完成任务失败");
                modelAndView.setViewName("workflow");
                return modelAndView;
            }

            model.put("response","任务完成成功");
            modelAndView.setViewName("workflow");
            return modelAndView;

        } catch (Exception e) {
            model.put("response", "出现异常，异常原因:" + e.getMessage());
            modelAndView.setViewName("workflow");
            return modelAndView;
        }
    }

    @PostMapping("/queryTask")
    @ResponseBody
    public ModelAndView queryTask(ModelAndView modelAndView, @RequestParam("processInstanceId") String processInstanceId) {
        Map<String, Object> model = modelAndView.getModel();
        try {
            BtripResult<Task> result = workFlowService.queryTask(processInstanceId);
            if (result == null || !result.getSuccess()) {
                model.put("response", "查询任务失败");
                modelAndView.setViewName("workflow");
                return modelAndView;
            }

            if (Objects.isNull(result.getModule().getProcessInstanceId())) {
                model.put("response", "该审批单已通过审批");
                modelAndView.setViewName("workflow");
                return modelAndView;
            }
            Task task = result.getModule();
            StringBuilder sb = new StringBuilder();
            sb.append("流程实例id:" + task.getProcessInstanceId() + "\n");
            sb.append("流程taskName:" + task.getName() + "\n");
            sb.append("任务签发人:" + task.getAssignee() + "\n");
            model.put("response", sb.toString());
            modelAndView.setViewName("workflow");
            return modelAndView;

        } catch (Exception e) {
            model.put("response", "出现异常，异常原因:" + e.getMessage());
            modelAndView.setViewName("workflow");
            return modelAndView;
        }
    }

    @PostMapping("/completeTaskByUserId")
    @ResponseBody
    public ModelAndView completeTaskByUserId(ModelAndView modelAndView, @RequestParam("processInstanceId") String processInstanceId, @RequestParam("userId") String userId) {
        Map<String, Object> model = modelAndView.getModel();
        try {
            BtripResult<Boolean> result = workFlowService.completeTask(processInstanceId);

            model.put("response","任务完成成功");
            modelAndView.setViewName("workflow");
            return modelAndView;

        } catch (Exception e) {
            model.put("response", "出现异常，异常原因:" + e.getMessage());
            modelAndView.setViewName("workflow");
            return modelAndView;
        }
    }

    @PostMapping("/createTask")
    @ResponseBody
    public ModelAndView createTask(ModelAndView modelAndView, @RequestParam("bizKey") String bizKey) {
        Map<String, Object> model = modelAndView.getModel();
        try {
            LoginInfo loginInfo = BtripSessionUtils.getLoginInfo();

            Map<String, Object> hashMap = new HashMap<>();

            hashMap.put(WorkFlowKeyWordConstants.CORP_ID, loginInfo.getCorpId());
            hashMap.put(WorkFlowKeyWordConstants.USER_ID, loginInfo.getUserId());
            hashMap.put(WorkFlowKeyWordConstants.LEVEL, 1);

            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(bizKey, hashMap);

            System.out.println("流程定义的id:" + processInstance.getProcessInstanceId());
            System.out.println("流程实例的id:" + processInstance.getId());
            System.out.println("当前活动的id:" + processInstance.getActivityId());
            System.out.println("当前流程的变量:" + processInstance.getProcessVariables());

            model.put("response","流程开启");
            modelAndView.setViewName("workflow");

            return modelAndView;
        } catch (Exception e) {
            model.put("response", "出现异常，异常原因:" + e.getMessage());
            modelAndView.setViewName("workflow");
            return modelAndView;
        }
    }



}
