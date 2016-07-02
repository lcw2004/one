package com.lcw.one.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.PooledDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
public class AppConfiguration {

//    <bean id="transactionManager" class="org.springframework.orm.hibernate4.HibernateTransactionManager">
//    <property name="sessionFactory" ref="sessionFactory" />
//    </bean>

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
        properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        properties.setProperty("hibernate.cache.use_query_cache", "true");
        properties.setProperty("hibernate.cache.use_second_level_cache", "true");

        LocalSessionFactoryBuilder localSessionFactoryBean = new LocalSessionFactoryBuilder(dataSource());
        localSessionFactoryBean.scanPackages("com.lcw");
        localSessionFactoryBean.addProperties(properties);
        localSessionFactoryBean.setNamingStrategy(new ImprovedNamingStrategy());

        SessionFactory sessionFactory =  localSessionFactoryBean.buildSessionFactory();
        return sessionFactory;
    }

    @Bean(name="dataSource")
    public PooledDataSource dataSource() {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setAcquireIncrement(3);
        cpds.setInitialPoolSize(10);
        cpds.setMinPoolSize(3);
        cpds.setMaxPoolSize(500);
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
        cpds.setPassword("root");
        return cpds;
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

//    <!-- 缓存配置 -->
//    <bean id="cacheManager" class="org.springframework.cache.ehcache.EhCacheManagerFactoryBean">
//    <property name="configLocation" value="classpath:${ehcache.configFile}" />
//    </bean>
    @Value("${ehcache.configFile}")
    private String ehcacheConfigPath;
    @Bean(name = "cacheManager")
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ClassPathResource classPathResource = new ClassPathResource(ehcacheConfigPath);
        ehCacheManagerFactoryBean.setConfigLocation(classPathResource);
        return ehCacheManagerFactoryBean;
    }
}
