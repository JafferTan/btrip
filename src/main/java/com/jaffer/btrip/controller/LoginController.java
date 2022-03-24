package com.jaffer.btrip.controller;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.beans.entity.CorpPO;
import com.jaffer.btrip.beans.entity.LoginInfo;
import com.jaffer.btrip.beans.entity.LoginUserCorpInfo;
import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.exception.BizException;
import com.jaffer.btrip.service.CorpService;
import com.jaffer.btrip.service.LoginService;
import com.jaffer.btrip.service.UserService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripSessionUtils;
import com.jaffer.btrip.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@Slf4j
public class LoginController {

    private static final String LOGIN_SESSION = "LOGIN_SESSION_%s";

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private CorpService corpService;

    @GetMapping("/login")
    public String login (){
        return "login";
    }


    @PostMapping("/loginJson")
    @ResponseBody
    public ModelAndView login(ModelAndView modelAndView, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("authCode") String authCode) {
        Map<String, Object> model = modelAndView.getModel();
        try {
            checkLoginParams(phoneNumber, authCode);

            BtripResult<LoginUserCorpInfo> result = loginService.loginByAuthCode(phoneNumber, authCode);
            if (result == null || BooleanUtils.isFalse(result.getSuccess())) {
                model.put("failReason", "登陆失败");
                modelAndView.setViewName("login");
                return modelAndView;
            }

            LoginUserCorpInfo module = result.getModule();
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setPhoneNumber(phoneNumber);
            BtripSessionUtils.setLoginInfo(loginInfo);
            model.put("corpList", JSON.toJSONString(module));
            modelAndView.setViewName("loginSelectCorp");
            return modelAndView;
        } catch (Exception e) {
            log.error("login fail, phoneNumber:{}, authCode:{}",phoneNumber, authCode, e);
            model.put("failReason", "出现异常，异常原因:" + e.getMessage());
            modelAndView.setViewName("login");
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
    public ModelAndView getAuthCode(ModelAndView modelAndView, @RequestParam("phoneNumber") String phoneNumber) {
        Map<String, Object> model = modelAndView.getModel();
        try {
            BtripResult<Boolean> result = loginService.getAuthCode(phoneNumber);
            if (result == null || BooleanUtils.isFalse(result.getSuccess())) {
                model.put("failReason", "获取验证码失败");
                modelAndView.setViewName("login");
                return modelAndView;
            }

            model.put("failReason","获取验证码成功");
            modelAndView.setViewName("login");
            return modelAndView;
        } catch (Exception e) {
            model.put("failReason", "出现异常，异常原因:" + e.getMessage());
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }

    @PostMapping("/selectUserCorpJson")
    @ResponseBody
    public ModelAndView selectUserCorp(ModelAndView modelAndView, @RequestParam("corpId") String corpId) {

        LoginInfo loginInfo = BtripSessionUtils.getLoginInfo();
        Map<String, Object> model = modelAndView.getModel();
        String phoneNumber = loginInfo.getPhoneNumber();
        try {
            BtripResult<CorpPO> corpRes = corpService.getCorpDetailByCorpId(corpId);
            if (corpRes == null || BooleanUtils.isFalse(corpRes.getSuccess())) {
                throw new BizException("企业不存在");
            }
            CorpPO corp = corpRes.getModule();
            BtripResult<UserPO> userRes = userService.getUserDetailByPhoneNumber(corpId, phoneNumber);
            if (userRes == null || BooleanUtils.isFalse(userRes.getSuccess())) {
                throw new BizException("用户不存在");
            }
            UserPO user = userRes.getModule();

            loginInfo.setCorpName(corp.getCorpName());
            loginInfo.setUserId(user.getUserId());
            loginInfo.setUserName(user.getUserName());
            loginInfo.setCorpId(corpId);
            BtripSessionUtils.setLoginInfo(loginInfo);
            model.put("loginInfo", loginInfo);
            modelAndView.setViewName("index");
            return modelAndView;
        } catch (Exception e) {
            log.error("selectUserCorp occurred exception, corpId:{}, phoneNumber:{}", corpId, phoneNumber, e);
            model.put("failReason", "登陆失败,失败原因:" + e.getMessage());
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }

    @PostMapping("/loginOutJson")
    public String loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            session.invalidate();
            return "login";
        } catch (Exception e) {
            log.error("loginOut fail, sessionId:{}",session.getId(), e);
            return "login";
        }
    }
}
