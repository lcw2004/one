package com.lcw.one.base.advice;

import com.lcw.one.util.exception.LoginInvalidException;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.ResponseMessageCodeEnum;
import com.lcw.one.util.http.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OneBaseExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(OneBaseExceptionAdvice.class);

    /**
     * 登录失效异常拦截器
     *
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(LoginInvalidException.class)
    @ResponseBody
    public ResponseMessage handlerLoginInvalidException(LoginInvalidException exception) {
        logger.warn(exception.getMessage(), exception);
        return Result.error(ResponseMessageCodeEnum.RE_LOGIN.getCode(), "登录失效");
    }

    /**
     * OneBaseException 拦截器
     *
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(OneBaseException.class)
    @ResponseBody
    public ResponseMessage handlerOneBaseException(OneBaseException exception) {
        logger.warn(exception.getMessage(), exception);
        return Result.error(exception.getErrorCode(), exception.getMessage());
    }

    /**
     * Exception 异常
     * 这种异常一般是程序写的有问题，直接抛出了Exception。前端如果遇到这种异常，需要找到错误原因并进行修复。
     *
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage handlerException(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return Result.error(ResponseMessageCodeEnum.ERROR.getCode(), "程序异常，请重试。如果重复出现请联系管理员处理！");
    }

}
