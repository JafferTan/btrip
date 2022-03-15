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

    private static final String LOGIN_SESSION = "LOGIN_SESSION_%s";

    //一个session只有60s的有效期
    private static final Integer SESSION_EXPIRE_TIME = 60 * 60;

    public static String getString(HttpServletRequest request , String str) {
        return (String) request.getSession().getAttribute(str);
    }

    public static LoginInfo getLoginInfo() {
//        String sessionId = Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getSessionId();
//        String sessionKey = String.format(LOGIN_SESSION,sessionId);
//
//        Jedis jedis = RedisUtils.getJedis();
//        assert jedis != null;
//        String s = jedis.get(sessionKey);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
        return loginInfo;
    }

    public static void setLoginInfo(LoginInfo loginInfo) {
//        String sessionId = Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getSessionId();
//        String sessionKey = String.format(LOGIN_SESSION,sessionId);
//        Jedis jedis = RedisUtils.getJedis();
//        assert jedis != null;
//        jedis.setex(sessionKey,SESSION_EXPIRE_TIME, JSON.toJSONString(loginInfo));

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
