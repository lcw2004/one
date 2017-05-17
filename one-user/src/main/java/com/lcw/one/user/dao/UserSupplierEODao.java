package com.lcw.one.user.dao;

import com.lcw.one.user.bean.SupplierQueryCondition;
import com.lcw.one.user.entity.UserSupplierEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserSupplierEODao extends BaseRepositoryImpl<UserSupplierEO, String> {

    @Autowired
    public UserSupplierEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserSupplierEO.class, entityManager), entityManager);
    }

    public PageInfo<UserSupplierEO> pageUserSupplierEOByAuditUserId(PageInfo pageInfo, SupplierQueryCondition condition, String auditUserId) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct s from BasePurchaseTypeAuditUserRelationEO up, UserSupplierPurchaseTypeRelationEO sp, UserSupplierEO s " );
        sql.append(" where sp.purchaseTypeId = up.purchaseTypeId and s.supplierId = sp.supplierId ");
        sql.append(" and up.userId = :auditUserId and sp.status = 1 ");
        buildQueryCondition(condition, params, sql);
        params.put("auditUserId", auditUserId);
        return page(pageInfo, sql.toString(), params);
    }

    public PageInfo<UserSupplierEO> page(PageInfo pageInfo, SupplierQueryCondition condition) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" from UserSupplierEO s where 1=1 ");
        buildQueryCondition(condition, params, sql);
        if (StringUtils.isNotEmpty(condition.getSupplierStatus())) {
            sql.append(" and s.status = :supplierStatus");
            params.put("supplierStatus", Integer.valueOf(condition.getSupplierStatus()));
        } else {
            sql.append(" and s.status != -1 and s.status != 0");
        }
        return page(pageInfo, sql.toString(), params);
    }

    public UserSupplierEO getUserSupplierEOByName(String name) {
        return getByHql("from UserSupplierEO where name = ?1", name);
    }

    public void updateSupplierState(String supplierId, Integer newState) {
        executeUpdate("update UserSupplierEO set status = ?1 where supplierId = ?2", newState, supplierId);
    }

    public UserSupplierEO getUserSupplierEOByPrincipalUserUserId(String userId) {
        return getByHql("from UserSupplierEO where principalUser.userId = ?1", userId);
    }

    private void buildQueryCondition(SupplierQueryCondition condition, Map<String, Object> params, StringBuilder sql) {
        // 负责人信息
        if (StringUtils.isNotEmpty(condition.getPrincipalUserLikeName())) {
            sql.append(" and ( ");
            sql.append(" s.principalUser.name like :principalUserLikeName ");
            sql.append(" or s.principalUser.account like :principalUserLikeName ");
            sql.append(" or s.principalUser.identityNumber = :principalUserLikeName ");
            sql.append(" or s.principalUser.userContactInfo.mobile like :principalUserLikeName ");
            sql.append(" or s.principalUser.userContactInfo.email like :principalUserLikeName ");
            sql.append(" or s.principalUser.userContactInfo.phone like :principalUserLikeName ");
            sql.append(" ) ");
            params.put("principalUserLikeName", "%" + condition.getPrincipalUserLikeName() + "%");
        }

        // 供应商信息
        if (StringUtils.isNotEmpty(condition.getSupplierLikeName())) {
            sql.append(" and ( ");
            sql.append(" s.name like :supplierLikeName ");
            sql.append(" or s.shortName like :supplierLikeName ");
            sql.append(" or s.usedName like :supplierLikeName ");
            sql.append(" ) ");
            params.put("supplierLikeName", "%" + condition.getSupplierLikeName() + "%");
        }
        if (StringUtils.isNotEmpty(condition.getLegalPersonLikeName())) {
            sql.append(" and ( ");
            sql.append(" and s.legalPersonName like :legalPersonLikeName ");
            sql.append(" and s.legalPersonNumber like :legalPersonLikeName ");
            sql.append(" ) ");
            params.put("legalPersonLikeName", condition.getLegalPersonLikeName());
        }
        if (StringUtils.isNotEmpty(condition.getRegistryTimeFrom())) {
            sql.append(" and s.createTime >= :registryTimeFrom ");
            params.put("registryTimeFrom", DateUtils.stringToDate(condition.getRegistryTimeFrom(), DateUtils.yyyy_MM_dd_EN));
        }
        if (StringUtils.isNotEmpty(condition.getRegistryTimeTo())) {
            sql.append(" and s.createTime <= :registryTimeTo ");
            params.put("registryTimeTo", DateUtils.stringToDate(condition.getRegistryTimeTo(), DateUtils.yyyy_MM_dd_EN));
        }

        if (StringUtils.isNotEmpty(condition.getOfficeCode())) {
            sql.append(" and s.officeCode = :officeCode ");
            params.put("officeCode", condition.getOfficeCode());
        }
        if (StringUtils.isNotEmpty(condition.getOfficeAddress())) {
            sql.append(" and s.officeAddress = :officeAddress ");
            params.put("officeAddress", condition.getOfficeAddress());
        }
        if (StringUtils.isNotEmpty(condition.getSupplyArea())) {
            sql.append(" and s.supplyArea = :supplyArea ");
            params.put("supplyArea", condition.getSupplyArea());
        }
    }
}