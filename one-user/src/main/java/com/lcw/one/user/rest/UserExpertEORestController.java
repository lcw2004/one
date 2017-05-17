package com.lcw.one.user.rest;

import io.swagger.annotations.ApiOperation;

import com.lcw.one.user.service.UserExpertEOService;
import com.lcw.one.user.entity.UserExpertEO;
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
@RequestMapping(value = "/${restPath}/user/expert")
@Api(description = "|UserExpertEO|")
public class UserExpertEORestController {

    @Autowired
    private UserExpertEOService userExpertEOService;

    @ApiOperation(value = "|UserExpertEO|详情")
    @GetMapping("/{id}")
    public ResponseMessage<UserExpertEO> getById(@NotNull @PathVariable("id") String id) {
        return Result.success(userExpertEOService.get(id));
    }

    @ApiOperation(value = "|UserExpertEO|列表")
    @GetMapping("")
    public ResponseMessage<List<UserExpertEO>> list(Integer pageNo, Integer pageSize) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(userExpertEOService.findAll());
    }

    @ApiOperation(value = "新增|UserExpertEO|")
    @PostMapping("")
    public ResponseMessage<UserExpertEO> save(@RequestBody UserExpertEO sysMenuEO) {
        return Result.success(userExpertEOService.save(sysMenuEO));
    }

    @ApiOperation(value = "修改|UserExpertEO|")
    @PutMapping("")
    public ResponseMessage<UserExpertEO> update(@RequestBody UserExpertEO sysMenuEO) {
        return Result.success(userExpertEOService.update(sysMenuEO));
    }

    @ApiOperation(value = "删除|UserExpertEO|")
    @DeleteMapping("/{id}")
    public ResponseMessage deleteById(@NotNull @PathVariable("id") String id) {
        userExpertEOService.delete(id);
        return Result.success();
    }

}
