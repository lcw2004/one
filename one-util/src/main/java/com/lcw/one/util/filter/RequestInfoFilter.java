package com.lcw.one.util.filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class RequestInfoFilter implements Filter {
    private static final Log logger = LogFactory.getLog(RequestInfoFilter.class);
    private static final List<String> ignoreList = new ArrayList<>();

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        ignoreList.add("js");
        ignoreList.add("css");
        ignoreList.add("png");
        ignoreList.add("ico");
        ignoreList.add("gif");
        ignoreList.add("jpg");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        try {
            long startTime = System.currentTimeMillis();
            String uri = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
            if(!isIgnore(uri)) {
                logger.info("==================== RequestInfoFilter Start ====================");
                logger.info(request.getMethod() + " : " + uri);

                logHeaders(request);
                logParams(request);

                chain.doFilter(request, response);

                long endTime = System.currentTimeMillis();
                logger.info("耗时：" + (endTime - startTime) + " ms");
                logger.info("==================== RequestInfoFilter End ====================");
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logHeaders(HttpServletRequest request) {
        Map<String, String> headerMap = new HashMap<>();

        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headName = headers.nextElement();
            if (headName != null && !"".equals(headName)) {
                headerMap.put(headName, request.getHeader(headName));
            }
        }
        headerMap.put("RemoteHost", request.getRemoteHost() + ":" + request.getRemotePort());

        logger.info(headerMap);
    }

    private void logParams(HttpServletRequest request) {
        Map<String, String> maps = new HashMap<>();

        Enumeration<String> keys = request.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (StringUtils.isNotEmpty(key)) {
                String values = request.getParameter(key);
                maps.put(key, values);
            }
        }

        logger.info(maps.toString());
    }

    private void logAttr(HttpServletRequest request) {
        Map<String, Object> maps = new HashMap<>();

        Enumeration<String> keys = request.getAttributeNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (StringUtils.isNotEmpty(key)) {
                Object values = request.getAttribute(key);
                maps.put(key, values);
            }
        }

        logger.info(maps.toString());
    }

    private static final boolean isIgnore(String url) {
        boolean ignore = false;
        int index = url.lastIndexOf(".");
        if(index > 0) {
            String subfix = url.substring(index + 1, url.length());
            if(ignoreList.contains(subfix)) {
                ignore = true;
            }
        }
        return ignore;
    }
}
