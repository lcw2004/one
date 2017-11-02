package com.lcw.one.login.config;

import com.lcw.one.login.security.FormAuthenticationFilter;
import com.lcw.one.login.security.filterChain.SimpleFilterChainDefinitionsService;
import com.lcw.one.login.security.SystemAuthorizingRealm;
import com.lcw.one.util.constant.GlobalConfig;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration implements EnvironmentAware {

    @Override
    public void setEnvironment(Environment env) {
        // 用Autowire的方式注入不了restPath
        GlobalConfig.setRestApiPath(env.getProperty("restPath"));
    }

    @Bean(name = "ehCacheManagerFactoryBean")
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ClassPathResource classPathResource = new ClassPathResource("cache/ehcache-local.xml");
        ehCacheManagerFactoryBean.setConfigLocation(classPathResource);
        return ehCacheManagerFactoryBean;
    }

    @Bean(name = "shiroCacheManager")
    @DependsOn({"ehCacheManagerFactoryBean"})
    public EhCacheManager shiroCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
        return ehCacheManager;
    }
	
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        shiroFilterFactoryBean.setSuccessUrl("/index.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/login.html");
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new FormAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SimpleFilterChainDefinitionsService abstractFilterChainDefinitionsService() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put(GlobalConfig.getRestApiPath() + "/login", "anon");
        filterChainDefinitionMap.put(GlobalConfig.getRestApiPath() + "/verifyCode", "anon");
        filterChainDefinitionMap.put(GlobalConfig.getRestApiPath() + "/user/supplierRegistry/*", "anon");
        filterChainDefinitionMap.put(GlobalConfig.getRestApiPath() + "/account/*", "anon");
        filterChainDefinitionMap.put(GlobalConfig.getRestApiPath() + "/**", "user");
        filterChainDefinitionMap.put("/account.html", "anon");
        filterChainDefinitionMap.put("/index.html", "user");
        filterChainDefinitionMap.put("/", "user");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/**", "user");

        SimpleFilterChainDefinitionsService definitionsService = new SimpleFilterChainDefinitionsService();
        definitionsService.setShiroFilterFactoryBean(shiroFilterFactoryBean());
        definitionsService.setDefaultFilterChainDefinitionMap(filterChainDefinitionMap);
        definitionsService.initPermission();
        definitionsService.updatePermission();
        return definitionsService;
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(systemAuthorizingRealm());
        defaultWebSecurityManager.setCacheManager(shiroCacheManager());

        SecurityUtils.setSecurityManager(defaultWebSecurityManager);

        return defaultWebSecurityManager;
    }

    @Bean
    public SystemAuthorizingRealm systemAuthorizingRealm() {
        return new SystemAuthorizingRealm();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

}
