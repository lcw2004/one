package com.lcw.one.main.rest;

import com.lcw.one.login.rest.LoginRestController;
import com.lcw.one.sys.rest.SysDictEORestController;
import com.lcw.one.sys.rest.SysSettingRestController;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "${restPath}/initData")
@Api(description = "初始化数据接口")
public class InitRestController {

    @Autowired
    private LoginRestController loginRestController;

    @Autowired
    private SysDictEORestController sysDictEORestController;

    @Autowired
    private SysSettingRestController sysSettingRestController;

    @ApiOperation(value = "获取初始化数据")
    @GetMapping("")
    @ResponseBody
    public ResponseMessage<Map<String, Object>> initData(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();

        result.put("userMenu", loginRestController.userMenu().getData());
        result.put("userInfo", loginRestController.userInfo(request).getData());
        result.put("sysDict", sysDictEORestController.groupList().getData());
        result.put("sysSetting", sysSettingRestController.listSetting(request).getData());

        return Result.success(result);
    }
}
