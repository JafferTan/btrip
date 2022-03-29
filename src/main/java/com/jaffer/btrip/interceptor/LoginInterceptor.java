package com.jaffer.btrip.interceptor;

import com.jaffer.btrip.beans.entity.LoginInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 登陆拦截器
 */

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private static final String LOGIN_SESSION = "LOGIN_SESSION_%s";


    /**
     * 登陆态拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            /**
             * 去session中取登陆态
             */
            LoginInfo loginInfo = (LoginInfo) request.getSession().getAttribute("loginInfo");
            if (Objects.nonNull(loginInfo)) {
                return true;
            }
        } catch (Exception e) {
            log.error("LoginInterceptor occurred exception, sessionId:{}", request.getSession().getId(), e);
        }
        request.getRequestDispatcher("/login").forward(request,response);
        return false;
    }
}
