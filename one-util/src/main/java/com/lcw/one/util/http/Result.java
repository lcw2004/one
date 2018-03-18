package com.lcw.one.util.http;

public class Result {

    private static final ResponseMessage RESPONSE_MESSAGE_SUCCESS = new ResponseMessage(ResponseMessageCodeEnum.SUCCESS.getCode(), "");

    public static ResponseMessage success() {
        return RESPONSE_MESSAGE_SUCCESS;
    }

    public static <T> ResponseMessage<T> success(T t) {
        return new ResponseMessage(ResponseMessageCodeEnum.SUCCESS.getCode(), "", t);
    }

    public static ResponseMessage error() {
        return error("");
    }

    public static ResponseMessage error(String message) {
        return error(ResponseMessageCodeEnum.ERROR.getCode(), message);
    }

    public static ResponseMessage error(String code, String message) {
        return message(code, message);
    }

    public static <T> ResponseMessage error(String code, String message, T t) {
        return message(code, message, t);
    }
    public static ResponseMessage message(String code, String message) {
        return message(code, message, null);
    }

    public static <T> ResponseMessage<T> message(String code, String message, T t) {
        return new ResponseMessage(code, message, t);
    }
}
