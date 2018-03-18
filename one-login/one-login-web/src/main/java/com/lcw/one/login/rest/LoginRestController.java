package com.lcw.one.login.rest;

import com.lcw.one.login.security.OneUsernamePasswordToken;
import com.lcw.one.login.security.exception.CaptchaException;
import com.lcw.one.login.security.validatecode.IVerifyCodeGen;
import com.lcw.one.login.security.validatecode.SimpleCharVerifyCodeGenImpl;
import com.lcw.one.login.security.validatecode.VerifyCode;
import com.lcw.one.login.service.WebLoginService;
import com.lcw.one.login.util.UserUtils;
import com.lcw.one.user.constant.VerifyCodeTypeEnum;
import com.lcw.one.user.service.UserInfoEOService;
import com.lcw.one.util.constant.GlobalConfig;
import com.lcw.one.util.http.CookieUtils;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.RedisUtil;
import com.lcw.one.util.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Validated
@Controller
@RequestMapping(value = "${restPath}/")
@Api(description = "登录接口")
public class LoginRestController {

    private static final Logger logger = LoggerFactory.getLogger(LoginRestController.class);

    @Autowired
    private UserInfoEOService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WebLoginService webLoginService;

    /**
     * 验证码
     *
     * @param request
     * @param response
     */
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);

            String cookieValue = CookieUtils.getCookieValueIfNullThenSetCookie(request, response);
            redisUtil.set(cookieValue + "_" + VerifyCodeTypeEnum.LOGIN.getCode(), verifyCode.getCode(), GlobalConfig.getRegistryCodeExpireTime());

            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "登录")
    @GetMapping("/login")
    @ResponseBody
    public ResponseMessage loginRest(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam @NotNull(message = "请输入用户名") String username,
                                     @RequestParam @NotNull(message = "请输入密码") String password,
                                     @RequestParam(value = "isRememberMe", defaultValue = "false") Boolean isRememberMe,
                                     String verifyCode) {
        String token = "";
        try {
            OneUsernamePasswordToken usernamePasswordToken = new OneUsernamePasswordToken(username, password.toCharArray(), verifyCode);

            // 调用Shiro登录
            Subject subject = SecurityUtils.getSubject();
            subject.login(usernamePasswordToken);

            token = webLoginService.login(request);
            request.getSession().setAttribute("token", token);
            CookieUtils.setCookie(request, response, token);
        } catch (CaptchaException e) {
            logger.info("验证码验证失败");
            return Result.error("0002", "您输入的验证码不正确");
        } catch (UnknownAccountException e) {
            UserUtils.increaseLoginErrorCount(username);
            logger.info("用户[{}]身份验证失败", username);
            boolean isNeedValidCode = UserUtils.isNeedValidCode(username);
            return Result.error("0001", "您输入的帐号或密码有误", isNeedValidCode);
        } catch (IncorrectCredentialsException e) {
            UserUtils.increaseLoginErrorCount(username);
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

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("authCode", token);
        return Result.success(resultMap);
    }

    @ApiOperation(value = "退出登录")
    @GetMapping("/logout")
    @ResponseBody
    public ResponseMessage logout(HttpServletRequest request, HttpServletResponse response) {
        webLoginService.logout(request, response);
        return Result.success();
    }

    @ApiOperation(value = "获取Token")
    @GetMapping(value = "/token")
    @ResponseBody
    public ResponseMessage token(HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("token");
        if (StringUtils.isNotEmpty(token)) {
            return Result.error("获取Token失败");
        }
        return Result.success(token);
    }

}
