package com.jaffer.btrip.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.jaffer.btrip.service.WorkFlowService;
import com.jaffer.btrip.util.BtripResult;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Objects;

@Controller
public class WorkFlowController {

    @Autowired
    private WorkFlowService workFlowService;

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

            if (Objects.isNull(result.getModule())) {
                model.put("response", "该审批单已通过审批");
                modelAndView.setViewName("workflow");
                return modelAndView;
            }

            model.put("response", JSON.toJSONString(result.getModule()));
            modelAndView.setViewName("workflow");
            return modelAndView;

        } catch (Exception e) {
            model.put("response", "出现异常，异常原因:" + e.getMessage());
            modelAndView.setViewName("workflow");
            return modelAndView;
        }
    }




}
