package com.lcw.one.user.service;

import com.lcw.one.user.bean.ManageUserQueryCondition;
import com.lcw.one.user.bean.UserQueryCondition;
import com.lcw.one.user.dao.UserManagerEODao;
import com.lcw.one.user.entity.UserManagerEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.service.CrudService;
import org.springframework.stereotype.Service;

@Service
public class UserManagerEOService extends CrudService<UserManagerEODao, UserManagerEO> {

    public PageInfo<UserManagerEO> page(ManageUserQueryCondition userQueryCondition) {
        return dao.page(userQueryCondition);
    }

}
