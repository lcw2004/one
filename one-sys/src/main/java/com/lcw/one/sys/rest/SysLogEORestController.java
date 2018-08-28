package com.lcw.one.sys.rest;

import com.lcw.one.sys.bean.SysLogQueryCondition;
import com.lcw.one.sys.entity.SysLogEO;
import com.lcw.one.sys.service.SysLogEOService;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/${restPath}/sys/log")
@Api(description = "系统日志")
public class SysLogEORestController {

    @Autowired
    private SysLogEOService sysLogEOService;

    @ApiOperation(value = "系统日志列表")
    @GetMapping("")
    public ResponseMessage<PageInfo<SysLogEO>> page(SysLogQueryCondition queryCondition) {
        return Result.success(sysLogEOService.page(queryCondition));
    }

    @ApiOperation(value = "系统日志详情")
    @GetMapping("/{logId}")
    public ResponseMessage<SysLogEO> getById(@PathVariable String logId) {
        return Result.success(sysLogEOService.get(logId));
    }

}
