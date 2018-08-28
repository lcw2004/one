package com.lcw.one.sys.rest;

import com.lcw.one.sys.entity.SysScheduleEO;
import com.lcw.one.sys.service.SysScheduleEOService;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/sys/schedule")
@Api(description = "任务调度")
public class SysScheduleEORestController {

    @Autowired
    private SysScheduleEOService sysScheduleEOService;

    @ApiOperation(value = "任务调度详情")
    @GetMapping("/{id}")
    public ResponseMessage<SysScheduleEO> get(@PathVariable String id) {
        return Result.success(sysScheduleEOService.get(id));
    }

    @ApiOperation(value = "任务调度列表")
    @GetMapping("")
    public ResponseMessage<List<SysScheduleEO>> list(Integer pageNo, Integer pageSize) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(sysScheduleEOService.findAll());
    }

    @ApiOperation(value = "新增任务调度")
    @PostMapping("")
    public ResponseMessage<SysScheduleEO> save(@RequestBody SysScheduleEO sysScheduleEO) {
        return Result.success(sysScheduleEOService.save(sysScheduleEO));
    }

    @ApiOperation(value = "修改任务调度")
    @PutMapping("")
    public ResponseMessage<SysScheduleEO> update(@RequestBody SysScheduleEO sysScheduleEO) {
        return Result.success(sysScheduleEOService.update(sysScheduleEO));
    }

    @ApiOperation(value = "删除任务调度")
    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable String id) {
        sysScheduleEOService.delete(id);
        return Result.success();
    }

}