package com.lcw.one.msg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lcw.one.msg.constant.MsgAnnounceStatusEnum;
import com.lcw.one.user.entity.UserInfoEO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;

/**
 * 公告
 */
@Entity
@Table(name = "msg_announce")
public class MsgAnnounceEO {

	/**
	 * 公告ID
	 */
	@Id
	@Column(name = "announce_id")
	private String announceId;

	/**
	 * 标题
	 */
	@Basic
	@Column(name = "title")
	private String title;

	/**
	 * 内容
	 */
	@Basic
	@Column(name = "content")
	private String content;

	/**
	 * 目标用户类型
	 */
	@Basic
	@Column(name = "target_type")
	private Integer targetType;

	/**
	 * 目标用户
	 */
	@Basic
	@Column(name = "target")
	private String target;

	/**
	 * 创建人
	 */
	@Basic
	@Column(name = "create_user_id")
	private String createUserId;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Basic
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 阅读次数
	 */
	@Basic
	@Column(name = "read_count")
	private Integer readCount;

	/**
	 * 状态
	 */
	@Basic
	@Column(name = "status")
	private Integer status;

	/**
	 * 是否置顶
	 */
	@Basic
	@Column(name = "is_top")
	private Integer isTop;

	@ManyToOne
	@JoinColumn(name = "create_user_id", insertable = false, updatable = false)
	private UserInfoEO createUser;

	@Transient
	private String statusLabel;

    public String getAnnounceId () {
        return this.announceId;
    }

    public void setAnnounceId (String announceId) {
        this.announceId = announceId;
    }

    public String getTitle () {
        return this.title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateUserId () {
        return this.createUserId;
    }

    public void setCreateUserId (String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime () {
        return this.createTime;
    }

    public void setCreateTime (Date createTime) {
        this.createTime = createTime;
    }

    public Integer getReadCount () {
        return this.readCount;
    }

    public void setReadCount (Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getStatus () {
        return this.status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }

    public Integer getIsTop () {
        return this.isTop;
    }

    public void setIsTop (Integer isTop) {
        this.isTop = isTop;
    }

	public Integer getTargetType() {
		return targetType;
	}

	public void setTargetType(Integer targetType) {
		this.targetType = targetType;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getStatusLabel() {
		MsgAnnounceStatusEnum statusEnum = MsgAnnounceStatusEnum.get(status);
		if (status != null) {
			statusLabel = statusEnum.getLabel();
		}
		return statusLabel;
	}

	public void setStatusLabel(String statusLabel) {
		this.statusLabel = statusLabel;
	}

	public UserInfoEO getCreateUser() {
		return createUser;
	}

	public void setCreateUser(UserInfoEO createUser) {
		this.createUser = createUser;
	}
}