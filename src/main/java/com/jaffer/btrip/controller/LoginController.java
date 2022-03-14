package com.jaffer.btrip.controller;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.beans.entity.LoginInfo;
import com.jaffer.btrip.beans.entity.LoginUserCorpInfo;
import com.jaffer.btrip.service.LoginService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripSessionUtils;
import com.jaffer.btrip.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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


    @GetMapping(value = "/login")
    public String helloWorld() {
        return "/login";
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
                modelAndView.setViewName("/login");
                return modelAndView;
            }

            LoginUserCorpInfo module = result.getModule();
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setPhoneNumber(phoneNumber);
            BtripSessionUtils.setLoginInfo(loginInfo);
            model.put("corpList", JSON.toJSONString(module));
            modelAndView.setViewName("/loginSelectCorp");
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
    public ModelAndView getAuthCode(ModelAndView modelAndView, @RequestParam("phoneNumber") String phoneNumber) {
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
    public ModelAndView selectUserCorp(ModelAndView modelAndView, @RequestParam("corpId") String corpId, @RequestParam("userId") String userId, @RequestParam("corpName") String corpName) {
        LoginInfo loginInfo = BtripSessionUtils.getLoginInfo();
        loginInfo.setUserId(userId);
        loginInfo.setCorpId(corpId);
        loginInfo.setCorpName(corpName);
        Map<String, Object> model = modelAndView.getModel();

        model.put("loginInfo", loginInfo);
        modelAndView.setViewName("/home");
        return modelAndView;
    }

    @PostMapping("/loginOutJson")
    public String loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String id = session.getId();
        try {
            session.invalidate();
            Jedis jedis = RedisUtils.getJedis();
            assert jedis != null;
            jedis.del(String.format(LOGIN_SESSION, id));
            return "/login";
        } catch (Exception e) {
            log.error("loginOut fail, sessionId:{}",id, e);
            return "/login";
        }
    }
}
