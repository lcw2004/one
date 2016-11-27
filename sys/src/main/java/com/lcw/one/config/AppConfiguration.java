package com.lcw.one.config;

import com.lcw.one.common.filter.RequestInfoFilter;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
public class AppConfiguration {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    @Value("${spring.datasource.driver-class-name}")
    private String datasourceDriverClass;

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
            cpds.setDriverClass(datasourceDriverClass);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl(datasourceUrl);
        cpds.setUser(datasourceUsername);
        cpds.setPassword(datasourcePassword);
        return cpds;
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

    @Bean(name = "validator")
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public FilterRegistrationBean requestInfoFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestInfoFilter());
        registration.addUrlPatterns("/*");
        registration.setName("RequestInfoFilter");
        return registration;
    }

}
