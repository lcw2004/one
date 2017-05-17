package com.lcw.one.util.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 2017-05-04.
 * @auth Licw
 */
public class RequestUtils {

    public static final String LOGIN_USER = "LOGIN_USER";
    public static final String LOGIN_USER_ID = "LOGIN_USER_ID";
    public static final String LOGIN_ROLE_ID = "LOGIN_ROLE_ID";

    /**
     * 获取客户端IP地址
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (StringUtils.isEmpty(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

    public static String getLoginUserId(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(LOGIN_USER_ID);
    }

    public static String getLoginRoleId(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(LOGIN_ROLE_ID);
    }

}
