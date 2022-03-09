package com.jaffer.btrip.controller;

import com.jaffer.btrip.beans.entity.DeptMaintainRQ;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import com.jaffer.btrip.util.BtripSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String password) {
        try {

        } catch (Exception e) {
            return "login";
        }
        return "index";
    }

    private void checkLoginParms(String phoneNumber, String password) {
        if (StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("密码缺失");
        }
        if (StringUtils.isEmpty(phoneNumber)) {
            throw new IllegalArgumentException("手机号缺失");
        }
    }
}
