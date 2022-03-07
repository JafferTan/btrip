package com.jaffer.btrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DepartmentController {
    @PostMapping("/getDeptDetailJson")
    @ResponseBody
    public String getDeptDetail(String corpId, Long deptId) {
        return "有瑜测试企业 "+ corpId  + "部门ID : " + deptId;
    }
}
