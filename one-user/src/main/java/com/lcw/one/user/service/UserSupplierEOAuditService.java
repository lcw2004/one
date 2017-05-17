package com.lcw.one.user.service;

import com.lcw.one.baseInfo.dao.BasePurchaseTypeEODao;
import com.lcw.one.user.bean.SupplierQueryCondition;
import com.lcw.one.user.constant.AuditStatusEnum;
import com.lcw.one.user.dao.UserSupplierEODao;
import com.lcw.one.user.dao.UserSupplierPurchaseTypeRelationEODao;
import com.lcw.one.baseInfo.entity.BasePurchaseTypeEO;
import com.lcw.one.user.entity.UserSupplierEO;
import com.lcw.one.user.entity.UserSupplierPurchaseTypeRelationEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.entity.TreeEntityUtil;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserSupplierEOAuditService extends CrudService<UserSupplierEODao, UserSupplierEO> {

    @Autowired
    private BasePurchaseTypeEODao basePurchaseTypeEODao;

    @Autowired
    private UserSupplierEOService userSupplierEOService;

    @Autowired
    private UserSupplierPurchaseTypeRelationEODao userSupplierPurchaseTypeRelationEODao;

    public PageInfo<UserSupplierEO> pageUserSupplierEOByAuditUserId(PageInfo pageInfo, SupplierQueryCondition condition, String auditUserId) {
        return dao.pageUserSupplierEOByAuditUserId(pageInfo, condition, auditUserId);
    }

    public UserSupplierEO getUserSupplierEOWithIsCanAudit(String id, String userId) {
        UserSupplierEO userSupplierEO = userSupplierEOService.get(id);

        // 检查是否在用户的可审核列表中，如果在，则设置为可审核
        List<BasePurchaseTypeEO> basePurchaseTypeEOList = basePurchaseTypeEODao.listBasePurchaseTypeEOByAuditUserId(userId);
        Map<String, BasePurchaseTypeEO> basePurchaseTypeEOMap = new TreeEntityUtil<BasePurchaseTypeEO>().asMapById(basePurchaseTypeEOList);
        if (CollectionUtils.isNotEmpty(userSupplierEO.getPurchaseTypeList())) {
            for (UserSupplierPurchaseTypeRelationEO relationEO : userSupplierEO.getPurchaseTypeList()) {
                relationEO.setCanAudit(basePurchaseTypeEOMap.containsKey(relationEO.getPurchaseTypeId()));
            }
        }
        return userSupplierEO;
    }

    public void auditSupplierPurchaseType(String supplierId, String purchaseTypeId, String auditUserId, Boolean auditResult, String remark) {
        UserSupplierPurchaseTypeRelationEO relationEO = userSupplierPurchaseTypeRelationEODao.getBySupplierIdAndPurchaseTypeId(supplierId, purchaseTypeId);
        Integer auditStatus = auditResult ? AuditStatusEnum.NORMAL.getValue() : AuditStatusEnum.REJECT.getValue();
        relationEO.setStatus(auditStatus);
        relationEO.setRemark(remark);
        relationEO.setAuditUserId(auditUserId);
        userSupplierPurchaseTypeRelationEODao.save(relationEO);

        // TODO 投标类别审核，记录审核日志
    }

}
