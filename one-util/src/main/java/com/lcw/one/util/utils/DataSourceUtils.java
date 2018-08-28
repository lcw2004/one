package com.lcw.one.util.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

public class DataSourceUtils {

    /**
     * 创建数据源（Druid连接池）
     * @param env
     * @return
     */
    public static DataSource createDruidDataSource(Environment env) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("spring.datasource.druid.initial-size")));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("spring.datasource.druid.max-active")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("spring.datasource.druid.min-idle")));
        dataSource.setMaxWait(Integer.parseInt(env.getProperty("spring.datasource.druid.max-wait")));
        dataSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(env.getProperty("spring.datasource.druid.time-between-eviction-runs-millis")));
        dataSource.setMinEvictableIdleTimeMillis(Integer.parseInt(env.getProperty("spring.datasource.druid.min-evictable-idle-time-millis")));
        dataSource.setValidationQuery(env.getProperty("spring.datasource.druid.validation-query"));
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        dataSource.setTestOnReturn(false);
        return dataSource;
    }

}
