package com.lcw.one.user.service;

import com.lcw.one.user.dao.UserSupplierPurchaseTypeRelationEODao;
import com.lcw.one.user.entity.UserSupplierPurchaseTypeRelationEO;
import com.lcw.one.user.entity.UserSupplierQualificationEO;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 2017-05-15.
 * @auth Licw
 */
@Service
public class UserSupplierPurchaseTypeRelationEOService extends CrudService<UserSupplierPurchaseTypeRelationEODao, UserSupplierPurchaseTypeRelationEO> {

    public void deletePurchaseTypeBySupplierId(String supplierId) {
        dao.deletePurchaseTypeBySupplierId(supplierId);
    }

    /**
     * 如果为空，则表示清空了数据，删除所有的。
     * 如果非空，则删除不在列表中的数据
     * @param relationEOList
     */
    public void deletePurchaseTypeBySupplierIdNotInList(String supplierId, List<UserSupplierPurchaseTypeRelationEO> relationEOList) {
        if(CollectionUtils.isEmpty(relationEOList)) {
            deletePurchaseTypeBySupplierId(supplierId);
        } else {
            List<String> idList = new ArrayList<>();
            for(UserSupplierPurchaseTypeRelationEO relationEO: relationEOList) {
                idList.add(relationEO.getPurchaseTypeId());
            }

            dao.deletePurchaseTypeBySupplierIdNotInList(supplierId, idList);
        }
    }
}
