package com.jaffer.btrip.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class IndexController {
    @GetMapping("/index")
    public String login(){
        return "index";
    }

    @GetMapping("")
    public String defaultController(){
        return "index";
    }

    @RequestMapping(value = "test", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String test(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("queryString", request.getQueryString());
        map.put("params", request.getQueryString());
        return JSON.toJSONString(map);
    }
}
