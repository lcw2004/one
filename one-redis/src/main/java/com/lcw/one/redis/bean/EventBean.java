package com.lcw.one.redis.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EventBean<T> {

    private String eventId;
    private String eventCode;
    private String eventName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Map<String, Object> eventParams;
    private T eventBody;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Map<String, Object> getEventParams() {
        return eventParams;
    }

    public void setEventParams(Map<String, Object> eventParams) {
        this.eventParams = eventParams;
    }

    public void addEventParam(String key, Object value) {
        if (this.getEventParams() == null) {
            this.setEventParams(new HashMap<String, Object>());
        }
        this.eventParams.put(key, value);
    }

    public Object getEventParam(String key) {
        Object value = null;
        if (this.eventParams.containsKey(key)) {
            value = this.eventParams.get(key);
        }
        return value;
    }

    public T getEventBody() {
        return eventBody;
    }

    public void setEventBody(T eventBody) {
        this.eventBody = eventBody;
    }
}
