/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.sys.interceptor;

import com.lcw.one.common.config.Global;
import com.lcw.one.common.util.StringUtils;
import com.lcw.one.sys.entity.Log;
import com.lcw.one.sys.entity.User;
import com.lcw.one.sys.service.LogService;
import com.lcw.one.sys.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
 * 日志拦截器，记录所有REST Api的访问情况
 *
 * @author Licw
 */
@Component
public class LogInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
    private long startTime;
    private long endTime;

    @Autowired
    private LogService logService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        startTime = System.currentTimeMillis();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        endTime = System.currentTimeMillis();

        String requestRri = request.getRequestURI();
        String uriPrefix = request.getContextPath() + Global.getRestApiPath();

        // 判断是否是REST Api的URL
        if (!StringUtils.startsWith(requestRri, uriPrefix)) {
            return;
        }

        // 判断用户是否登录
        User user = UserUtils.getUser();
        if (user == null || user.getId() == null) {
            return;
        }

        // 记录日志
        Log log = new Log();
        log.setId(UUID.randomUUID().toString());
        log.setType(ex == null ? Log.TYPE_ACCESS : Log.TYPE_EXCEPTION);
        log.setCreateBy(user);
        log.setCreateDate(new Date());
        log.setRemoteAddr(StringUtils.getRemoteAddr(request));
        log.setUserAgent(request.getHeader("user-agent"));
        log.setRequestUri(request.getRequestURI());
        log.setMethod(request.getMethod());
        log.setParams(buildQueryString(request));
        log.setException(ex != null ? ex.toString() : "");
        logService.save(log);

        // TODO 需要记录每个URL的访问耗时情况，统计起来以便优化
        logger.info("记录日志 {type: {}, loginName: {}, uri: {}}, ", log.getType(), user.getLoginName(), log.getRequestUri());
        logger.info("访问耗时:{}, URL: {}", endTime - startTime, request.getRequestURI());
    }

    /**
     * 将参数拼为一个字符串
     *
     * @param request
     * @return
     */
    private static String buildQueryString(HttpServletRequest request) {
        StringBuilder params = new StringBuilder();
        int index = 0;
        for (Object param : request.getParameterMap().keySet()) {
            params.append((index++ == 0 ? "" : "&") + param + "=");
            params.append(StringUtils.abbr(StringUtils.endsWithIgnoreCase((String) param, "password") ? "" : request.getParameter((String) param), 100));
        }
        return params.toString();
    }

}
