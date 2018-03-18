package com.lcw.one.login.rest;

import com.lcw.one.base.utils.LoginUserUtils;
import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.user.service.UserInfoEOService;
import com.lcw.one.util.exception.LoginInvalidException;
import com.lcw.one.util.http.CookieUtils;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Validated
@Controller
@RequestMapping(value = "${restPath}/")
@Api(description = "用户接口")
public class UserRestController {

    @Autowired
    private UserInfoEOService userService;

    @ApiOperation(value = "修改当前用户信息")
    @PutMapping("/updateUserInfo")
    @ResponseBody
    public ResponseMessage updateUserInfo(@RequestBody UserInfoEO userInfoEO) {
        return Result.success(userService.updateUserInfo(userInfoEO));
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("/updatePassword")
    @ResponseBody
    public ResponseMessage updatePassword(HttpServletRequest request, @NotNull(message = "请输入旧密码") @RequestParam String oldPassword,
                                          @NotNull(message = "请输入新密码") @RequestParam String newPassword) {
        userService.updatePassword(LoginUserUtils.getLoginUserId(request), oldPassword, newPassword);
        return Result.success();
    }

    @ApiOperation(value = "检查是否已经登录")
    @GetMapping("/isLogin")
    @ResponseBody
    public ResponseMessage<Map<String, Object>> isLogin(HttpServletRequest request) {
        Boolean isLogin = false;
        try {
            // 此处不能刷新Redis缓存，否则用户不退出的情况下30分钟不会掉线
            LoginUserUtils.getLoginUserIdWithoutFlush(CookieUtils.getAuthToken(request));
            isLogin = true;
        } catch (LoginInvalidException e) {
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("authCode", CookieUtils.getAuthToken(request));
        resultMap.put("isLogin", isLogin);
        resultMap.put("systemTime", DateUtils.dateToString(new Date()));
        return Result.success(resultMap);
    }


}
