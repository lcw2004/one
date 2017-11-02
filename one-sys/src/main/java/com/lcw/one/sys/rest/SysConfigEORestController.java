package com.lcw.one.sys.rest;

import com.lcw.one.sys.service.SysConfigEOService;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/${restPath}/sys/config")
@Api(description = "系统配置")
public class SysConfigEORestController {

    @Autowired
    private SysConfigEOService sysConfigEOService;

    @ApiOperation(value = "系统配置列表")
    @GetMapping("/{configType}")
    public ResponseMessage<Map<String, String>> map(@PathVariable("configType") String configType) {
        return Result.success(sysConfigEOService.map(configType));
    }

    @ApiOperation(value = "系统配置列表")
    @PostMapping("/{configType}")
    public ResponseMessage update(@RequestBody Map<String, String> configMap) {
        sysConfigEOService.update(configMap);
        return Result.success();
    }


}
