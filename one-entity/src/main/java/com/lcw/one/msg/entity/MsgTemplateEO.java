package com.lcw.one.msg.entity;

import javax.persistence.*;
import java.lang.String;
import java.lang.Integer;
import java.util.List;

/**
 * 系统消息模板
 */
@Entity
@Table(name = "msg_template")
public class MsgTemplateEO {

	/**
	 * 消息模板ID
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
	 * 消息内容
	 */
	@Basic
	@Column(name = "content")
	private String content;

	/**
	 * 状态
	 */
	@Basic
	@Column(name = "status")
	private Integer status;


    @Basic
    @Column(name = "msg_type_code")
	private String msgTypeCode;

	@ManyToOne
    @JoinColumn(name = "msg_type_code", insertable = false, updatable = false)
    private MsgTypeEO msgType;

	@Transient
	private List<String> channelIdList;

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

    public String getContent () {
        return this.content;
    }

    public void setContent (String content) {
        this.content = content;
    }

    public Integer getStatus () {
        return this.status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }

    public String getMsgTypeCode() {
        return msgTypeCode;
    }

    public void setMsgTypeCode(String msgTypeCode) {
        this.msgTypeCode = msgTypeCode;
    }

    public MsgTypeEO getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgTypeEO msgType) {
        this.msgType = msgType;
    }

    public List<String> getChannelIdList() {
        return channelIdList;
    }

    public void setChannelIdList(List<String> channelIdList) {
        this.channelIdList = channelIdList;
    }
}