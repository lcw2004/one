package com.lcw.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginControll {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "modules/sys/sysLogin";
    }
}
