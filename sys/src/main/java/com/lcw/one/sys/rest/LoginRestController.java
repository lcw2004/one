package com.lcw.one.sys.rest;

import com.google.common.collect.Maps;
import com.lcw.one.common.util.CacheUtils;
import com.lcw.one.sys.advice.ResponseMessage;
import com.lcw.one.sys.advice.Result;
import com.lcw.one.sys.security.CaptchaException;
import com.lcw.one.sys.security.UsernamePasswordToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "${restPath}/")
public class LoginRestController {

    /**
     * 10分钟内最大错误次数
     */
    private static final int MAX_LOGIN_ERROR_COUNT = 3;

    private static final Logger logger = LoggerFactory.getLogger(LogRestController.class);

    @PostMapping(value = "/login")
    public ResponseMessage loginRest(String username, String password, String validateCode) {
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password.toCharArray());
            subject.login(usernamePasswordToken);
        } catch (CaptchaException e) {
            logger.info("验证码验证失败");
            return Result.error("0002", "验证码验证失败");
        } catch (UnknownAccountException e) {
            increaseLoginErrorCount(username);
            logger.info("用户[{}]身份验证失败", username);
            return Result.error("0001", "您输入的帐号或密码有误");
        } catch (IncorrectCredentialsException e) {
            increaseLoginErrorCount(username);
            logger.info("用户[{}]密码验证失败", username);
            return Result.error("0001", "您输入的帐号或密码有误");
        } catch (AuthenticationException e) {
            // 记录日志，有未处理的验证失败
            logger.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }

        return Result.success();
    }

    /**
     * TODO REST 是否是验证码登录
     *
     * @param useruame 用户名
     * @param isFail   计数加1
     * @param clean    计数清零
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean) {
        Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils.get("loginFailMap");
        if (loginFailMap == null) {
            loginFailMap = Maps.newHashMap();
            CacheUtils.put("loginFailMap", loginFailMap);
        }
        Integer loginFailNum = loginFailMap.get(useruame);
        if (loginFailNum == null) {
            loginFailNum = 0;
        }
        if (isFail) {
            loginFailNum++;
            loginFailMap.put(useruame, loginFailNum);
        }
        if (clean) {
            loginFailMap.remove(useruame);
        }
        return loginFailNum >= 3;
    }

    /**
     * 判断是否需要验证验证码
     *
     * @param userName
     * @return
     */
    public static boolean isNeedValidCode(String userName) {
        Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils.get("loginFailMap");
        if (loginFailMap == null) {
            loginFailMap = Maps.newHashMap();
            CacheUtils.put("loginFailMap", loginFailMap);
        }

        Integer loginFailNum = loginFailMap.get(userName);
        if (loginFailNum == null) {
            loginFailNum = 0;
        }

        return loginFailNum >= MAX_LOGIN_ERROR_COUNT;
    }

    /**
     * 登录失败次数增加一次
     *
     * @param userName
     * @return
     */
    public static void increaseLoginErrorCount(String userName) {
         Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils.get("loginFailMap");
        if (loginFailMap == null) {
            loginFailMap = Maps.newHashMap();
            CacheUtils.put("loginFailMap", loginFailMap);
        }
        Integer loginFailNum = loginFailMap.get(userName);
        if (loginFailNum == null) {
            loginFailNum = 0;
        }

        loginFailNum++;
        loginFailMap.put(userName, loginFailNum);
    }


}
