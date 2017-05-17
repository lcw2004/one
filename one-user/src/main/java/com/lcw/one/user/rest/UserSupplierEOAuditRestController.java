package com.lcw.one.user.rest;

import com.lcw.one.user.bean.SupplierQueryCondition;
import com.lcw.one.user.entity.UserSupplierEO;
import com.lcw.one.user.service.UserSupplierEOAuditService;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/${restPath}/user/supplier/audit")
@Api(description = "用户 - 供应商审核")
public class UserSupplierEOAuditRestController {

    @Autowired
    private UserSupplierEOAuditService userSupplierEOAuditService;

    @ApiOperation(value = "供应商详情")
    @GetMapping("/{id}")
    public ResponseMessage<UserSupplierEO> getUserSupplierEOWithIsCanAudit(HttpServletRequest request, @NotNull @PathVariable("id") String id) {
        return Result.success(userSupplierEOAuditService.getUserSupplierEOWithIsCanAudit(id, RequestUtils.getLoginUserId(request)));
    }

    @ApiOperation(value = "投标类别待审核供应商列表")
    @GetMapping("")
    public ResponseMessage<PageInfo<UserSupplierEO>> list(HttpServletRequest request, Integer pageNo, Integer pageSize, @ModelAttribute SupplierQueryCondition queryCondition) {
        String userId = RequestUtils.getLoginUserId(request);
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(userSupplierEOAuditService.pageUserSupplierEOByAuditUserId(pageInfo, queryCondition, userId));
    }

    @ApiOperation(value = "供应商审核")
    @PostMapping("/{supplierId}/{purchaseTypeId}")
    public ResponseMessage audit(HttpServletRequest request,
                                 @NotNull @PathVariable("supplierId") String supplierId,
                                 @NotNull @PathVariable("purchaseTypeId") String purchaseTypeId,
                                 @NotNull @RequestParam Boolean auditResult,
                                 String remark) {
        if (!auditResult && StringUtils.isEmpty(remark)) {
            return Result.error("驳回理由不能为空");
        }

        userSupplierEOAuditService.auditSupplierPurchaseType(supplierId, purchaseTypeId, RequestUtils.getLoginUserId(request), auditResult, remark);
        return Result.success();
    }

}
