package com.lcw.one.config;

import com.lcw.one.common.config.Global;
import com.lcw.one.common.filter.RequestInfoFilter;
import com.lcw.one.modules.sys.security.FormAuthenticationFilter;
import com.lcw.one.modules.sys.security.SystemAuthorizingRealm;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.PooledDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class AppConfiguration {

    @Bean
    public FilterRegistrationBean requestInfoFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestInfoFilter());
        registration.addUrlPatterns("/*");
        registration.setName("RequestInfoFilter");
        return registration;
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(localSessionFactoryBean());
        return hibernateTransactionManager;
    }

    @Bean(name = "sessionFactory")
    public SessionFactory localSessionFactoryBean() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        properties.setProperty("hibernate.cache.use_query_cache", "true");
        properties.setProperty("hibernate.cache.use_second_level_cache", "true");

        LocalSessionFactoryBuilder localSessionFactoryBean = new LocalSessionFactoryBuilder(dataSource());
        localSessionFactoryBean.scanPackages("com.lcw");
        localSessionFactoryBean.addProperties(properties);
        localSessionFactoryBean.setNamingStrategy(new ImprovedNamingStrategy());

        SessionFactory sessionFactory = localSessionFactoryBean.buildSessionFactory();
        return sessionFactory;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setAcquireIncrement(3);
        cpds.setInitialPoolSize(10);
        cpds.setMinPoolSize(3);
        cpds.setMaxPoolSize(20);
        cpds.setMaxIdleTime(600);
        cpds.setIdleConnectionTestPeriod(500);
        cpds.setMaxStatements(0);
        cpds.setMaxIdleTimeExcessConnections(5);
        cpds.setMaxStatementsPerConnection(100);
        cpds.setStatementCacheNumDeferredCloseThreads(1);

        try {
            cpds.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/jeesite?useUnicode=true&characterEncoding=utf-8");
        cpds.setUser("root");
        cpds.setPassword("one");
        return cpds;
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Value("${ehcache.configFile}")
    private String ehcacheConfigPath;

    @Bean(name = "cacheManager")
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ClassPathResource classPathResource = new ClassPathResource("cache/ehcache-local.xml");
        ehCacheManagerFactoryBean.setConfigLocation(classPathResource);
        return ehCacheManagerFactoryBean;
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
        Map<String, javax.servlet.Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new FormAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/api/**", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/userfiles/**", "anon");
        filterChainDefinitionMap.put(Global.getAdminPath() + "/login", "authc");
        filterChainDefinitionMap.put(Global.getAdminPath() + "/logout", "logout");
        filterChainDefinitionMap.put(Global.getAdminPath() + "/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
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
