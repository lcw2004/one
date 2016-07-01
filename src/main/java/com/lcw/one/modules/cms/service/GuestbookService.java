/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.modules.cms.service;

import com.google.common.collect.Lists;
import com.lcw.one.common.persistence.Page;
import com.lcw.one.common.service.BaseService;
import com.lcw.one.modules.cms.dao.GuestbookDao;
import com.lcw.one.modules.cms.entity.Guestbook;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.search.BooleanClause.Occur;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 留言Service
 * @author ThinkGem
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class GuestbookService extends BaseService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	public Guestbook get(String id) {
		return guestbookDao.get(id);
	}
	
	public Page<Guestbook> find(Page<Guestbook> page, Guestbook guestbook) {
		DetachedCriteria dc = guestbookDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(guestbook.getType())){
			dc.add(Restrictions.eq("type", guestbook.getType()));
		}
		if (StringUtils.isNotEmpty(guestbook.getContent())){
			dc.add(Restrictions.like("content", "%"+guestbook.getContent()+"%"));
		}
		dc.add(Restrictions.eq(Guestbook.FIELD_DEL_FLAG, guestbook.getDelFlag()));
		dc.addOrder(Order.desc("id"));
		return guestbookDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Guestbook guestbook) {
		guestbookDao.save(guestbook);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id, Boolean isRe) {
		guestbookDao.updateDelFlag(id, isRe!=null&&isRe?Guestbook.DEL_FLAG_AUDIT:Guestbook.DEL_FLAG_DELETE);
	}
	
	/**
	 * 更新索引
	 */
	public void createIndex(){
		guestbookDao.createIndex();
	}
	
	/**
	 * 全文检索
	 */
	public Page<Guestbook> search(Page<Guestbook> page, String q, String beginDate, String endDate){
		
		// 设置查询条件
		BooleanQuery query = guestbookDao.getFullTextQuery(q, "name","content","reContent");
		
		// 设置过滤条件
		List<BooleanClause> bcList = Lists.newArrayList();

		bcList.add(new BooleanClause(new TermQuery(new Term(Guestbook.FIELD_DEL_FLAG, Guestbook.DEL_FLAG_NORMAL)), Occur.MUST));
		
		if (StringUtils.isNotBlank(beginDate) && StringUtils.isNotBlank(endDate)) {   
			bcList.add(new BooleanClause(new TermRangeQuery("createDate", beginDate.replaceAll("-", ""),
					endDate.replaceAll("-", ""), true, true), Occur.MUST));
		}

		bcList.add(new BooleanClause(new TermQuery(new Term("type", "1")), Occur.SHOULD));
		bcList.add(new BooleanClause(new TermQuery(new Term("type", "2")), Occur.SHOULD));
		bcList.add(new BooleanClause(new TermQuery(new Term("type", "3")), Occur.SHOULD));
		bcList.add(new BooleanClause(new TermQuery(new Term("type", "4")), Occur.SHOULD));
		
		BooleanQuery queryFilter = guestbookDao.getFullTextQuery((BooleanClause[])bcList.toArray(new BooleanClause[bcList.size()]));

		System.out.println(queryFilter);
		
		// 设置排序（默认相识度排序）
		Sort sort = null;//new Sort(new SortField("updateDate", SortField.DOC, true));
		// 全文检索
		guestbookDao.search(page, query, queryFilter, sort);
		// 关键字高亮
		guestbookDao.keywordsHighlight(query, page.getList(), 30, "name");
		guestbookDao.keywordsHighlight(query, page.getList(), 1300, "content");
		guestbookDao.keywordsHighlight(query, page.getList(), 1300, "reContent");
		
		return page;
	}
	
}
