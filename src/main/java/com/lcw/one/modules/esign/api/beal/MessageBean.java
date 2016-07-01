package com.lcw.one.modules.esign.api.beal;

/**
 * Created by lcw20 on 2016-06-23.
 */
public class MessageBean {

    public static final String CODE_SUCCESS = "000";
    public static final MessageBean SUCCESS = new MessageBean(CODE_SUCCESS, "成功");

    private String code;
    private String desc;
    private Object body;

    public MessageBean() {
    }

    public MessageBean(String code) {
        this.code = code;
    }

    public MessageBean(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public MessageBean(String code, String desc, Object body) {
        this.code = code;
        this.desc = desc;
        this.body = body;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}

