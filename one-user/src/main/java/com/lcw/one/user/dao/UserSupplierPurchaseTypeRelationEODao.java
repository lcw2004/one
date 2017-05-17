package com.lcw.one.user.dao;

import com.lcw.one.user.entity.UserSupplierPurchaseTypeRelationEO;
import com.lcw.one.baseInfo.service.BasePurchaseTypeEOService;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class UserSupplierPurchaseTypeRelationEODao extends BaseRepositoryImpl<UserSupplierPurchaseTypeRelationEO, String> {

    @Autowired
    private BasePurchaseTypeEOService basePurchaseTypeEOService;

    @Autowired
    public UserSupplierPurchaseTypeRelationEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserSupplierPurchaseTypeRelationEO.class, entityManager), entityManager);
    }

    public List<UserSupplierPurchaseTypeRelationEO> listBySupplierId(String supplierId) {
        List<UserSupplierPurchaseTypeRelationEO> relationEOList = super.list("from UserSupplierPurchaseTypeRelationEO where supplierId = ?1", supplierId);
        return relationEOList;
    }

    public UserSupplierPurchaseTypeRelationEO getBySupplierIdAndPurchaseTypeId(String supplierId, String purchaseTypeId) {
        return getByHql("from UserSupplierPurchaseTypeRelationEO where supplierId = ?1 and purchaseTypeId = ?2", supplierId, purchaseTypeId);
    }


    /**
     * 根据供应商ID删除对应的所有投标类别关联关系
     * @param supplierId
     */
    public void deletePurchaseTypeBySupplierId(String supplierId) {
        super.executeUpdate("delete from UserSupplierPurchaseTypeRelationEO where supplierId = ?1", supplierId);
    }

    public void deletePurchaseTypeBySupplierIdNotInList(String supplierId, List<String> purchaseTypeIdList) {
        super.executeUpdate("delete from UserSupplierPurchaseTypeRelationEO where supplierId = ?1 and purchaseTypeId not in ?2", supplierId, purchaseTypeIdList);
    }

}