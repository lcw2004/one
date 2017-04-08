package com.lcw.one.util;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

public class DataSourceUtils {

    public static DataSource createDruidDataSource(Environment env) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
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
}
