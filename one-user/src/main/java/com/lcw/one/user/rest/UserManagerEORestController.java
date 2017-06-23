package com.lcw.one.user.rest;

import com.lcw.one.user.bean.ManageUserQueryCondition;
import com.lcw.one.user.bean.UserQueryCondition;
import com.lcw.one.user.entity.UserManagerEO;
import com.lcw.one.user.service.UserManagerEOService;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/${restPath}/user/manager")
@Api(description = "管理用户")
public class UserManagerEORestController {

    @Autowired
    private UserManagerEOService userManagerEOService;

    @ApiOperation(value = "管理用户详情")
    @GetMapping("/{id}")
    public ResponseMessage<UserManagerEO> getById(@NotNull @PathVariable("id") String id) {
        return Result.success(userManagerEOService.get(id));
    }

    @ApiOperation(value = "管理用户列表")
    @GetMapping("")
    public ResponseMessage<PageInfo<UserManagerEO>> list(@ModelAttribute ManageUserQueryCondition userQueryCondition) {
        return Result.success(userManagerEOService.page(userQueryCondition));
    }

    @ApiOperation(value = "新增管理用户")
    @PostMapping("")
    public ResponseMessage<UserManagerEO> save(@RequestBody UserManagerEO sysMenuEO) {
        return Result.success(userManagerEOService.save(sysMenuEO));
    }

    @ApiOperation(value = "修改管理用户")
    @PutMapping("")
    public ResponseMessage<UserManagerEO> update(@RequestBody UserManagerEO sysMenuEO) {
        return Result.success(userManagerEOService.update(sysMenuEO));
    }

    @ApiOperation(value = "删除管理用户")
    @DeleteMapping("/{id}")
    public ResponseMessage deleteById(@NotNull @PathVariable("id") String id) {
        userManagerEOService.delete(id);
        return Result.success();
    }

}
