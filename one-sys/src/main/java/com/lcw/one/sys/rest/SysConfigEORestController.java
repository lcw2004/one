package com.lcw.one.sys.rest;

import com.lcw.one.sys.entity.SysConfigEO;
import com.lcw.one.sys.service.SysConfigEOService;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/${restPath}/sys/config")
@Api(description = "系统参数")
public class SysConfigEORestController {

    @Autowired
    private SysConfigEOService sysConfigEOService;

    @ApiOperation(value = "系统参数列表")
    @GetMapping("")
    public ResponseMessage<PageInfo<SysConfigEO>> page(HttpServletRequest request, String configName, String configKey) {
        return Result.success(sysConfigEOService.page(new PageInfo(request), configName, configKey));
    }

    @ApiOperation(value = "系统参数列表")
    @GetMapping("/{configId}")
    public ResponseMessage<SysConfigEO> get(@PathVariable String configId) {
        return Result.success(sysConfigEOService.get(configId));
    }

    @ApiOperation(value = "修改系统参数")
    @PutMapping("")
    public ResponseMessage update(@RequestBody SysConfigEO sysConfigEO) {
        sysConfigEOService.update(sysConfigEO);
        return Result.success();
    }

    @ApiOperation(value = "系统参数列表")
    @GetMapping("/type/{configType}")
    public ResponseMessage<Map<String, String>> map(@PathVariable String configType) {
        return Result.success(sysConfigEOService.map(configType));
    }
}
