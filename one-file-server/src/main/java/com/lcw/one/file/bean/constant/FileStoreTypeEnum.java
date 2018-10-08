package com.lcw.one.file.bean.constant;

import com.lcw.one.file.store.impl.AliyunOSSStoreImpl;
import com.lcw.one.file.store.impl.LocalFileStoreImpl;

/**
 * 文件存储类型
 */
public enum FileStoreTypeEnum {

    LOCAL("local", "本地文件存储", LocalFileStoreImpl.class), // 速度较快
    OSS("oss", "OSS存储", AliyunOSSStoreImpl.class),
    LOCAL_OSS("local-oss", "本地文件临时存储 + OSS永久存储", LocalFileStoreImpl.class); // 速度稍慢

    private String value;
    private String label;
    private Class clazz;

    FileStoreTypeEnum(String value, String label, Class clazz) {
        this.value = value;
        this.label = label;
        this.clazz = clazz;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public Class getClazz() {
        return clazz;
    }

}
