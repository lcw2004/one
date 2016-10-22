package com.lcw.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserControll {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getUser() {
        return "hello12ddddd2";
    }
    @RequestMapping(value = "/get1", method = RequestMethod.GET)
    public String getUser1() {
        return "hello1222332";
    }
    
    @RequestMapping(value = "/get2", method = RequestMethod.GET)
    public String getUser2() {
        return "getUser2";
    }
    
    @RequestMapping(value = "/get3", method = RequestMethod.GET)
    public String getUser3() {
        return "getUser3";
    }

    @RequestMapping(value = "/get4", method = RequestMethod.GET)
    public String getUser4() {
        return "getUser4";
    }

    @RequestMapping(value = "/get5", method = RequestMethod.GET)
    public String getUser5() {
        return "getUser5";
    }
}
