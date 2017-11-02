package com.lcw.one.login.rest;

import com.lcw.one.baseInfo.constant.VerifyCodeTypeEnum;
import com.lcw.one.login.security.UsernamePasswordToken;
import com.lcw.one.login.security.exception.CaptchaException;
import com.lcw.one.login.security.validatecode.IVerifyCodeGen;
import com.lcw.one.login.security.validatecode.SimpleCharVerifyCodeGenImpl;
import com.lcw.one.login.security.validatecode.VerifyCode;
import com.lcw.one.login.util.UserUtils;
import com.lcw.one.sys.entity.SysMenuEO;
import com.lcw.one.user.constant.UserInfoTypeEnum;
import com.lcw.one.user.constant.UserSupplierStatusEnum;
import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.user.entity.UserSupplierEO;
import com.lcw.one.user.service.UserInfoEOService;
import com.lcw.one.user.service.UserSupplierEOService;
import com.lcw.one.util.constant.GlobalConfig;
import com.lcw.one.util.exception.LoginInvalidException;
import com.lcw.one.util.http.CookieUtils;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.LoginUserUtils;
import com.lcw.one.util.utils.RedisUtil;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Validated
@Controller
@RequestMapping(value = "${restPath}/")
@Api(description = "登录接口")
public class LoginRestController {

    private static final Logger logger = LoggerFactory.getLogger(LoginRestController.class);

    @Autowired
    private UserInfoEOService userService;

    @Autowired
    private UserSupplierEOService userSupplierEOService;

    @Autowired
    private RedisUtil redisUtil;

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
    public ResponseMessage loginRest(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam @NotNull(message = "请输入用户名") String username,
                                     @RequestParam @NotNull(message = "请输入密码") String password,
                                     @RequestParam(value = "isRememberMe", defaultValue = "false") Boolean isRememberMe,
                                     String verifyCode) {
        String authCode = "";
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password.toCharArray(), verifyCode);
            subject.login(usernamePasswordToken);
            UserUtils.flushUserLoginTimeAndIp();


            String cookieValue = CookieUtils.setCookie(request, response);
            LoginUserUtils.set(cookieValue + LoginUserUtils.LOGIN_USER, UserUtils.getUser());
            LoginUserUtils.set(cookieValue + LoginUserUtils.LOGIN_USER_ID, UserUtils.getUserId());
            LoginUserUtils.set(cookieValue + LoginUserUtils.LOGIN_USER_NAME, UserUtils.getUser().getName());
            LoginUserUtils.set(cookieValue + LoginUserUtils.LOGIN_ROLE_ID, UserUtils.getRoleIds());

            // 非供应商用户不需要完善登录信息
            UserInfoEO user = UserUtils.getUser();
            if (user.getType() == UserInfoTypeEnum.SUPPLIER.getValue()) {
                UserSupplierEO userSupplierEO = userSupplierEOService.getUserSupplierEOByPrincipalUserUserId(user.getUserId());
                LoginUserUtils.set(cookieValue + LoginUserUtils.LOGIN_SUPPLIER_ID, userSupplierEO.getSupplierId());
                LoginUserUtils.set(cookieValue + LoginUserUtils.LOGIN_SUPPLIER, userSupplierEO);
            }

            // 返回授权码
            authCode = cookieValue;
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
        resultMap.put("authCode", authCode);
        return Result.success(resultMap);
    }

    @ApiOperation(value = "退出登录")
    @GetMapping("/logout")
    @ResponseBody
    public ResponseMessage logout(HttpServletRequest request, HttpServletResponse response) {
        LoginUserUtils.clearAll(CookieUtils.getCookieValue(request));
        CookieUtils.removeCookie(request, response);
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
    public ResponseMessage<UserInfoEO> userInfo(HttpServletRequest request) {
        LoginUserUtils.getLoginUserId(request);
        UserInfoEO user = UserUtils.getUser();
        if (user == null) {
            throw new LoginInvalidException();
        }
        return Result.success(user);
    }

    @ApiOperation(value = "获取当前登录供应商信息")
    @GetMapping("/supplierInfo")
    @ResponseBody
    public ResponseMessage<UserSupplierEO> supplierInfo() {
        UserInfoEO user = UserUtils.getUser();
        if (user == null) {
            throw new LoginInvalidException();
        }

        UserSupplierEO userSupplierEO = userSupplierEOService.getUserSupplierEOByPrincipalUserUserId(user.getUserId());
        return Result.success(userSupplierEO);
    }

    @ApiOperation(value = "判断是否需要完善供应商信息")
    @GetMapping("/isNeedPerfectSupplierInfo")
    @ResponseBody
    public ResponseMessage<Boolean> isNeedPerfectSupplierInfo(HttpServletRequest request) {
        UserInfoEO user = UserUtils.getUser();
        if (user == null) {
            throw new LoginInvalidException();
        }

        // 非供应商用户不需要完善登录信息
        if (user.getType() != UserInfoTypeEnum.SUPPLIER.getValue()) {
            return Result.success(false);
        }

        UserSupplierEO userSupplierEO = userSupplierEOService.getUserSupplierEOByPrincipalUserUserId(user.getUserId());
        return Result.success(Objects.equals(userSupplierEO.getStatus(), UserSupplierStatusEnum.NOT_COMMIT.getValue()));
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

    @ApiOperation(value = "检查是否已经登录")
    @GetMapping("/isLogin")
    @ResponseBody
    public ResponseMessage<Map<String, Object>> isLogin(HttpServletRequest request) {
        Boolean isLogin = false;
        try {
            LoginUserUtils.getLoginUserId(request);
            isLogin = true;
        } catch (LoginInvalidException e) {
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("authCode", CookieUtils.getCookieValue(request));
        resultMap.put("isLogin", isLogin);
        return Result.success(resultMap);
    }
}
