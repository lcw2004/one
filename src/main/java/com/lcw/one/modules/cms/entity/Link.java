/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.modules.cms.entity;

import com.lcw.one.common.persistence.IdEntity;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 链接Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
@Entity
@Table(name = "cms_link")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Link extends IdEntity<Category> {
	
	private static final long serialVersionUID = 1L;
	private Category category;// 分类编号
	private String title;	// 链接名称
	private String color;	// 标题颜色（red：红色；green：绿色；blue：蓝色；yellow：黄色；orange：橙色）
	private String image;	// 链接图片
	private String href;	// 链接地址
	private Integer weight;	// 权重，越大越靠前
	private Date weightDate;// 权重期限，超过期限，将weight设置为0

	public Link() {
		super();
		this.weight = 0;
	}
	
	public Link(String id){
		this();
		this.id = id;
	}

	public Link(Category category){
		this();
		this.category = category;
	}

	@ManyToOne
	@JoinColumn(name="category_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Length(min=1, max=255)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Length(min=0, max=50)
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Length(min=0, max=255)
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Length(min=0, max=255)
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Date getWeightDate() {
		return weightDate;
	}

	public void setWeightDate(Date weightDate) {
		this.weightDate = weightDate;
	}
	
}