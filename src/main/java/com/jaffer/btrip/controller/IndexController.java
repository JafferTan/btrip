package com.jaffer.btrip.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
