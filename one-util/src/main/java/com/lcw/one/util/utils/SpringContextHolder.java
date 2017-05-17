/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.util.utils;

import org.apache.commons.lang3.Validate;
import org.springframework.context.ApplicationContext;

public class SpringContextHolder {

    private static ApplicationContext context;

    public static <T> T getBean(Class<T> clazz) {
        try {
            return getApplicationContext().getBean(clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T getBean(String name) {
        return (T) getApplicationContext().getBean(name);
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        Validate.validState(context != null, "applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
        return context;
    }
}