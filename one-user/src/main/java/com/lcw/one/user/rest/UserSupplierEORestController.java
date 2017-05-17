package com.lcw.one.user.rest;

import com.lcw.one.user.bean.SupplierQueryCondition;
import com.lcw.one.user.dao.UserSupplierQualificationEODao;
import com.lcw.one.user.entity.UserSupplierEO;
import com.lcw.one.user.entity.UserSupplierQualificationEO;
import com.lcw.one.user.service.UserSupplierEOService;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/user/supplier")
@Api(description = "用户 - 供应商")
public class UserSupplierEORestController {

    @Autowired
    private UserSupplierEOService userSupplierEOService;

    @Autowired
    private UserSupplierQualificationEODao userSupplierQualificationEODao;

    @ApiOperation(value = "供应商详情")
    @GetMapping("/{id}")
    public ResponseMessage<UserSupplierEO> getById(@NotNull @PathVariable("id") String id) {
        return Result.success(userSupplierEOService.get(id));
    }

    @ApiOperation(value = "供应商资质文件列表")
    @GetMapping("/{id}/qualifications")
    public ResponseMessage<List<UserSupplierQualificationEO>> listQualification(@NotNull @PathVariable("id") String id) {
        return Result.success(userSupplierQualificationEODao.listBySupplierId(id));
    }

    @ApiOperation(value = "供应商列表")
    @GetMapping("")
    public ResponseMessage<PageInfo<UserSupplierEO>> list(Integer pageNo, Integer pageSize, @ModelAttribute SupplierQueryCondition queryCondition) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(userSupplierEOService.page(pageInfo, queryCondition));
    }

    @ApiOperation(value = "新增供应商")
    @PostMapping("")
    public ResponseMessage<UserSupplierEO> save(@RequestBody UserSupplierEO sysMenuEO) {
        return Result.success(userSupplierEOService.save(sysMenuEO));
    }

    @ApiOperation(value = "修改供应商")
    @PutMapping("")
    public ResponseMessage<UserSupplierEO> update(@RequestBody UserSupplierEO sysMenuEO) {
        return Result.success(userSupplierEOService.update(sysMenuEO));
    }

    @ApiOperation(value = "删除供应商")
    @DeleteMapping("/{id}")
    public ResponseMessage delete(@NotNull @PathVariable("id") String id) {
        userSupplierEOService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "停用供应商账户")
    @PutMapping("/{id}/stop")
    public ResponseMessage stop(@NotNull @PathVariable("id") String id) {
        userSupplierEOService.stop(id);
        return Result.success();
    }

    @ApiOperation(value = "启用供应商账户")
    @PutMapping("/{id}/start")
    public ResponseMessage start(@NotNull @PathVariable("id") String id) {
        userSupplierEOService.start(id);
        return Result.success();
    }

    @ApiOperation(value = "将供应商账户移到黑名单中")
    @PutMapping("/{id}/move-to-blacklist")
    public ResponseMessage moveBlacklist(@NotNull @PathVariable("id") String id) {
        userSupplierEOService.moveToBlacklist(id);
        return Result.success();
    }

    @ApiOperation(value = "将供应商账户从黑名单中移除")
    @PutMapping("/{id}/move-from-blacklist")
    public ResponseMessage moveFromBlacklist(@NotNull @PathVariable("id") String id) {
        userSupplierEOService.moveFromBlacklist(id);
        return Result.success();
    }

}
