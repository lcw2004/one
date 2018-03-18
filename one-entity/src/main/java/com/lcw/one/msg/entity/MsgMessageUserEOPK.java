package com.lcw.one.msg.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;

public class MsgMessageUserEOPK implements Serializable {

    @Id
    @Column(name = "message_id")
    private String messageId;

    @Id
    @Column(name = "user_id")
    private String userId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MsgMessageUserEOPK that = (MsgMessageUserEOPK) o;

        if (messageId != null ? !messageId.equals(that.messageId) : that.messageId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (messageId != null ? messageId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

}