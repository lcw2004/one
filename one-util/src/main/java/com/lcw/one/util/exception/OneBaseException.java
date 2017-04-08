package com.lcw.one.util.exception;

public class OneBaseException extends RuntimeException {

    private String errorCode;

    public OneBaseException() {
    }

    public OneBaseException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
