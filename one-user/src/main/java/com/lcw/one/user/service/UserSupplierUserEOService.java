package com.lcw.one.user.service;

import com.lcw.one.user.entity.UserSupplierUserEO;
import com.lcw.one.user.dao.UserSupplierUserEODao;
import com.lcw.one.util.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSupplierUserEOService extends CrudService<UserSupplierUserEODao, UserSupplierUserEO> {

    public UserSupplierUserEO get(String supplierId, String userId) {
        return dao.getByHql("from UserSupplierUserEO where supplierId = ?1 and userId = ?2", supplierId, userId);
    }
}
