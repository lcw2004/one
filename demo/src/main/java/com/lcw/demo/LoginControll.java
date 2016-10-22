package com.lcw.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginControll {

    @RequestMapping("/")
    public String welcome() {
        return "welcome";
    }


}
