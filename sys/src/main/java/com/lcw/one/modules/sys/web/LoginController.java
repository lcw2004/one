/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.modules.sys.web;

import com.google.common.collect.Maps;
import com.lcw.one.common.config.Global;
import com.lcw.one.common.util.CacheUtils;
import com.lcw.one.common.util.CookieUtils;
import com.lcw.one.common.util.StringUtils;
import com.lcw.one.common.util.validatecode.EnCharValidateCode;
import com.lcw.one.common.util.validatecode.ValidateCodeInterface;
import com.lcw.one.common.web.BaseController;
import com.lcw.one.modules.sys.entity.User;
import com.lcw.one.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * 登录Controller
 * @author ThinkGem
 * @version 2013-5-31
 */
@Controller
public class LoginController extends BaseController {

    /**
     * 管理登录
     */
    @RequestMapping(value = "/validateCode", method = RequestMethod.GET)
    public void validateCode(HttpServletRequest request, HttpServletResponse response) {
        ValidateCodeInterface validateCode = new EnCharValidateCode();
        try {
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            String code = validateCode.generate(80, 28, response.getOutputStream());
            request.getSession().setAttribute("ValidateCode", code);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 首页，http://127.0.0.1:8080/one
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "redirect:" + Global.getAdminPath();
    }

    /**
     * 登录页，http://127.0.0.1:8080/one/a/login
     */
    @RequestMapping(value = "${adminPath}/login", method = RequestMethod.GET)
    public String login() {
        User user = UserUtils.getUser();
        // 如果已经登录，则跳转到管理首页
        if (user.getId() != null) {
            return "redirect:" + Global.getAdminPath();
        }
        return "modules/sys/sysLogin";
    }

    /**
     * TODO 需要重构至rest接口里面
     * 登录失败，真正登录的POST请求由Filter完成
     */
    @RequestMapping(value = "${adminPath}/login", method = RequestMethod.POST)
    public String login(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username, Model model) {
        User user = UserUtils.getUser();
        // 如果已经登录，则跳转到管理首页
        if (user.getId() != null) {
            return "redirect:" + Global.getAdminPath();
        }
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
        model.addAttribute("isValidateCodeLogin", isValidateCodeLogin(username, true, false));
        return "modules/sys/sysLogin";
    }

    /**
     * TODO 需要重构至rest接口里面
     * 登录成功，进入管理首页
     */
    @RequiresUser
    @RequestMapping(value = "${adminPath}")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        User user = UserUtils.getUser();
        // 未登录，则跳转到登录页
        if (user.getId() == null) {
            return "redirect:" + Global.getAdminPath() + "/login";
        }
        // 登录成功后，验证码计算器清零
        isValidateCodeLogin(user.getLoginName(), false, true);
        // 登录成功后，获取上次登录的当前站点ID
        UserUtils.putCache("siteId", CookieUtils.getCookie(request, "siteId"));
        return "modules/sys/sysIndex";
    }

    /**
     * 获取主题方案
     */
    @RequestMapping(value = "/theme/{theme}")
    public String getThemeInCookie(@PathVariable String theme, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNotBlank(theme)) {
            CookieUtils.setCookie(response, "theme", theme);
        } else {
            theme = CookieUtils.getCookie(request, "theme");
        }
        return "redirect:" + request.getParameter("url");
    }

    /**
     * TODO REST 是否是验证码登录
     * @param useruame 用户名
     * @param isFail 计数加1
     * @param clean 计数清零
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean) {
        Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils.get("loginFailMap");
        if (loginFailMap == null) {
            loginFailMap = Maps.newHashMap();
            CacheUtils.put("loginFailMap", loginFailMap);
        }
        Integer loginFailNum = loginFailMap.get(useruame);
        if (loginFailNum == null) {
            loginFailNum = 0;
        }
        if (isFail) {
            loginFailNum++;
            loginFailMap.put(useruame, loginFailNum);
        }
        if (clean) {
            loginFailMap.remove(useruame);
        }
        return loginFailNum >= 3;
    }

}
