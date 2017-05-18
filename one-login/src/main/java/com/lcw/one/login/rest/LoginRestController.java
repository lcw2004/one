package com.lcw.one.login.rest;

import com.google.common.collect.Maps;
import com.lcw.one.login.security.UsernamePasswordToken;
import com.lcw.one.login.security.exception.CaptchaException;
import com.lcw.one.login.security.validatecode.IVerifyCodeGen;
import com.lcw.one.login.security.validatecode.SimpleCharVerifyCodeGenImpl;
import com.lcw.one.login.security.validatecode.VerifyCode;
import com.lcw.one.login.util.CacheUtils;
import com.lcw.one.login.util.UserUtils;
import com.lcw.one.sys.entity.SysMenuEO;
import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.user.service.UserInfoEOService;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.RequestUtils;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Map;

@Validated
@Controller
@RequestMapping(value = "${restPath}/")
@Api(description = "登录接口")
public class LoginRestController {

    private static final Logger logger = LoggerFactory.getLogger(LoginRestController.class);

    /**
     * 10分钟内最大错误次数
     */
    private static final int MAX_LOGIN_ERROR_COUNT = 3;

    @Autowired
    private UserInfoEOService userService;

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


    @ApiOperation(value = "登录")
    @GetMapping(value = "/login")
    @ResponseBody
    public ResponseMessage loginRest(HttpServletRequest request,
            @RequestParam @NotNull(message = "请输入用户名") String username,
            @RequestParam @NotNull(message = "请输入密码") String password,
            @RequestParam(value = "isRememberMe", defaultValue = "false") Boolean isRememberMe,
            String verifyCode) {
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password.toCharArray(), verifyCode);
            subject.login(usernamePasswordToken);
            UserUtils.flushUserLoginTimeAndIp();

            request.getSession().setAttribute(RequestUtils.LOGIN_USER, UserUtils.getUser());
            request.getSession().setAttribute(RequestUtils.LOGIN_USER_ID, UserUtils.getUserId());
            request.getSession().setAttribute(RequestUtils.LOGIN_ROLE_ID, UserUtils.getRoleIds());
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

    @ApiOperation(value = "退出登录")
    @GetMapping("/logout")
    @ResponseBody
    public ResponseMessage logout() {
        UserUtils.logout();
        return Result.success();
    }


    /**
     * 登录成功之后获取当前登录用户信息的接口
     *
     * @return
     */
    @ApiOperation(value = "获取登录用户信息")
    @GetMapping("/userInfo")
    @ResponseBody
    public ResponseMessage<UserInfoEO> userInfo(HttpServletResponse response) {
        UserInfoEO user = UserUtils.getUser();
        if (user != null) {
            return Result.success(user);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return Result.error();
        }
    }

    @ApiOperation(value = "获取登录用户菜单权限")
    @GetMapping("/userMenu")
    @ResponseBody
    public ResponseMessage<SysMenuEO> userMenu() {
        return Result.success(UserUtils.getMenuTree());
    }

    @ApiOperation(value = "修改当前用户信息")
    @PutMapping("/updateUserInfo")
    @ResponseBody
    public ResponseMessage updateUserInfo(@RequestBody UserInfoEO userInfoEO) {
        UserUtils.updateUserInfo(userInfoEO);
        return Result.success(UserUtils.getUser());
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("/updatePassword")
    @ResponseBody
    public ResponseMessage updatePassword(@NotNull(message = "请输入旧密码") @RequestParam String oldPassword,
                                          @NotNull(message = "请输入新密码") @RequestParam String newPassword) {
        userService.updatePassword(UserUtils.getUserId(), oldPassword, newPassword);
        return Result.success();
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
