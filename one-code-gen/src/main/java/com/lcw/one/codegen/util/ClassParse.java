package com.lcw.one.codegen.util;

import com.lcw.one.codegen.bean.EntityMetaInfo;
import com.lcw.one.util.exception.OneBaseException;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 2017-04-15.
 * @auth Licw
 */
public class ClassParse {

    public static List<EntityMetaInfo> parse(String packageName) {
        List<EntityMetaInfo> classBeanList = new ArrayList<>();
        List<Class<?>> classList = ClassUtil.getClasses(packageName);
        for (Class<?> clazz : classList) {
            classBeanList.add(parse(clazz));
        }
        return classBeanList;
    }

    public static EntityMetaInfo parse(Class clazz) {
        EntityMetaInfo entityMetaInfo = null;
        boolean isEntity = clazz.isAnnotationPresent(Entity.class);
        if (isEntity) {
            entityMetaInfo = new EntityMetaInfo();
            entityMetaInfo.setClassName(clazz.getName());
            entityMetaInfo.setSimpleClassName(clazz.getSimpleName());
            entityMetaInfo.setIdFieldClass(getIdType(clazz));
            entityMetaInfo.setTableName(getTableName(clazz));
            entityMetaInfo.setRelationTable(isHasTwoId(clazz));
        }
        return entityMetaInfo;
    }


    public static String getTableName(Class clazz) {
        if (clazz.isAnnotationPresent(Table.class)) {
            Table annotation = (Table) clazz.getAnnotation(Table.class);
            return annotation.name();
        } else {
            return null;
        }
    }

    public static boolean isHasTwoId(Class clazz) {
        int idCount = 0;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Id.class)) {
                idCount++;
            }
        }
        return idCount >= 2;
    }

    public static Class getIdType(Class clazz) {
        Method idMethod = null;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Id.class)) {
                idMethod = method;
            }
        }

        if (idMethod == null) {
            throw new OneBaseException(clazz.getName() + ", Id is not assign");
        }

        return idMethod.getReturnType();
    }

}
