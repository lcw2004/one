package com.lcw.one.weixin.rest;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.Button;
import com.foxinmy.weixin4j.mp.api.MenuApi;
import com.foxinmy.weixin4j.token.TokenManager;
import com.foxinmy.weixin4j.type.ButtonType;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "${restPath}/weixin/test")
@Api(description = "账户接口")
public class WeixinTestController {

    @Autowired
    private TokenManager tokenManager;

    @Value("${one.weixin.appId}")
    private String appId;

    @ApiOperation(value = "获取Token")
    @GetMapping(value = "/token")
    public ResponseMessage token() throws WeixinException {
        return Result.success(tokenManager.getAccessToken());
    }

    @ApiOperation(value = "获取菜单")
    @GetMapping(value = "/menu")
    public ResponseMessage menu() throws WeixinException {
        MenuApi menuApi = new MenuApi(tokenManager);
        List<Button> buttonList = new ArrayList<>();

        String menuUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + "&redirect_uri=http://wx.csruicai.cn/api/weixin/auth&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect";
        Button button1 = new Button("招投标系统4", menuUrl, ButtonType.view);
        buttonList.add(button1);
        menuApi.createMenu(buttonList);
        return Result.success();
    }

}
