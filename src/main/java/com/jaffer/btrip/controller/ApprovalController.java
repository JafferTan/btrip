package com.jaffer.btrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApprovalController {
    @PostMapping("/approval")
    @ResponseBody
    public String login() {
        return "测试登陆审批首页";
    }
}
