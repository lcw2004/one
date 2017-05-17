package com.lcw.one.user.rest;

import io.swagger.annotations.ApiOperation;

import com.lcw.one.user.service.UserManagerEOService;
import com.lcw.one.user.entity.UserManagerEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/user/manager")
@Api(description = "|UserManagerEO|")
public class UserManagerEORestController {

    @Autowired
    private UserManagerEOService userManagerEOService;

    @ApiOperation(value = "|UserManagerEO|详情")
    @GetMapping("/{id}")
    public ResponseMessage<UserManagerEO> getById(@NotNull @PathVariable("id") String id) {
        return Result.success(userManagerEOService.get(id));
    }

    @ApiOperation(value = "|UserManagerEO|列表")
    @GetMapping("")
    public ResponseMessage<List<UserManagerEO>> list(Integer pageNo, Integer pageSize) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(userManagerEOService.findAll());
    }

    @ApiOperation(value = "新增|UserManagerEO|")
    @PostMapping("")
    public ResponseMessage<UserManagerEO> save(@RequestBody UserManagerEO sysMenuEO) {
        return Result.success(userManagerEOService.save(sysMenuEO));
    }

    @ApiOperation(value = "修改|UserManagerEO|")
    @PutMapping("")
    public ResponseMessage<UserManagerEO> update(@RequestBody UserManagerEO sysMenuEO) {
        return Result.success(userManagerEOService.update(sysMenuEO));
    }

    @ApiOperation(value = "删除|UserManagerEO|")
    @DeleteMapping("/{id}")
    public ResponseMessage deleteById(@NotNull @PathVariable("id") String id) {
        userManagerEOService.delete(id);
        return Result.success();
    }

}
