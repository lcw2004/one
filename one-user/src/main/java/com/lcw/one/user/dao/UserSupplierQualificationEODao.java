package com.lcw.one.user.dao;

import com.lcw.one.sys.entity.SysFileEO;
import com.lcw.one.user.entity.UserSupplierQualificationEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class UserSupplierQualificationEODao extends BaseRepositoryImpl<UserSupplierQualificationEO, String> {

    @Autowired
    public UserSupplierQualificationEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserSupplierQualificationEO.class, entityManager), entityManager);
    }

    public List<UserSupplierQualificationEO> listBySupplierId(String supplierId) {
        return list("from UserSupplierQualificationEO where supplierId = ?1", supplierId);
    }

}