package com.lcw.one.weixin.rest;

import com.foxinmy.weixin4j.model.WeixinAccount;
import com.foxinmy.weixin4j.mp.api.OauthApi;
import com.foxinmy.weixin4j.mp.model.OauthToken;
import com.lcw.one.base.utils.LoginUserUtils;
import com.lcw.one.login.service.WeixinLoginService;
import com.lcw.one.user.service.UserInfoEOService;
import com.lcw.one.util.bean.LoginUser;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "${restPath}/weixin/auth")
@Api(description = "微信授权服务")
public class WeixinAuthController {

    private static final Logger logger = LoggerFactory.getLogger(WeixinAuthController.class);

    @Autowired
    private WeixinAccount weixinAccount;

    @Autowired
    private UserInfoEOService userInfoEOService;

    @Autowired
    private WeixinLoginService weixinLoginService;

    @ApiOperation(value = "微信回调页面")
    @GetMapping(value = "")
    public String authPage(HttpServletRequest request, String code) {
        if (isLogin(request)) {
            // 用户已经登录
            return toHome();
        } else {
            try {
                // 用户未登录
                logger.info("Weixin Auth Code : {}", code);
                OauthApi oauthApi = new OauthApi(weixinAccount);
                OauthToken oauthToken = oauthApi.getOauthToken(code);
                logger.info("Weixin Opend Id:" + oauthToken.getOpenId());
                request.getSession().setAttribute("openId", oauthToken.getOpenId());

                String token = weixinLoginService.loginByOpenId(request, oauthToken.getOpenId());
                request.getSession().setAttribute("token", token);
                return toHome();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return toLogin();
            }
        }
    }

    @ApiOperation(value = "获取Token")
    @GetMapping(value = "/token")
    @ResponseBody
    public ResponseMessage<String> token(HttpServletRequest request) {
        String openId = (String) request.getSession().getAttribute("openId");
        String token = (String) request.getSession().getAttribute("token");
        if (StringUtils.isEmpty(openId) && StringUtils.isNotEmpty(token)) {
            return Result.error("获取Token失败");
        }
        return Result.success(token);
    }

    @ApiOperation(value = "登录")
    @GetMapping(value = "/login")
    @ResponseBody
    public ResponseMessage loginRest(HttpServletRequest request, @NotBlank(message = "请输入用户名") String account,
                                     @NotBlank(message = "请输入密码") String password) {
        String authCode = "";
        try {
            String openId = (String) request.getSession().getAttribute("openId");
            if (StringUtils.isEmpty(openId)) {
                return Result.error("登录失败");
            }
            String token = weixinLoginService.bindWeChatId(request, account, password, openId);
            request.getSession().setAttribute("token", token);
        } catch (OneBaseException e) {
            return Result.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("authCode", authCode);
        return Result.success(resultMap);
    }

    @ApiOperation(value = "登出")
    @GetMapping(value = "/logout")
    @ResponseBody
    public ResponseMessage logout(HttpServletRequest request) {
        try {
            weixinLoginService.logout(LoginUserUtils.getToken(request));
            request.getSession().removeAttribute("token");
            request.getSession().removeAttribute("openId");
        } catch (Exception e) {
            // ignore
            logger.error(e.getMessage(), e);
        }
        return Result.success();
    }

    @ApiOperation(value = "解绑")
    @GetMapping(value = "/unbind")
    @ResponseBody
    public ResponseMessage unbind(HttpServletRequest request) {
        String openId = (String) request.getSession().getAttribute("openId");
        LoginUser loginUser = LoginUserUtils.getCurrentUser(request);
        if (StringUtils.isEmpty(openId) && loginUser == null) {
            return Result.error("解绑失败");
        }
        userInfoEOService.unbindWechat(loginUser.getUserId(), openId);
        weixinLoginService.logout(LoginUserUtils.getToken(request));
        request.getSession().removeAttribute("token");
        return Result.success();
    }

    private static boolean isLogin(HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("token");
        return StringUtils.isNotEmpty(token) && LoginUserUtils.checkToken(token);
    }

    private static String toHome() {
        return "redirect:http://wx.csruicai.cn/";
    }

    private static String toLogin() {
        return "redirect:http://wx.csruicai.cn/#/login";
    }

}
