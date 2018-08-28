package com.lcw.one.util.filter;

import com.lcw.one.util.http.Settings;
import com.lcw.one.util.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Http缓存过滤器
 */
public class HttpCacheFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(HttpCacheFilter.class);

    private long maxAge = Settings.HTTP_CACHE_MAX_AGE;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        chain.doFilter(request, new AddExpiresHeaderResponse(request, response, maxAge));
    }

    @Override
    public void init(FilterConfig config) {
        String maxAgeStr = config.getInitParameter("maxAge");
        if (StringUtils.isNotEmpty(maxAgeStr)) {
            maxAge = Long.valueOf(maxAgeStr);
        }
    }

}