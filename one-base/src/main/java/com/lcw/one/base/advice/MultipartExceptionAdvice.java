package com.lcw.one.base.advice;

import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

/**
 * 文件上传异常拦截器
 */
@ControllerAdvice
public class MultipartExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(MultipartExceptionAdvice.class);

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    public ResponseMessage handlerOneBaseException(MultipartException exception) {
        logger.warn(exception.getMessage(), exception);

        // 这个异常是使用Tomcat的时候，如果未配置temp路径，运行一段时间会抛出这个异常。现在配置了temp路径，最近没出现过这个异常了。
        if(exception.getMessage().contains("temporary upload location")) {
            return Result.error("系统错误，请重启服务。");
        }

        return Result.error("文件大小超过限制(" + maxFileSize.toUpperCase() + ")");
    }

}
