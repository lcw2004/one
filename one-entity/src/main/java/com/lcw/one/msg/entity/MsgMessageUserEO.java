package com.lcw.one.msg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.lang.String;
import java.lang.Integer;

/**
 * 消息用户关联
 */
@Entity
@Table(name = "msg_message_user")
@IdClass(MsgMessageUserEOPK.class)
public class MsgMessageUserEO {

	/**
	 * 消息ID
	 */
	@Id
	@Column(name = "message_id")
	private String messageId;

	/**
	 * 用户ID
	 */
	@Id
	@Column(name = "user_id")
	private String userId;

	/**
	 * 消息状态
	 */
	@Basic
	@Column(name = "status")
	private Integer status;


    public String getMessageId () {
        return this.messageId;
    }

    public void setMessageId (String messageId) {
        this.messageId = messageId;
    }

    public String getUserId () {
        return this.userId;
    }

    public void setUserId (String userId) {
        this.userId = userId;
    }

    public Integer getStatus () {
        return this.status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }

}