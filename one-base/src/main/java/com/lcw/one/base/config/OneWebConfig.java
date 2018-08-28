package com.lcw.one.base.config;

import com.lcw.one.util.http.Settings;
import com.lcw.one.sys.entity.SystemInfo;
import com.lcw.one.util.filter.HttpCacheFilter;
import com.lcw.one.util.filter.OverWriteJSessionIdFilter;
import com.lcw.one.util.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.servlet.MultipartConfigElement;

/**
 * Web配置，用于注入过滤器，拦截器等
 */
@Configuration
public class OneWebConfig {

    @Autowired
    private Environment environment;

    @Value("${one.file.temp}")
    private String tempPath;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        FileUtil.mkdir(tempPath);
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(tempPath);
        return factory.createMultipartConfig();
    }

    @Bean
    public FilterRegistrationBean httpCacheFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpCacheFilter());
        registration.addUrlPatterns("/*");
        registration.setName("httpCacheFilter");
        registration.addInitParameter("maxAge", String.valueOf(Settings.HTTP_CACHE_MAX_AGE));
        registration.setOrder(8);
        return registration;
    }

    @Bean
    public FilterRegistrationBean jSessionIdFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new OverWriteJSessionIdFilter());
        registration.addUrlPatterns("/*");
        registration.setName("jSessionIdFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public SystemInfo systemInfo() {
        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setFullName(environment.getProperty("one.application.name"));
        systemInfo.setShortName(environment.getProperty("one.application.shortName"));
        systemInfo.setVersion(environment.getProperty("one.application.version"));
        systemInfo.setPowerByName(environment.getProperty("one.application.powerByName"));
        systemInfo.setPowerByLink(environment.getProperty("one.application.powerByLink"));
        return systemInfo;
    }
}
