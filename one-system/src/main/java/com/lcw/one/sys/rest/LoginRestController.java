package com.lcw.one.sys.rest;

import com.google.common.collect.Maps;
import com.lcw.one.common.util.CacheUtils;
import com.lcw.one.common.util.validatecode.IVerifyCodeGen;
import com.lcw.one.common.util.validatecode.SimpleCharVerifyCodeGenImpl;
import com.lcw.one.common.util.validatecode.VerifyCode;
import com.lcw.one.sys.advice.ResponseMessage;
import com.lcw.one.sys.advice.Result;
import com.lcw.one.sys.entity.User;
import com.lcw.one.sys.security.CaptchaException;
import com.lcw.one.sys.security.UsernamePasswordToken;
import com.lcw.one.sys.utils.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping(value = "${restPath}/")
public class LoginRestController {

    private static final Logger logger = LoggerFactory.getLogger(LogRestController.class);

    /**
     * 10分钟内最大错误次数
     */
    private static final int MAX_LOGIN_ERROR_COUNT = 3;

    /**
     * 登录接口
     * @param username
     * @param password
     * @param isRememberMe
     * @param verifyCode
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseMessage loginRest(@Valid @NotNull(message = "请输入用户名") String username,
                                     @Valid @NotNull(message = "请输入密码") String password,
                                     @RequestParam(value = "isRememberMe", defaultValue = "false") Boolean isRememberMe,
                                     String verifyCode) {
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password.toCharArray(), verifyCode);
            subject.login(usernamePasswordToken);
        } catch (CaptchaException e) {
            logger.info("验证码验证失败");
            return Result.error("0002", "您输入的验证码不正确");
        } catch (UnknownAccountException e) {
            increaseLoginErrorCount(username);
            logger.info("用户[{}]身份验证失败", username);
            boolean isNeedValidCode = isNeedValidCode(username);
            return Result.error("0001", "您输入的帐号或密码有误", isNeedValidCode);
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
     * 退出登录
     * @return
     */
    @GetMapping("/logout")
    @ResponseBody
    public ResponseMessage logout() {
        UserUtils.logout();
        return Result.success();
    }

    /**
     * 登录成功之后获取当前登录用户信息的接口
     * @return
     */
    @GetMapping("/userInfo")
    @ResponseBody
    public ResponseMessage<User> userInfo(HttpServletResponse  response) {
        User user = UserUtils.getUser();
        if(user != null) {
            return Result.success(user);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return Result.error();
        }
    }

    /**
     * 生成验证码的接口
     * @param request
     * @param response
     */
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            request.getSession().setAttribute("VerifyCode", verifyCode.getCode());
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

    /**
     * TODO REST 是否是验证码登录
     *
     * @param useruame 用户名
     * @param isFail   计数加1
     * @param clean    计数清零
     * @return
     */
    @SuppressWarnings("unchecked")
    @Deprecated
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
