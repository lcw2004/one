package com.lcw.one.main.config;

import com.lcw.one.util.filter.HttpCacheFilter;
import com.lcw.one.util.filter.RequestInfoFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Web配置，用于注入过滤器，拦截器等
 */
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean requestInfoFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestInfoFilter());
        registration.addUrlPatterns("/*");
        registration.setName("RequestInfoFilter");
        registration.setOrder(10);
        return registration;
    }

    @Bean
    public FilterRegistrationBean httpCacheFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpCacheFilter());
        registration.addUrlPatterns("/*");
        registration.setName("httpCacheFilter");
        registration.addInitParameter("maxAge", String.valueOf(60 * 60 * 24 * 7));
        registration.setOrder(8);
        return registration;
    }
}
