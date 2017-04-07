package config;

import com.lcw.one.common.config.Global;
import com.lcw.one.sys.security.FormAuthenticationFilter;
import com.lcw.one.sys.security.SystemAuthorizingRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

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

    @Bean(name = "cacheManager")
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ClassPathResource classPathResource = new ClassPathResource("cache/ehcache-local.xml");
        ehCacheManagerFactoryBean.setConfigLocation(classPathResource);
        return ehCacheManagerFactoryBean;
    }


    @Bean(name = "shiroCacheManager")
    @DependsOn({"cacheManager"})
    public EhCacheManager shiroCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
        return ehCacheManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl(Global.getAdminPath() + "/login");
        shiroFilterFactoryBean.setSuccessUrl(Global.getAdminPath());
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new FormAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put(Global.getRestApiPath() + "/login", "anon");
        filterChainDefinitionMap.put(Global.getRestApiPath() + "/logout", "anon");
        filterChainDefinitionMap.put(Global.getRestApiPath() + "/verifyCode", "anon");
        filterChainDefinitionMap.put(Global.getRestApiPath() + "/userInfo", "anon");
        filterChainDefinitionMap.put("/api/**", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/userfiles/**", "anon");
        filterChainDefinitionMap.put(Global.getAdminPath() + "/login", "authc");
        filterChainDefinitionMap.put(Global.getAdminPath() + "/logout", "logout");
        filterChainDefinitionMap.put(Global.getAdminPath() + "/**", "workflow");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
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
