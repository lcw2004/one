package com.lcw.one.notify;

import com.lcw.one.util.utils.DataSourceUtils;
import com.lcw.one.util.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.sql.DataSource;

@Configuration
@SpringBootApplication
public class OneNotifyApplication {

    private static final Logger logger = LoggerFactory.getLogger(OneNotifyApplication.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(OneNotifyApplication.class, args);
        SpringContextHolder.setApplicationContext(applicationContext);
        logger.info("Registry ApplicationContext");
    }

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        return DataSourceUtils.createDruidDataSource(environment);
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter (){
        return new ServerEndpointExporter();
    }

}
