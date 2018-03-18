package com.lcw.one.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel注解定义
 *
 * @author ThinkGem
 * @version 2013-03-10
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {

    /**
     * 导出字段名（默认调用当前字段的“get”方法，如指定导出字段为对象，请填写“对象名.对象属性”，例：“area.name”、“office.name”）
     */
    String value() default "";

    /**
     * 字段标题
     */
    String title();

    /**
     * 导出字段对齐方式（0：自动；1：靠左；2：居中；3：靠右）
     * <p>
     * 备注：Integer/Long类型设置居右对齐（align=3）
     */
    int align() default 0;

    /**
     * 在Excel中的列序号，从0开始
     */
    int column();

    /**
     * 是否可以为空
     */
    boolean nullable() default true;

    String dict() default "";
}
