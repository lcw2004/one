package com.lcw.one.sys.entity;

import javax.persistence.*;
import java.lang.String;
import java.lang.Integer;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统首页组件
 */
@Entity
@Table(name = "sys_home_component")
public class SysHomeComponentEO {

	/**
	 * 组件编号
	 */
	@Id
	@Column(name = "component_id")
	private String componentId;

	/**
	 * 组件标题
	 */
	@Basic
	@Column(name = "component_title")
	private String componentTitle;

	/**
	 * 组件名称
	 */
	@Basic
	@Column(name = "component_name")
	private String componentName;

	/**
	 * 组件链接
	 */
	@Basic
	@Column(name = "component_link")
	private String componentLink;

	/**
	 * 组件类型
	 */
	@Basic
	@Column(name = "component_type")
	private Integer componentType;

	@Basic
    @Column(name = "config")
    private String config;

    @Transient
	private Map<String, Object> result = new HashMap<>();

    public String getComponentId () {
        return this.componentId;
    }

    public void setComponentId (String componentId) {
        this.componentId = componentId;
    }

    public String getComponentTitle () {
        return this.componentTitle;
    }

    public void setComponentTitle (String componentTitle) {
        this.componentTitle = componentTitle;
    }

    public String getComponentName () {
        return this.componentName;
    }

    public void setComponentName (String componentName) {
        this.componentName = componentName;
    }

    public String getComponentLink () {
        return this.componentLink;
    }

    public void setComponentLink (String componentLink) {
        this.componentLink = componentLink;
    }

    public Integer getComponentType () {
        return this.componentType;
    }

    public void setComponentType (Integer componentType) {
        this.componentType = componentType;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}