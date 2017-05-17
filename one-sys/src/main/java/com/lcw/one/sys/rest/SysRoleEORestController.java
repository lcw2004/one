package com.lcw.one.sys.rest;

import com.lcw.one.sys.service.SysRoleEOService;
import com.lcw.one.sys.entity.SysRoleEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/sys/role")
@Api(description = "系统 - 角色")
public class SysRoleEORestController {

    @Autowired
    private SysRoleEOService sysRoleEOService;

    @ApiOperation(value = "角色详情")
    @GetMapping("/{id}")
    public ResponseMessage<SysRoleEO> getById(@NotNull @PathVariable("id") String id) {
        return Result.success(sysRoleEOService.get(id));
    }

    @ApiOperation(value = "角色列表")
    @GetMapping("")
    public ResponseMessage<PageInfo<SysRoleEO>> list(Integer pageNo, Integer pageSize, String roleName) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(sysRoleEOService.page(pageInfo, roleName));
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("")
    public ResponseMessage<SysRoleEO> save(@RequestBody SysRoleEO sysMenuEO) {
        return Result.success(sysRoleEOService.save(sysMenuEO));
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("")
    public ResponseMessage<SysRoleEO> update(@RequestBody SysRoleEO sysMenuEO) {
        return Result.success(sysRoleEOService.update(sysMenuEO));
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/{id}")
    public ResponseMessage deleteById(@NotNull @PathVariable("id") String id) {
        sysRoleEOService.delete(id);
        return Result.success();
    }

}
