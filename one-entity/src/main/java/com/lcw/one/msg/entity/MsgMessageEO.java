package com.lcw.one.msg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;

/**
 * 消息
 */
@Entity
@Table(name = "msg_message")
public class MsgMessageEO {

	/**
	 * 消息ID
	 */
	@Id
	@Column(name = "message_id")
	private String messageId;

	/**
	 * 消息模板ID
	 */
	@Basic
	@Column(name = "template_id")
	private String templateId;

	/**
	 * 消息标题
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
	 * 发送时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Basic
	@Column(name = "send_time")
	private Date sendTime;

	/**
	 * 目标用户类型
	 */
	@Basic
	@Column(name = "target_type")
	private Integer targetType;


    public String getMessageId () {
        return this.messageId;
    }

    public void setMessageId (String messageId) {
        this.messageId = messageId;
    }

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

    public Date getSendTime () {
        return this.sendTime;
    }

    public void setSendTime (Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getTargetType () {
        return this.targetType;
    }

    public void setTargetType (Integer targetType) {
        this.targetType = targetType;
    }

}