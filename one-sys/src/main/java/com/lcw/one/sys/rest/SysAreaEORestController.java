package com.lcw.one.sys.rest;

import com.lcw.one.sys.entity.SysAreaEO;
import com.lcw.one.sys.service.SysAreaEOService;
import com.lcw.one.util.constant.DeleteFlagEnum;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/sys/area")
@Api(description = "系统 - 区域")
public class SysAreaEORestController {

    @Autowired
    private SysAreaEOService sysAreaEOService;

    @ApiOperation(value = "区域详情")
    @GetMapping("/{id}")
    public ResponseMessage<SysAreaEO> getById(@PathVariable("id") String id) {
        return Result.success(sysAreaEOService.get(id));
    }

    @ApiOperation(value = "区域列表")
    @GetMapping("")
    public ResponseMessage<List<SysAreaEO>> list(HttpServletRequest request) {
        return Result.success(sysAreaEOService.findAll());
    }

    @ApiOperation(value = "区域树")
    @GetMapping(value = "/tree")
    public ResponseMessage<SysAreaEO> listAsTree() {
        SysAreaEO topSysMenu = sysAreaEOService.organizeListAsTree(sysAreaEOService.get("1"), sysAreaEOService.findAll());
        return Result.success(topSysMenu);
    }

    @ApiOperation(value = "新增区域")
    @PostMapping("")
    public ResponseMessage<SysAreaEO> save(@RequestBody SysAreaEO sysMenuEO) {
        return Result.success(sysAreaEOService.save(sysMenuEO));
    }

    @ApiOperation(value = "修改区域")
    @PutMapping("")
    public ResponseMessage<SysAreaEO> update(@RequestBody SysAreaEO sysMenuEO) {
        return Result.success(sysAreaEOService.update(sysMenuEO));
    }

    @ApiOperation(value = "删除区域")
    @DeleteMapping("/{id}")
    public ResponseMessage deleteById(@PathVariable("id") String id) {
        sysAreaEOService.delete(id);
        return Result.success();
    }

}
