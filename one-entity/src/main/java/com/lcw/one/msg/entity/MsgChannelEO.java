package com.lcw.one.msg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.lang.String;

/**
 * 消息渠道
 */
@Entity
@Table(name = "msg_channel")
public class MsgChannelEO {

	/**
	 * 渠道编号
	 */
	@Id
	@Column(name = "channel_id")
	private String channelId;

	/**
	 * 渠道名称
	 */
	@Basic
	@Column(name = "channel_name")
	private String channelName;


    public String getChannelId () {
        return this.channelId;
    }

    public void setChannelId (String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName () {
        return this.channelName;
    }

    public void setChannelName (String channelName) {
        this.channelName = channelName;
    }

}