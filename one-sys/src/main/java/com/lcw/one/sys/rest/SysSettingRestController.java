package com.lcw.one.sys.rest;

import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.RequestUtils;
import com.lcw.one.util.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/${restPath}/sys/setting")
@Api(description = "系统 - 配置")
public class SysSettingRestController {

    @Autowired
    private Environment environment;

    @ApiOperation(value = "配置列表")
    @GetMapping("")
    public ResponseMessage<Map<String, Object>> listSetting(HttpServletRequest request) {
        Map<String, Object> settingMap = new HashMap<>();
        settingMap.put("appName", environment.getProperty("application.name"));
        settingMap.put("appShortName", environment.getProperty("application.shortName"));
        settingMap.put("appVersion", environment.getProperty("application.version"));
        String maxFileSizeShow = environment.getProperty("spring.http.multipart.max-file-size");
        settingMap.put("maxFileSize", getMaxFileSize(maxFileSizeShow));
        settingMap.put("maxFileSizeShow", maxFileSizeShow);
        settingMap.put("basePath", RequestUtils.getBasePath(request));
        return Result.success(settingMap);
    }

    private static long getMaxFileSize(String maxFileSizeStr) {
        if(StringUtils.isEmpty(maxFileSizeStr)) {
            return 0;
        }

        long maxFileSize = 0;
        if (maxFileSizeStr.toUpperCase().endsWith("MB")) {
            int mb = Integer.parseInt(maxFileSizeStr.substring(0, maxFileSizeStr.length() - 2));
            maxFileSize = mb * 1024 * 1024;
        } else if (maxFileSizeStr.toUpperCase().endsWith("KB")) {
            int kb = Integer.parseInt(maxFileSizeStr.substring(0, maxFileSizeStr.length() - 2));
            maxFileSize = kb * 1024;
        }
        return maxFileSize;
    }

}
