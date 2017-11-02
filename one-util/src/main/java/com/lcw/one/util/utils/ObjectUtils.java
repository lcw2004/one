package com.lcw.one.util.utils;

import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @version 2017-04-23.
 * @auth Licw
 */
public class ObjectUtils {

    /**
     * 通过序列化接口深拷贝对象
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T extends Serializable> T cloneSerializable(T obj) {
        ByteArrayOutputStream memoryBuffer = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        T dest = null;
        try {
            out = new ObjectOutputStream(memoryBuffer);
            out.writeObject(obj);
            out.flush();
            in = new ObjectInputStream(new ByteArrayInputStream(memoryBuffer.toByteArray()));
            dest = (T) in.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
        return dest;
    }

    /**
     * 通过JSON序列化的方式深拷贝对象
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> T clone(T obj) {
        String json = GsonUtil.t2Json(obj);
        T dest = (T) GsonUtil.fromJson(json, obj.getClass());
        return dest;
    }

    public static <E> List<E> cloneAs(Collection objList, Class targetClass) {
        List<E> copyList = new ArrayList<>();
        for (Object obj : objList) {
            copyList.add((E) cloneAs(obj, targetClass));
        }
        return copyList;
    }

    /**
     * 通过JSON序列化的方式深拷贝对象为指定类
     *
     * @param obj         需要拷贝的对象
     * @param targetClass 目标对象Class
     * @param <T>
     * @return
     */
    public static <T> T cloneAs(Object obj, Class targetClass) {
        String json = GsonUtil.t2Json(obj);
        T dest = (T) GsonUtil.fromJson(json, targetClass);
        return dest;
    }

    public static <T extends Serializable> List<T> cloneSerializable(Collection<T> objList) {
        List<T> copyList = new ArrayList<>();
        for (Serializable obj : objList) {
            copyList.add((T) cloneSerializable(obj));
        }
        return copyList;
    }

    /**
     * 将对象List按指定字段值转为Map对象
     *
     * @param list 列表
     * @param fieldName 字段名
     * @param <ID>
     * @param <E>
     * @return
     */
    public static <ID, E> Map<ID, E> asMapByFiled(List<E> list, String fieldName) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        Map<ID, E> map = new HashMap<>();
        for (E e : list) {
            Object filedValue = Reflections.invokeGetter2(e, fieldName);
            map.put((ID) filedValue, e);
        }
        return map;
    }
}
