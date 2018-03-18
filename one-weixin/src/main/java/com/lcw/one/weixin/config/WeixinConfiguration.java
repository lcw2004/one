package com.lcw.one.weixin.config;

import com.foxinmy.weixin4j.cache.RedisCacheStorager;
import com.foxinmy.weixin4j.model.WeixinAccount;
import com.foxinmy.weixin4j.mp.token.WeixinTokenCreator;
import com.foxinmy.weixin4j.token.TokenManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeixinConfiguration {

    @Value("${one.weixin.appId}")
    private String appId;

    @Value("${one.weixin.appSecret}")
    private String appSecret;

    @Bean
    public WeixinAccount weixinAccount () {
        return new WeixinAccount(appId, appSecret);
    }

    @Bean
    public RedisCacheStorager redisCacheStorager() {
        return new RedisCacheStorager();
    }

    @Bean
    public WeixinTokenCreator weixinTokenCreator() {
        return new WeixinTokenCreator(appId, appSecret);
    }

    @Bean
    public TokenManager tokenManager() {
        return new TokenManager(weixinTokenCreator(), redisCacheStorager());
    }
}
