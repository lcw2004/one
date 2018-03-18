/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.login.security;

/**
 * 用户和密码（包含验证码）令牌类
 *
 * @author ThinkGem
 * @version 2013-5-19
 */
public class OneUsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

    private String captcha;

    public OneUsernamePasswordToken() {
        super();
    }

    public OneUsernamePasswordToken(String username, char[] password) {
        super(username, password);
    }

    public OneUsernamePasswordToken(String username, char[] password, String captcha) {
        super(username, password);
        this.captcha = captcha;
    }

    public OneUsernamePasswordToken(String username, char[] password, boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}