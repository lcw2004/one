package com.lcw.one.sys.rest;

import com.lcw.one.sys.entity.SysScheduleLogEO;
import com.lcw.one.sys.service.SysScheduleLogEOService;
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
@RequestMapping(value = "/${restPath}/sys/scheduleLog")
@Api(description = "任务调度日志")
public class SysScheduleLogEORestController {

    @Autowired
    private SysScheduleLogEOService sysScheduleLogEOService;

    @ApiOperation(value = "任务调度日志详情")
    @GetMapping("/{id}")
    public ResponseMessage<SysScheduleLogEO> get(@PathVariable String id) {
        return Result.success(sysScheduleLogEOService.get(id));
    }

    @ApiOperation(value = "任务调度日志列表")
    @GetMapping("")
    public ResponseMessage<List<SysScheduleLogEO>> list(Integer pageNo, Integer pageSize) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(sysScheduleLogEOService.findAll());
    }

    @ApiOperation(value = "新增任务调度日志")
    @PostMapping("")
    public ResponseMessage<SysScheduleLogEO> save(@RequestBody SysScheduleLogEO sysScheduleLogEO) {
        return Result.success(sysScheduleLogEOService.save(sysScheduleLogEO));
    }

    @ApiOperation(value = "修改任务调度日志")
    @PutMapping("")
    public ResponseMessage<SysScheduleLogEO> update(@RequestBody SysScheduleLogEO sysScheduleLogEO) {
        return Result.success(sysScheduleLogEOService.update(sysScheduleLogEO));
    }

    @ApiOperation(value = "删除任务调度日志")
    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable String id) {
        sysScheduleLogEOService.delete(id);
        return Result.success();
    }

}