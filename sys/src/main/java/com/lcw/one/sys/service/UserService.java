/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.sys.service;

import com.lcw.one.sys.dao.UserDao;
import com.lcw.one.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 字典Service
 * @author ThinkGem
 * @version 2013-5-29
 */
@Service
@Transactional(readOnly = true)
public class UserService extends BaseService {

	@Autowired
	private UserDao userDao;
	
	public User get(String id) {
		return userDao.get(id);
	}

	@Transactional(readOnly = false)
	public void save(User dict) {
		userDao.save(dict);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		userDao.deleteById(id);
	}

}
