package com.lcw.one.base.service;

import com.lcw.one.base.config.GlobalConfig;
import com.lcw.one.user.constant.VerifyCodeTypeEnum;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.RandomUtils;
import com.lcw.one.util.utils.RedisUtil;
import com.lcw.one.util.utils.StringUtils;
import com.lcw.one.util.utils.http.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VerifyCodeService {

    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeService.class);

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 发送验证
     * @param cookieValue
     * @param verifyCodeType
     * @return
     */
    public String sendVerifyCode(String cookieValue, VerifyCodeTypeEnum verifyCodeType) {
        // 生成验证码
        String validCode = RandomUtils.randomNumberString(6);
        logger.info("Send ValidCode: {}", validCode);

        // 获取Cookie并缓存验证码
        redisUtil.set(cookieValue + "_" + verifyCodeType.getCode(), validCode, GlobalConfig.getRegistryCodeExpireTime());
        return validCode;
    }

    /**
     * 验证验证码是否失效
     * @param cookieValue
     * @param verifyCodeType 验证码类型
     * @param verifyCode 用户输入的验证码
     */
    public void  validVerifyCode(String cookieValue, VerifyCodeTypeEnum verifyCodeType, String verifyCode) {
        if (StringUtils.isEmpty(verifyCode)) {
            throw new OneBaseException("0003", "请输入验证码");
        }
        String validCodeInDB = redisUtil.get(cookieValue + "_" + verifyCodeType.getCode());
        if (StringUtils.isEmpty(validCodeInDB)) {
            throw new OneBaseException("0003", "验证码已经失效");
        }
        if (!verifyCode.equals(validCodeInDB)) {
            throw new OneBaseException("0003", "验证码验证不通过");
        }
    }


}
