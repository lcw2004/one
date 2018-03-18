package com.lcw.one.base.config;

import com.lcw.one.util.utils.SwaggerUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger API配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return SwaggerUtils.initDocket();
    }

}
