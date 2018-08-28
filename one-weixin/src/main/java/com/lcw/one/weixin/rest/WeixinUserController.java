package com.lcw.one.weixin.rest;


import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.Button;
import com.foxinmy.weixin4j.mp.api.MenuApi;
import com.foxinmy.weixin4j.token.TokenManager;
import com.foxinmy.weixin4j.type.ButtonType;
import com.lcw.one.base.utils.LoginUserUtils;
import com.lcw.one.login.service.WeixinLoginService;
import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.user.service.UserInfoEOService;
import com.lcw.one.util.bean.LoginUser;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "${restPath}/weixin/user")
@Api(description = "账户接口")
public class WeixinUserController {

    @Autowired
    private UserInfoEOService userInfoEOService;

    @ApiOperation(value = "获取Token")
    @GetMapping(value = "/info")
    public ResponseMessage userInfo(HttpServletRequest request) {
        LoginUser loginUser = LoginUserUtils.getCurrentUser(request);
        UserInfoEO userInfoEO = userInfoEOService.get(loginUser.getUserId());
        return Result.success(userInfoEO);
    }

}
