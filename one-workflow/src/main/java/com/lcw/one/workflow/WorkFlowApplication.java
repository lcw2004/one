package com.lcw.one.workflow;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcw.one.util.exception.LoginInvalidException;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.filter.RequestInfoFilter;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.ResponseMessageCodeEnum;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.utils.DataSourceUtils;
import com.lcw.one.util.utils.SpringContextHolder;
import com.lcw.one.util.utils.SwaggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;

@Configuration
@EnableAsync(proxyTargetClass = true)
@EnableCaching(proxyTargetClass = true)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.lcw.one", repositoryBaseClass = BaseRepositoryImpl.class)
@EntityScan("com.lcw.one")
@ComponentScan("com.lcw.one")
@EnableSwagger2
@SpringBootApplication
@ControllerAdvice
public class WorkFlowApplication {

    private static final Logger logger = LoggerFactory.getLogger(WorkFlowApplication.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(WorkFlowApplication.class, args);
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
    public Docket docket() {
        return SwaggerUtils.initDocket();
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(LoginInvalidException.class)
    @ResponseBody
    public ResponseMessage handlerOneBaseException(LoginInvalidException exception) {
        logger.warn(exception.getMessage(), exception);
        return Result.error(ResponseMessageCodeEnum.RE_LOGIN.getCode(), "登录失效");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(OneBaseException.class)
    @ResponseBody
    public ResponseMessage handlerOneBaseException(OneBaseException exception) {
        logger.warn(exception.getMessage(), exception);
        return Result.error(exception.getErrorCode(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage handlerOneBaseException(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return Result.error(ResponseMessageCodeEnum.ERROR.getCode(), "程序异常，请重试。如果重复出现请联系管理员处理！");
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }

    private void setSerializer(StringRedisTemplate template) {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
    }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.ALWAYS);
        builder.failOnEmptyBeans(false);
        builder.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return builder;
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
}
