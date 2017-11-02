package com.lcw.one.util.filter;

import com.lcw.one.util.http.CookieUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetCookieFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        CookieUtils.getCookieValueIfNullThenSetCookie(request, response);
        chain.doFilter(request, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}