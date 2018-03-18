package com.lcw.one.util.http;

import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.StringUtils;
import com.lcw.one.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CookieUtils {

    private static final String COOKIE_NAME = "OneId";

    /**
     * 设置Cookie值，用传入的Cookie Value
     *
     * @param request
     * @param response
     * @param cookieValue
     * @throws IOException
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieValue) throws IOException {
        setCookie(request, response, COOKIE_NAME, cookieValue);
    }


    /**
     * 设置Cookie值，用传入的Cookie Value
     *
     * @param request
     * @param response
     * @param cookieValue
     * @throws IOException
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue) throws IOException {
        Cookie cookie = getCookie(request, cookieName);
        if (cookie == null) {
            cookie = new Cookie(cookieName, cookieValue);
        }
        cookie.setValue(cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    /**
     * 设置Cookie值，随机生成一个Cookie
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public static String setCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cookieValue = UUID.randomUUID();
        setCookie(request, response, cookieValue);
        return cookieValue;
    }

    /**
     * 获取Cookie，如果为空则设置Cookie，并返回Cookie值
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public static String getCookieValueIfNullThenSetCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cookieValue = getCookieValue(request);
        if (StringUtils.isEmpty(cookieValue)) {
            cookieValue = setCookie(request, response);
        }
        return cookieValue;
    }

    /**
     * 获取Cookie值
     *
     * @param request
     * @return
     */
    public static String getCookieValue(HttpServletRequest request) {
        return getCookieValue(request, COOKIE_NAME);
    }


    /**
     * 获取Cookie值
     *
     * @param request
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie cookie = getCookie(request, cookieName);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    /**
     * 移除Cookie值
     *
     * @param request
     * @param response
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookie(request, COOKIE_NAME);
        cookie.setValue(null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 获取Cookie对象
     *
     * @param request
     * @param cookieName
     * @return
     */
    private static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (CollectionUtils.isEmpty(cookies)) {
            return null;
        }

        Cookie ticket = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                ticket = cookie;
                break;
            }
        }
        return ticket;
    }

    public static String getAuthToken(HttpServletRequest request) {
        // 从Head中获取token，如果取不到则从session中取
        // 有些方法比如下载文件的时候不能在head中设置token，所以暂时将token存到session中
        String token = request.getHeader("authorization");
        if (StringUtils.isEmpty(token)) {
            token = getAuthTokenFromSession(request);
        }
        return token;
    }

    public static String getAuthTokenFromSession(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("token");
    }

    public static void overWriteJSessionId(HttpServletRequest request, HttpServletResponse response) {
        try {
            String JSESSIONID = "JSESSIONID";
            String jSessionId = CookieUtils.getCookieValue(request, JSESSIONID);
            if (StringUtils.isNotEmpty(jSessionId)) {
                CookieUtils.setCookie(request, response, JSESSIONID, jSessionId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
