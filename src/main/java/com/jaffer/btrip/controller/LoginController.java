package com.jaffer.btrip.controller;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.beans.entity.DeptMaintainRQ;
import com.jaffer.btrip.beans.entity.LoginInfo;
import com.jaffer.btrip.service.LoginService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import com.jaffer.btrip.util.BtripSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String helloWorld() {
        return "login";
    }

    @PostMapping("/loginJson")
    @ResponseBody
    public ModelAndView login(ModelAndView modelAndView, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("authCode") String authCode) {
        Map<String, Object> model = modelAndView.getModel();
        try {
            checkLoginParams(phoneNumber, authCode);

            BtripResult<LoginInfo> result = loginService.loginByAuthCode(phoneNumber, authCode);
            if (result == null || BooleanUtils.isFalse(result.getSuccess())) {
                model.put("failReason", "登陆失败");
                modelAndView.setViewName("/login");
                return modelAndView;
            }

            LoginInfo module = result.getModule();
            model.put("phoneNumber", phoneNumber);
            model.put("corpList", JSON.toJSONString(module));
            modelAndView.setViewName("/index");
            return modelAndView;
        } catch (Exception e) {
            log.error("login fail, phoneNumber:{}, authCode:{}",phoneNumber, authCode, e);
            model.put("failReason", "出现异常，异常原因:" + e.getMessage());
            modelAndView.setViewName("/login");
            return modelAndView;
        }
    }

    private void checkLoginParams(String phoneNumber, String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            throw new IllegalArgumentException("验证码缺失");
        }
        if (StringUtils.isEmpty(phoneNumber)) {
            throw new IllegalArgumentException("手机号缺失");
        }
    }

    @PostMapping("/getAuthCodeJson")
    @ResponseBody
    public ModelAndView getAuthCode(@RequestParam("phoneNumber") String phoneNumber) {
        ModelAndView modelAndView = new ModelAndView();
        Map<String, Object> model = modelAndView.getModel();
        try {
            BtripResult<Boolean> result = loginService.getAuthCode(phoneNumber);
            if (result == null || BooleanUtils.isFalse(result.getSuccess())) {
                model.put("failReason", "获取验证码失败");
                modelAndView.setViewName("/login");
                return modelAndView;
            }

            model.put("failReason","获取验证码成功");
            modelAndView.setViewName("/login");
            return modelAndView;
        } catch (Exception e) {
            model.put("failReason", "出现异常，异常原因:" + e.getMessage());
            modelAndView.setViewName("/login");
            return modelAndView;
        }
    }

    @PostMapping("/selectUserCorpJson")
    @ResponseBody
    public ModelAndView selectUserCorp(ModelAndView modelAndView, @RequestParam("corpId") String corpId) {
        Map<String, Object> model = modelAndView.getModel();
        model.put("corpId", corpId);
        modelAndView.setViewName("/index");
        return modelAndView;
    }

}
