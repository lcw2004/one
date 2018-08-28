package com.lcw.one.util.filter;

import com.lcw.one.util.utils.http.CookieUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用现有的JSessionId覆盖系统自己生成的JSessionId，否则会导致开发的时候启动不同的服务，生成不同的JSessionId，导致系统退出
 */
public class OverWriteJSessionIdFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        chain.doFilter(request, response);
        CookieUtils.overWriteJSessionId(request, response);
    }

}
