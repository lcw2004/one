package com.lcw.one.notify.bean;

import java.util.Date;

public class MessageBean {

    /**
     * 发送人ID
     */
    private String senderId;

    /**
     * 发送人姓名
     */
    private String senderName;

    /**
     * 发送类型
     */
    private Integer senderType;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 目标用户ID
     */
    private String targetUserId;

    /**
     * 消息目标用户类型（BidProjectChatEnum）
     */
    private Integer targetType;

    public MessageBean() {
        this.sendTime = new Date();
    }

    public MessageBean(String content) {
        this.content = content;
        this.sendTime = new Date();
    }

    public MessageBean(String senderId, String senderName, String content) {
        this.senderId = senderId;
        this.senderName = senderName;
        this.content = content;
        this.sendTime = new Date();
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Integer getSenderType() {
        return senderType;
    }

    public void setSenderType(Integer senderType) {
        this.senderType = senderType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }
}
