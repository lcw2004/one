package com.lcw.one.sys.rest;

import com.lcw.one.sys.entity.SysHomeComponentEO;
import com.lcw.one.sys.service.SysHomeComponentEOService;
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
@RequestMapping(value = "/${restPath}/sys/homeComponent")
@Api(description = "系统首页组件")
public class SysHomeComponentEORestController {

    @Autowired
    private SysHomeComponentEOService sysHomeComponentEOService;

    @ApiOperation(value = "系统首页组件详情")
    @GetMapping("/{id}")
    public ResponseMessage<SysHomeComponentEO> get(@PathVariable Integer id) {
        return Result.success(sysHomeComponentEOService.get(id));
    }

    @ApiOperation(value = "系统首页组件列表")
    @GetMapping("")
    public ResponseMessage<List<SysHomeComponentEO>> list(Integer pageNo, Integer pageSize) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(sysHomeComponentEOService.findAll());
    }

    @ApiOperation(value = "新增系统首页组件")
    @PostMapping("")
    public ResponseMessage<SysHomeComponentEO> save(@RequestBody SysHomeComponentEO sysHomeComponentEO) {
        return Result.success(sysHomeComponentEOService.save(sysHomeComponentEO));
    }

    @ApiOperation(value = "修改系统首页组件")
    @PutMapping("")
    public ResponseMessage<SysHomeComponentEO> update(@RequestBody SysHomeComponentEO sysHomeComponentEO) {
        return Result.success(sysHomeComponentEOService.update(sysHomeComponentEO));
    }

    @ApiOperation(value = "删除系统首页组件")
    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable Integer id) {
        sysHomeComponentEOService.delete(id);
        return Result.success();
    }

}