package com.lcw.one.file.store;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IFileStore {

    /**
     * 获取文件存储类型
     * @return
     */
    String getStoreType();

    /**
     * 存储文件，并返回文件相对路径
     *
     * @param is           文件IS流
     * @param relativePath 文件相对路径
     * @return 文件相对路径
     */
    void storeFile(InputStream is, String relativePath) throws IOException;

    /**
     * 根据传入的路径返回文件，并将文件写道OS流中
     *
     * @param relativePath 相对路径
     * @return
     */
    void loadFile(String relativePath, OutputStream os);

    /**
     * 根据传入的路径返回文件流
     *
     * @param relativePath 相对路径
     * @return
     */
    InputStream loadFile(String relativePath);

    /**
     * 根据传入的路径返回二进制数据
     *
     * @param relativePath 相对路径
     * @return
     */
    byte[] loadFileBytes(String relativePath);

    /**
     * 检查文件是否存在
     * @param relativePath 相对路径
     * @return
     */
    boolean isExists(String relativePath);

    /**
     * 删除文件
     * @param relativePath 相对路径
     */
    void delete(String relativePath);

}
