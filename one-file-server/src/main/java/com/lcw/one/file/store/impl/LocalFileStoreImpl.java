package com.lcw.one.file.store.impl;

import com.lcw.one.file.bean.constant.FileStoreTypeEnum;
import com.lcw.one.file.store.IFileStore;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.utils.FileUtil;
import com.lcw.one.util.utils.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class LocalFileStoreImpl implements IFileStore {

    @Value("${one.file.local.path}")
    private String basePath;

    @Override
    public String getStoreType() {
        return FileStoreTypeEnum.LOCAL.getValue();
    }

    @Override
    public void storeFile(InputStream is, String relativePath) throws IOException {
        String fullPath = getBasePath() + relativePath;
        File file = new File(fullPath);

        // 检查父目录是否存在
        FileUtil.checkAndMkdirs(file);

        // 存储文件
        FileUtil.copyInputStreamToFile(is, file);
    }

    @Override
    public void loadFile(String relativePath, OutputStream os) {
        InputStream is = null;
        try {
            String fullPath = getBasePath() + relativePath;
            is = new FileInputStream(new File(fullPath));
            IOUtils.copy(is, os, FileUtil.BUFF_SIZE);
        } catch (FileNotFoundException e) {
            throw new OneBaseException("文件[" + relativePath + "]不存在");
        } catch (IOException e) {
            throw new OneBaseException("读取文件[" + relativePath + "]不存在");
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    @Override
    public InputStream loadFile(String relativePath) {
        InputStream is = null;
        try {
            String fullPath = getBasePath() + relativePath;
            is = new FileInputStream(new File(fullPath));
        } catch (FileNotFoundException e) {
            throw new OneBaseException("文件[" + relativePath + "]不存在");
        }
        return is;
    }

    @Override
    public byte[] loadFileBytes(String relativePath) {
        byte[] bytes;
        InputStream is = null;
        try {
            String fullPath = getBasePath() + relativePath;
            is = new FileInputStream(new File(fullPath));
            bytes = IOUtils.readFully(is);
        } catch (FileNotFoundException e) {
            throw new OneBaseException("文件[" + relativePath + "]不存在");
        } catch (IOException e) {
            throw new OneBaseException("读取文件[" + relativePath + "]失败");
        } finally {
            IOUtils.closeQuietly(is);
        }
        return bytes;
    }

    @Override
    public boolean isExists(String relativePath) {
        return FileUtil.exists(getBasePath() + relativePath);
    }

    @Override
    public void delete(String relativePath) {
        FileUtil.deleteFile(getBasePath() + relativePath);
    }

    /**
     * 在基础路径上面补上路劲分隔符
     */
    private String getBasePath() {
        if (!(basePath.endsWith("\\") || basePath.endsWith("/"))) {
            basePath = basePath + FileUtil.FILE_SEPARATOR;
        }
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
