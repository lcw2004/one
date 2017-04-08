package com.lcw.one.workflow.config;

import com.lcw.one.util.utils.DataSourceUtils;
import org.activiti.engine.IdentityService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class AppConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        return DataSourceUtils.createDruidDataSource(environment);
    }

    @Bean
    InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {
        return new InitializingBean() {
            public void afterPropertiesSet() throws Exception {

            }
        };
    }
}
