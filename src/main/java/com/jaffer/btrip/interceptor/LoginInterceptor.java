package com.jaffer.btrip.interceptor;

import com.alibaba.fastjson.JSON;
import com.jaffer.btrip.beans.entity.LoginInfo;
import com.jaffer.btrip.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * 登陆拦截器
 */

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private static final String LOGIN_SESSION = "LOGIN_SESSION_%s";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            HttpSession session = request.getSession();
            Jedis jedis = RedisUtils.getJedis();
            String sessionRedis = String.format(LOGIN_SESSION, session.getId());
            assert jedis != null;
            boolean exists = jedis.exists(sessionRedis);
            if (BooleanUtils.isFalse(exists)) {
                response.sendRedirect("/login");
                return false;
            }
            LoginInfo loginInfo = JSON.parseObject(jedis.get(sessionRedis), LoginInfo.class);
            if (!Objects.isNull(loginInfo)) {
                return true;
            }
        } catch (Exception e) {
            log.error("LoginInterceptor occurred exception, sessionId:{}", request.getSession().getId(), e);
        }
        response.sendRedirect("/login");
        return false;
    }
}
