package com.lcw.one.file.store;

import com.lcw.one.file.bean.constant.FileStoreType;
import com.lcw.one.file.bean.constant.FileStoreTypeEnum;
import com.lcw.one.file.store.impl.AliyunOSSStoreImpl;
import com.lcw.one.file.store.impl.LocalFileStoreImpl;
import com.lcw.one.util.utils.SpringContextHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileStoreFactory {

    @Value("${one.file.type}")
    private String fileStoreType;

    public IFileStore defaultFileStore() {
        return instance(fileStoreType);
    }

    public IFileStore instance(String fileStoreType) {
        Class clazz = LocalFileStoreImpl.class;
        if (fileStoreType.equals(FileStoreTypeEnum.OSS.getValue())) {
            clazz = AliyunOSSStoreImpl.class;
        }
        return (IFileStore) SpringContextHolder.getApplicationContext().getBean(clazz);
    }

}
