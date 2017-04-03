package com.lcw.one.sys.advice;

public class Result {
    private static final ResponseMessage RESPONSE_MESSAGE_SUCCESS = new ResponseMessage(ResponseCodeEnum.SUCCESS.getCode(), "");

    public static ResponseMessage success() {
        return RESPONSE_MESSAGE_SUCCESS;
    }

    public static <T> ResponseMessage<T> success(T t) {
        return new ResponseMessage(ResponseCodeEnum.SUCCESS.getCode(), "", t);
    }

    public static ResponseMessage error(String message) {
        return new ResponseMessage(ResponseCodeEnum.ERROR.getCode(), message);
    }

    public static ResponseMessage error(String code, String message) {
        return new ResponseMessage(code, message);
    }

    public static <T> ResponseMessage<T> error(String code, String message, T t) {
        return new ResponseMessage(code, message, t);
    }
}
