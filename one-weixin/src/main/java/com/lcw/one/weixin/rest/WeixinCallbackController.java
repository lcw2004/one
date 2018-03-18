package com.lcw.one.weixin.rest;


import com.lcw.one.util.utils.IOUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(value = "${restPath}/weixin/callback")
@Api(description = "微信回调接口")
public class WeixinCallbackController {

    private static final Logger logger = LoggerFactory.getLogger(WeixinCallbackController.class);

    @ApiOperation(value = "获取Token")
    @GetMapping(value = "")
    public String callback(String signature, String timestamp, String nonce, String echostr) {
        logger.info(String.format("微信回调，参数：signature[%s], timestamp[%s], nonce[%s], echostr[%s]", signature, timestamp, nonce, echostr));
        return echostr;
    }

    @ApiOperation(value = "获取菜单")
    @PostMapping(value = "")
    public String eventProcess(HttpServletRequest request) {
        try {
            byte[] bytes = IOUtils.toByteArray(request.getInputStream());
            logger.info("微信传入内容:" + new String(bytes));

            // 处理微信回调，是否异步由各自的实现决定
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "SUCCESS";
    }

}
