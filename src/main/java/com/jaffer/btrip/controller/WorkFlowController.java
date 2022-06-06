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


    @PostMapping("/completeTaskByUserId")
    @ResponseBody
    public ModelAndView completeTaskByUserId(ModelAndView modelAndView, @RequestParam("corpId") String corpId, @RequestParam("userId") String userId) {
        Map<String, Object> model = modelAndView.getModel();
        try {
            BtripResult<Boolean> result = workFlowService.completeTaskByUserId(corpId, userId);
            if (result.getSuccess()) {
                model.put("response","任务完成成功");
                modelAndView.setViewName("workflow");
                return modelAndView;
            } else {
                model.put("response", "任务失败");
                modelAndView.setViewName("workflow");
            }
            return modelAndView;
        } catch (Exception e) {
            model.put("response", "出现异常，异常原因:" + e.getMessage());
            modelAndView.setViewName("workflow");
            return modelAndView;
        }
    }

    @PostMapping("/refuseTaskByUserId")
    @ResponseBody
    public ModelAndView refuseTaskByUserId (ModelAndView modelAndView, @RequestParam("corpId") String corpId, @RequestParam("userId") String userId) {
        Map<String, Object> model = modelAndView.getModel();
        try {

            BtripResult<Boolean> result = workFlowService.refuseTaskByByUserId(corpId, userId);

            if (result.getSuccess()) {
                model.put("response","任务完成成功");
                modelAndView.setViewName("workflow");
                return modelAndView;
            } else {
                model.put("response", "任务失败");
                modelAndView.setViewName("workflow");
            }

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
            hashMap.put(WorkFlowKeyWordConstants.LEVEL, 0);
            runtimeService.startProcessInstanceByKey(bizKey, hashMap);
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
