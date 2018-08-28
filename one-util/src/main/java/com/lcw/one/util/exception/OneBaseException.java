package com.lcw.one.util.exception;

/**
 * 基础异常
 */
public class OneBaseException extends RuntimeException {

    private String errorCode;

    public OneBaseException() {
    }

    public OneBaseException(String message) {
        this("-1", message);
    }

    public OneBaseException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public OneBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
