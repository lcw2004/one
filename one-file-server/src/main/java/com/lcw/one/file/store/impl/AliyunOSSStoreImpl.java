package com.lcw.one.file.store.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.lcw.one.file.bean.constant.FileStoreTypeEnum;
import com.lcw.one.file.store.IFileStore;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.utils.FileUtil;
import com.lcw.one.util.utils.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class AliyunOSSStoreImpl implements IFileStore {

    @Value("${one.file.oss.endpoint}")
    private String endpoint;

    @Value("${one.file.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${one.file.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${one.file.oss.bucketName}")
    private String bucketName;

    @Override
    public String getStoreType() {
        return FileStoreTypeEnum.OSS.getValue();
    }

    @Override
    public void storeFile(InputStream is, String relativePath) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, relativePath, is);
        ossClient.shutdown();
    }

    @Override
    public void loadFile(String relativePath, OutputStream os) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        OSSObject ossObject = ossClient.getObject(bucketName, relativePath);
        try {
            IOUtils.copy(ossObject.getObjectContent(), os, FileUtil.BUFF_SIZE);
        } catch (IOException e) {
            throw new OneBaseException("读取文件[" + relativePath + "]失败");
        }
        ossClient.shutdown();
    }

    /**
     * 不要调用这个接口，因为ossclient关闭了，会导致is也关闭
     *
     * @param relativePath 相对路径
     * @return
     */
    @Override
    public InputStream loadFile(String relativePath) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        OSSObject ossObject = ossClient.getObject(bucketName, relativePath);
        InputStream is = ossObject.getObjectContent();
        ossClient.shutdown();
        return is;
    }

    @Override
    public byte[] loadFileBytes(String relativePath) {
        byte[] bytes;
        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();
            loadFile(relativePath, os);
            bytes = os.toByteArray();
        } finally {
            IOUtils.closeQuietly(os);
        }
        return bytes;
    }

    @Override
    public boolean isExists(String relativePath) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        boolean result = ossClient.doesObjectExist(bucketName, relativePath);
        ossClient.shutdown();
        return result;
    }

    @Override
    public void delete(String relativePath) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject(bucketName, relativePath);
        ossClient.shutdown();
    }

}
