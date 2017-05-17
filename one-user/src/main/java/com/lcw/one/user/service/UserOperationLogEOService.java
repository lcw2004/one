package com.lcw.one.user.service;

import com.lcw.one.user.entity.UserOperationLogEO;
import com.lcw.one.user.dao.UserOperationLogEODao;
import com.lcw.one.util.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOperationLogEOService extends CrudService<UserOperationLogEODao, UserOperationLogEO> {

}
