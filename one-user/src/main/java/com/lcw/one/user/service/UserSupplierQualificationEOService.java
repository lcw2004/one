package com.lcw.one.user.service;

import com.lcw.one.user.entity.UserSupplierQualificationEO;
import com.lcw.one.user.dao.UserSupplierQualificationEODao;
import com.lcw.one.util.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSupplierQualificationEOService extends CrudService<UserSupplierQualificationEODao, UserSupplierQualificationEO> {

    public List<UserSupplierQualificationEO> listBySupplierId(String supplierId) {
        return dao.listBySupplierId(supplierId);
    }
}
