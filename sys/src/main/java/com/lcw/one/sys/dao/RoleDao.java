/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.sys.dao;

import com.lcw.one.common.persistence.BaseDao;
import com.lcw.one.common.persistence.Parameter;
import com.lcw.one.sys.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * 角色DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
@Repository
public class RoleDao extends BaseDao<Role> {

	public Role findByName(String name){
		return getByHql("from Role where delFlag = :p1 and name = :p2", new Parameter(Role.DEL_FLAG_NORMAL, name));
	}

//	@Query("from Role where delFlag='" + Role.DEL_FLAG_NORMAL + "' order by name")
//	public List<Role> findAllList();
//
//	@Query("select distinct r from Role r, User u where r in elements (u.roleList) and r.delFlag='" + Role.DEL_FLAG_NORMAL +
//			"' and u.delFlag='" + User.DEL_FLAG_NORMAL + "' and u.id=?1 or (r.user.id=?1 and r.delFlag='" + Role.DEL_FLAG_NORMAL +
//			"') order by r.name")
//	public List<Role> findByUserId(Long userId);

}
