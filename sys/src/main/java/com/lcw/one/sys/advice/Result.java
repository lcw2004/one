package com.lcw.one.sys.advice;

public class Result {
    private static final ResponseMessage RESPONSE_MESSAGE_SUCCESS = new ResponseMessage(ResponseCodeEnum.SUCCESS.getCode(), "");

    public static ResponseMessage success() {
        return RESPONSE_MESSAGE_SUCCESS;
    }

    public static ResponseMessage error(String message) {
        return new ResponseMessage(ResponseCodeEnum.ERROR.getCode(), message);
    }

    public static ResponseMessage error(String code, String message) {
        return new ResponseMessage(code, message);
    }
}
