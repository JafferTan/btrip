package com.jaffer.btrip.util;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.beans.entity.LoginInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class BtripSessionUtils {

    public static LoginInfo getLoginInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
        return loginInfo;
    }

    public static void setLoginInfo(LoginInfo loginInfo) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("loginInfo", loginInfo);
    }

    public static HttpSession getSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        return session;
    }
}
