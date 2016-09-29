package com.lcw.one.common.web;

public class ResponseMessage {

    public static final ResponseMessage SUCCESS = new ResponseMessage("0000", "成功");

    private String code;
    private String desc;
    private Object data;

    public ResponseMessage() {
    }

    public ResponseMessage(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public ResponseMessage(String code, String desc, Object data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
