package com.lcw.one.msg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.lang.String;

/**
 * 系统消息类型
 */
@Entity
@Table(name = "msg_type")
public class MsgTypeEO {

	/**
	 * 消息类型编码
	 */
	@Id
	@Column(name = "msg_type_code")
	private String msgTypeCode;

	/**
	 * 消息类型名称
	 */
	@Basic
	@Column(name = "msg_type_name")
	private String msgTypeName;


    public String getMsgTypeCode () {
        return this.msgTypeCode;
    }

    public void setMsgTypeCode (String msgTypeCode) {
        this.msgTypeCode = msgTypeCode;
    }

    public String getMsgTypeName () {
        return this.msgTypeName;
    }

    public void setMsgTypeName (String msgTypeName) {
        this.msgTypeName = msgTypeName;
    }

}