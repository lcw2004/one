package com.lcw.one.sys.rest;

import com.lcw.one.sys.entity.SysOfficeEO;
import com.lcw.one.sys.service.SysOfficeEOService;
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
@RequestMapping(value = "/${restPath}/sys/office")
@Api(description = "系统 - 机构")
public class SysOfficeEORestController {

    @Autowired
    private SysOfficeEOService sysOfficeEOService;

    @ApiOperation(value = "机构详情")
    @GetMapping("/{id}")
    public ResponseMessage<SysOfficeEO> getById(@PathVariable("id") String id) {
        return Result.success(sysOfficeEOService.get(id));
    }

    @ApiOperation(value = "机构列表")
    @GetMapping("")
    public ResponseMessage<List<SysOfficeEO>> list(HttpServletRequest request) {
        return Result.success(sysOfficeEOService.findAll());
    }

    @ApiOperation(value = "机构树")
    @GetMapping(value = "/tree")
    public ResponseMessage<SysOfficeEO> listAsTree() {
        SysOfficeEO topSysMenu = sysOfficeEOService.organizeListAsTree(sysOfficeEOService.get("1"), sysOfficeEOService.findAll());
        return Result.success(topSysMenu);
    }

    @ApiOperation(value = "添加机构")
    @PostMapping("")
    public ResponseMessage<SysOfficeEO> save(@RequestBody SysOfficeEO sysMenuEO) {
        return Result.success(sysOfficeEOService.save(sysMenuEO));
    }

    @ApiOperation(value = "修改机构")
    @PutMapping("")
    public ResponseMessage<SysOfficeEO> update(@RequestBody SysOfficeEO sysMenuEO) {
        return Result.success(sysOfficeEOService.update(sysMenuEO));
    }

    @ApiOperation(value = "机构详情")
    @DeleteMapping("/{id}")
    public ResponseMessage deleteById(@PathVariable("id") String id) {
        sysOfficeEOService.delete(id);
        return Result.success();
    }

}
