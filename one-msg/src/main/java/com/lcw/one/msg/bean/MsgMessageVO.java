package com.lcw.one.msg.bean;

import com.lcw.one.msg.entity.MsgMessageEO;
import com.lcw.one.util.utils.DateUtils;

public class MsgMessageVO extends MsgMessageEO {

    private Integer status;

    private String timeDiff;

    public MsgMessageVO(MsgMessageEO msgMessageEO, Integer status) {
        this.setMessageId(msgMessageEO.getMessageId());
        this.setTemplateId(msgMessageEO.getTemplateId());
        this.setTitle(msgMessageEO.getTitle());
        this.setContent(msgMessageEO.getContent());
        this.setSendTime(msgMessageEO.getSendTime());
        this.setTargetType(msgMessageEO.getTargetType());
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTimeDiff() {
        timeDiff = DateUtils.timeDiffCn(getSendTime());
        return timeDiff;
    }

    public void setTimeDiff(String timeDiff) {
        this.timeDiff = timeDiff;
    }
}
