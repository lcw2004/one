package com.lcw.one.file.store.impl;

import com.lcw.one.file.store.IFileStore;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.utils.DateUtils;
import com.lcw.one.util.utils.FileUtil;
import com.lcw.one.util.utils.IOUtils;
import com.lcw.one.util.utils.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;

@Service
public class LocalFileStoreImpl implements IFileStore {

    @Value("${one.file.path}")
    private String basePath;

    @Override
    public String storeFile(InputStream is, String fileExtension) throws IOException {
        String path = generateFilePath(fileExtension);
        String fullPath = getBatPath() + path;
        File file = new File(fullPath);

        // 检查父目录是否存在
        FileUtil.checkAndMkdirs(file);

        // 存储文件
        FileUtil.copyInputStreamToFile(is, file);
        return path;
    }

    @Override
    public InputStream loadFile(String path) {
        InputStream is;
        try {
            String fullPath = getBatPath() + path;
            is = new FileInputStream(new File(fullPath));
        } catch (FileNotFoundException e) {
            throw new OneBaseException("文件[" + path + "]不存在");
        }
        return is;
    }

    @Override
    public byte[] loadFileBytes(String path) {
        byte[] bytes;
        InputStream is = null;
        try {
            String fullPath = getBatPath() + path;
            is = new FileInputStream(new File(fullPath));

            bytes = IOUtils.readFully(is);
        } catch (FileNotFoundException e) {
            throw new OneBaseException("文件[" + path + "]不存在");
        } catch (IOException e) {
            throw new OneBaseException("读取文件[" + path + "]失败");
        } finally {
            IOUtils.closeQuietly(is);
        }
        return bytes;
    }

    private static String generateFilePath(String fileType) {
        String path = DateUtils.dateToString(new Date(), "yyyy" + File.separator + "MM" + File.separator + "dd");
        return path + File.separator + UUID.randomUUID() + "." + fileType;
    }

    /**
     * 在基础路径上面补上路劲分隔符
     */
    private String getBatPath() {
        if (!(basePath.endsWith("\\") || basePath.endsWith("/"))) {
            basePath = basePath + File.separator;
        }
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
