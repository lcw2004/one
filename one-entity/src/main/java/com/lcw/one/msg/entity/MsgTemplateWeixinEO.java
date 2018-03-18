package com.lcw.one.msg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.lang.String;
import java.lang.Integer;

/**
 * 微信消息模板
 */
@Entity
@Table(name = "msg_template_weixin")
public class MsgTemplateWeixinEO {

	/**
	 * 微信消息模板ID
	 */
	@Id
	@Column(name = "template_id")
	private String templateId;

	/**
	 * 消息模板标题
	 */
	@Basic
	@Column(name = "title")
	private String title;

	/**
	 * 状态
	 */
	@Basic
	@Column(name = "status")
	private Integer status;

	/**
	 * 微信模板编号
	 */
	@Basic
	@Column(name = "wx_template_id")
	private String wxTemplateId;

	/**
	 * 微信模板头部
	 */
	@Basic
	@Column(name = "wx_template_head")
	private String wxTemplateHead;

	/**
	 * 微信模板尾部
	 */
	@Basic
	@Column(name = "wx_template_tail")
	private String wxTemplateTail;


    public String getTemplateId () {
        return this.templateId;
    }

    public void setTemplateId (String templateId) {
        this.templateId = templateId;
    }

    public String getTitle () {
        return this.title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public Integer getStatus () {
        return this.status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }

    public String getWxTemplateId () {
        return this.wxTemplateId;
    }

    public void setWxTemplateId (String wxTemplateId) {
        this.wxTemplateId = wxTemplateId;
    }

    public String getWxTemplateHead () {
        return this.wxTemplateHead;
    }

    public void setWxTemplateHead (String wxTemplateHead) {
        this.wxTemplateHead = wxTemplateHead;
    }

    public String getWxTemplateTail () {
        return this.wxTemplateTail;
    }

    public void setWxTemplateTail (String wxTemplateTail) {
        this.wxTemplateTail = wxTemplateTail;
    }

}