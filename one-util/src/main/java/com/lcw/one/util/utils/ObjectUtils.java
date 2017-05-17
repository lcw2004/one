package com.lcw.one.util.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @version 2017-04-23.
 * @auth Licw
 */
public class ObjectUtils {

    /**
     * 通过序列化接口深拷贝对象
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
     * 通过JSON的方式深拷贝对象
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> T clone(T obj) {
        String json = GsonUtil.t2Json(obj);
        T dest = (T) GsonUtil.fromJson(json, obj.getClass());
        return dest;
    }

    public static <T extends Serializable> List<T> cloneSerializable(Collection<T> objList) {
        List<T> copyList = new ArrayList<>();
        for (Serializable obj : objList) {
            copyList.add((T) cloneSerializable(obj));
        }
        return copyList;
    }
}
