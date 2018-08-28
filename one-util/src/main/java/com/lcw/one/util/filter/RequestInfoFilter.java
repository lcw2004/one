package com.lcw.one.util.filter;

import com.lcw.one.util.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求信息过滤器
 */
public class RequestInfoFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(HttpCacheFilter.class);
    private static final List<String> IGNORE_LIST = new ArrayList<>();

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
        IGNORE_LIST.add("js");
        IGNORE_LIST.add("css");
        IGNORE_LIST.add("png");
        IGNORE_LIST.add("ico");
        IGNORE_LIST.add("gif");
        IGNORE_LIST.add("jpg");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        try {
            String uri = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
            if (!isIgnore(uri)) {
                logger.info("==================== RequestInfoFilter Start ====================");
                logger.info(request.getMethod() + " : " + uri);
                logger.info(RequestUtils.getHeaderMap(request).toString());
                logger.info(RequestUtils.getParameterMapSafe(request).toString());

                long startTime = System.currentTimeMillis();
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

    private static boolean isIgnore(String url) {
        boolean ignore = false;
        int index = url.lastIndexOf(".");
        if (index > 0) {
            String suffix = url.substring(index + 1, url.length());
            if (IGNORE_LIST.contains(suffix)) {
                ignore = true;
            }
        }
        return ignore;
    }
}
