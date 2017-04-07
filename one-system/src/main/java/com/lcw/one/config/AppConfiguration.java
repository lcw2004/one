package com.lcw.one.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.lcw.one.common.filter.RequestInfoFilter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
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

    @Autowired
    private Environment env;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(datasourceDriverClass);
        dataSource.setUrl(datasourceUrl);
        dataSource.setUsername(datasourceUsername);
        dataSource.setPassword(datasourcePassword);
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("spring.datasource.druid.initialSize")));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("spring.datasource.druid.maxActive")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("spring.datasource.druid.minIdle")));
        dataSource.setMaxWait(Integer.parseInt(env.getProperty("spring.datasource.druid.maxWait")));
        dataSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(env.getProperty("spring.datasource.druid.timeBetweenEvictionRunsMillis")));
        dataSource.setMinEvictableIdleTimeMillis(Integer.parseInt(env.getProperty("spring.datasource.druid.minEvictableIdleTimeMillis")));
        dataSource.setValidationQuery(env.getProperty("spring.datasource.druid.validationQuery"));
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        dataSource.setTestOnReturn(false);
        return dataSource;
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
        registration.setOrder(10);
        return registration;
    }

//    @Bean(name = "cacheManager")
//    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
//        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
//        ClassPathResource classPathResource = new ClassPathResource("cache/ehcache-local.xml");
//        ehCacheManagerFactoryBean.setConfigLocation(classPathResource);
//        return ehCacheManagerFactoryBean;
//    }
}
