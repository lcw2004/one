package com.lcw.one.util.utils;

import org.springframework.web.servlet.ModelAndView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @version 2017-11-05.
 * @auth Licw
 */
public class AspectHelper {

    public static Method getMethodByClassAndName(Class c, String methodName) {
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

    public static Annotation getAnnotationByClass(Class cls, Class annoClass) {
        Annotation[] all = cls.getAnnotations();
        for (Annotation annotation : all) {
            if (annotation.annotationType() == annoClass) {
                return annotation;
            }
        }
        return null;
    }

    public static Annotation getAnnotationByMethod(Method method, Class annoClass) {
        Annotation[] all = method.getAnnotations();
        for (Annotation annotation : all) {
            if (annotation.annotationType() == annoClass) {
                return annotation;
            }
        }
        return null;
    }

    public static Object getReturnObj(Class c, String returnUrl) {
        if (c == ModelAndView.class) {
            return new ModelAndView(returnUrl);
        }
        return returnUrl;
    }
}
