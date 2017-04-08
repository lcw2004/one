package com.lcw.one.common.advice;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ShiroExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ShiroExceptionAdvice.class);

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AuthenticationException.class, UnknownAccountException.class,
            UnauthenticatedException.class, IncorrectCredentialsException.class,
            UnauthorizedException.class})
    public void unauthorized() {
        logger.info("catch UnknownAccountException");
    }

}
