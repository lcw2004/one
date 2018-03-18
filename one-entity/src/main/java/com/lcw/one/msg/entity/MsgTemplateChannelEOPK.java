package com.lcw.one.msg.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;

public class MsgTemplateChannelEOPK implements Serializable {

    @Id
    @Column(name = "template_id")
    private String templateId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MsgTemplateChannelEOPK that = (MsgTemplateChannelEOPK) o;

        if (templateId != null ? !templateId.equals(that.templateId) : that.templateId != null) return false;
        if (channelId != null ? !channelId.equals(that.channelId) : that.channelId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (templateId != null ? templateId.hashCode() : 0);
        result = 31 * result + (channelId != null ? channelId.hashCode() : 0);
        return result;
    }

}