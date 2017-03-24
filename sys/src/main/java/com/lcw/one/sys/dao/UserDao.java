/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.sys.dao;

import com.lcw.one.common.persistence.BaseDao;
import com.lcw.one.common.persistence.Parameter;
import com.lcw.one.sys.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 用户DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
@Repository
public class UserDao extends BaseDao<User> {

    public List<User> findAllList() {
        return find("from User where delFlag=:p1 order by id", new Parameter(User.DEL_FLAG_NORMAL));
    }

    public User findByLoginName(String loginName) {
        return getByHql("from User where loginName = :p1 and delFlag = :p2", new Parameter(loginName, User.DEL_FLAG_NORMAL));
    }

    public int updatePasswordById(String newPassword, String id) {
        return update("update User set password=:p1 where id = :p2", new Parameter(newPassword, id));
    }

    public int updateLoginInfo(String loginIp, Date loginDate, String id) {
        return update("update User set loginIp=:p1, loginDate=:p2 where id = :p3", new Parameter(loginIp, loginDate, id));
    }

}
