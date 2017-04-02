package com.lcw.one.sys.rest;

import com.lcw.one.sys.security.UsernamePasswordToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${restPath}/")
public class LoginRestController {

    private static final Logger logger = LoggerFactory.getLogger(LogRestController.class);

    @PostMapping(value = "/login")
    public String loginRest(String username, String password, String validateCode) {
        System.out.println("loginRest");

        Subject subject = SecurityUtils.getSubject();

        try {
            //4、登录，即身份验证
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password.toCharArray());
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            System.out.println("身份验证失败");
            //5、身份验证失败
        }

        System.out.println(subject.isAuthenticated());

        return "modules/sys/sysLogin";
    }

}
