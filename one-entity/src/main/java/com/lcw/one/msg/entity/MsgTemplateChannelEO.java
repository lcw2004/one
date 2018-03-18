package com.lcw.one.msg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.lang.String;

/**
 * 消息模板渠道
 */
@Entity
@Table(name = "msg_template_channel")
@IdClass(MsgTemplateChannelEOPK.class)
public class MsgTemplateChannelEO {

	/**
	 * 消息模板ID
	 */
	@Id
	@Column(name = "template_id")
	private String templateId;

	/**
	 * 渠道编号
	 */
	@Id
	@Column(name = "channel_id")
	private String channelId;


    public String getTemplateId () {
        return this.templateId;
    }

    public void setTemplateId (String templateId) {
        this.templateId = templateId;
    }

    public String getChannelId () {
        return this.channelId;
    }

    public void setChannelId (String channelId) {
        this.channelId = channelId;
    }

}