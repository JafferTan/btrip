package com.jaffer.btrip.configuration;

import com.jaffer.btrip.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePaths = new ArrayList<>();
        excludePaths.add("/login");
        excludePaths.add("/loginJson");
        excludePaths.add("/test");
        excludePaths.add("/error");
        excludePaths.add("/getAuthCodeJson");
        excludePaths.add("/templates/**");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(excludePaths);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
