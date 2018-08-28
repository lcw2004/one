package com.lcw.one.user.rest;

import com.lcw.one.base.utils.LoginUserUtils;
import com.lcw.one.user.bean.ManageUserQueryCondition;
import com.lcw.one.user.bean.vo.ExcelImportBean;
import com.lcw.one.user.bean.vo.ManageUserVO;
import com.lcw.one.user.entity.UserManagerEO;
import com.lcw.one.user.service.UserManagerImportService;
import com.lcw.one.user.service.UserManagerEOService;
import com.lcw.one.util.excel.ExcelUtils;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/user/manager")
@Api(description = "管理用户")
public class UserManagerEORestController {

    @Autowired
    private UserManagerEOService userManagerEOService;

    @Autowired
    private UserManagerImportService userManagerImportService;

    @ApiOperation(value = "当前用户详情")
    @GetMapping("/userInfo")
    public ResponseMessage<UserManagerEO> getCurrentUser(HttpServletRequest request) {
        return Result.success(userManagerEOService.get(LoginUserUtils.getLoginUserId(request)));
    }

    @ApiOperation(value = "修改用户详情")
    @PutMapping("/userInfo")
    public ResponseMessage<UserManagerEO> updateCurrentUser(HttpServletRequest request, @RequestBody UserManagerEO userManagerEO) {
        return Result.success(userManagerEOService.update(userManagerEO));
    }

    @ApiOperation(value = "管理用户详情")
    @GetMapping("/{id}")
    public ResponseMessage<UserManagerEO> getById(@PathVariable String id) {
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
    public ResponseMessage deleteById(@PathVariable String id) {
        userManagerEOService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "导出模板文件")
    @GetMapping("/template")
    public void exportTemplate(HttpServletResponse response) throws IOException {
        String title = "用户数据模板";
        byte[] bytes = ExcelUtils.saveAsExcel(title, UserManagerImportService.templateData(), UserManagerImportService.buildExcelRule());
        ExcelUtils.writeExcelBytesToResponse(response, bytes, title);
    }

    @ApiOperation(value = "导入用户")
    @PostMapping("/import")
    public ResponseMessage<ExcelImportBean<ManageUserVO>> importUser(Integer coverType, String[] roleIdList, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null) {
            if (roleIdList == null || roleIdList.length == 0) {
                return Result.error("角色不能为空");
            }

            List<ManageUserVO> userVOList = ExcelUtils.parse(ManageUserVO.class, file.getInputStream(), UserManagerImportService.buildExcelRule());
            ExcelImportBean<ManageUserVO> excelImportBean = new ExcelImportBean<>();
            excelImportBean.setSourceDataList(userVOList);
            excelImportBean.getParamMap().put("roleIdList", Arrays.asList(roleIdList));
            userManagerImportService.importUser(coverType, excelImportBean);
            return Result.success(excelImportBean);
        } else {
            return Result.error("上传文件失败");
        }
    }
}
