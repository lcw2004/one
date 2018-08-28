/**
 * Copyright (c) 2005-2012 springside.org.cn
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.util.utils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

/**
 * 关于异常的工具类.
 *
 * @author calvin
 * @version 2013-01-15
 */
public class Exceptions {

    /**
     * 将CheckedException转换为UncheckedException.
     */
    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e);
        }
    }

    /**
     * 将ErrorStack转化为String.
     */
    public static String getStackTraceAsString(Exception e) {
        String stackTrace = null;
        StringWriter stringWriter = null;
        try {
            stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            stackTrace = stringWriter.toString();
        } finally {
            IOUtils.closeQuietly(stringWriter);
        }
        return stackTrace;
    }

    /**
     * 判断异常是否由某些底层的异常引起.
     */
    public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
        Throwable cause = ex.getCause();
        while (cause != null) {
            for (Class<? extends Exception> causeClass : causeExceptionClasses) {
                if (causeClass.isInstance(cause)) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }

    /**
     * 获取第一个验证失败的信息
     *
     * @param exception
     * @return
     */
    public static ConstraintViolation getFirstError(ConstraintViolationException exception) {
        ConstraintViolation violation = null;
        Set<ConstraintViolation<?>> set = exception.getConstraintViolations();
        if (CollectionUtils.isNotEmpty(set)) {
            violation = set.iterator().next();
        }
        return violation;
    }

    public static String getMessage(Exception e) {
        String message = e.getMessage();
        if (StringUtils.isEmpty(message) && e.getCause() != null) {
            message = e.getCause().getMessage();
        }
        if (StringUtils.isEmpty(message)) {
            message = getStackTraceAsString(e);
        }
        return message;
    }
}
