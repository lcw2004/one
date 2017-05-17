package com.lcw.one.sys.rest;

import com.lcw.one.sys.service.SysMenuEOService;
import com.lcw.one.sys.entity.SysMenuEO;
import com.lcw.one.util.http.PageInfo;
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
@RequestMapping(value = "/${restPath}/sys/menu")
@Api(description = "系统 - 菜单")
public class SysMenuEORestController {

    @Autowired
    private SysMenuEOService sysMenuEOService;


    @ApiOperation(value = "菜单详情")
    @GetMapping("/{id}")
    public ResponseMessage<SysMenuEO> getById(@NotNull @PathVariable("id") String id) {
        return Result.success(sysMenuEOService.get(id));
    }

    @ApiOperation(value = "菜单列表")
    @GetMapping("")
    public ResponseMessage<List<SysMenuEO>> list(HttpServletRequest request) {
        return Result.success(sysMenuEOService.findAll());
    }

    @ApiOperation(value = "菜单树")
    @GetMapping(value = "/tree")
    public ResponseMessage<SysMenuEO> listAsTree() {
        SysMenuEO topSysMenu = sysMenuEOService.organizeListAsTree(sysMenuEOService.get("1"), sysMenuEOService.findAll());
        return Result.success(topSysMenu);
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping("")
    public ResponseMessage<SysMenuEO> save(@RequestBody SysMenuEO sysMenuEO) {
        return Result.success(sysMenuEOService.save(sysMenuEO));
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("")
    public ResponseMessage<SysMenuEO> update(@RequestBody SysMenuEO sysMenuEO) {
        return Result.success(sysMenuEOService.update(sysMenuEO));
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/{id}")
    public ResponseMessage deleteById(@NotNull @PathVariable("id") String id) {
        sysMenuEOService.deleteTreeEntity(id);
        return Result.success();
    }

}
