/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.modules.sys.interceptor;

import com.lcw.one.common.config.Global;
import com.lcw.one.common.service.BaseService;
import com.lcw.one.common.utils.SpringContextHolder;
import com.lcw.one.common.utils.StringUtils;
import com.lcw.one.modules.sys.entity.Log;
import com.lcw.one.modules.sys.entity.User;
import com.lcw.one.modules.sys.service.LogService;
import com.lcw.one.modules.sys.utils.UserUtils;
import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
 * 日志拦截器，记录所有REST Api的访问情况
 * @author Licw
 */
@Transactional
public class LogInterceptor extends BaseService implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class.getName());
    private static LogService logService = SpringContextHolder.getBean(LogService.class);
    private long startTime;
    private long endTime;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        startTime = System.currentTimeMillis();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    @Transactional(readOnly = false)
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
        logger.info("访问耗时:" + (endTime - startTime) + "\tURL:" + request.getRequestURI());
    }

    /**
     * 将参数拼为一个字符串
     * @param request
     * @return
     */
    private String buildQueryString(HttpServletRequest request) {
        StringBuilder params = new StringBuilder();
        int index = 0;
        for (Object param : request.getParameterMap().keySet()) {
            params.append((index++ == 0 ? "" : "&") + param + "=");
            params.append(StringUtils.abbr(StringUtils.endsWithIgnoreCase((String) param, "password") ? "" : request.getParameter((String) param), 100));
        }
        return params.toString();
    }

}
