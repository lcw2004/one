/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.modules.cms.dao;

import com.lcw.one.common.persistence.BaseDao;
import com.lcw.one.common.persistence.Parameter;
import com.lcw.one.modules.cms.entity.Link;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 链接DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
@Repository
public class LinkDao extends BaseDao<Link> {
	
	public List<Link> findByIdIn(Long[] ids){
		return find("front Like where id in (:p1)", new Parameter(new Object[]{ids}));
	}
	
	public int updateExpiredWeight(){
		return update("update Link set weight=0 where weight > 0 and weightDate < current_timestamp()");
	}
}
