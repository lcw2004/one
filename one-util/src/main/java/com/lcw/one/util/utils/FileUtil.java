package com.lcw.one.util.utils;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.*;
import java.util.Date;

public class FileUtil extends FileUtils {

    public static final int BUFF_SIZE = 1024 * 10;
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    public static String FILE_SEPARATOR = "/";

    public static void copyFile(String src, String target) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(src));
            out = new BufferedOutputStream(new FileOutputStream(target));
            byte[] buffer = new byte[BUFF_SIZE];
            int len;
            while ((len = in.read(buffer, 0, BUFF_SIZE)) > 0) {
                out.write(buffer, 0, len);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }

    public static byte[] readFile(File file) {
        byte[] bytes = null;
        try {
            bytes = IOUtils.readFully(new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 从文件中读取字节数组
     *
     * @param fileName 文件名称
     * @return
     */
    public static byte[] readFile(String fileName) {
        return readFile(new File(fileName));
    }

    /**
     * 将字节数组写到文件中
     *
     * @param bytes      字节数组
     * @param outputFile 文件名称
     * @return
     */
    public static void writeFile(byte[] bytes, String outputFile) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(outputFile);
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

    public static File mkdir(String folderPath) {
        File file = new File(folderPath);
        if (!file.exists() || !file.isDirectory()) {
            boolean success = false;
            do {
                success = file.mkdirs();
            } while (success);

        }
        return file;
    }

    public static void delFolder(String folderPath) throws Exception {
        delAllFile(folderPath);
        File myFilePath = new File(folderPath);
        myFilePath.delete();
    }

    public static boolean delAllFile(String path) throws Exception {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }

        String[] tempList = file.list();
        File temp = null;
        if (tempList != null) {
            for (int i = 0; i < tempList.length; i++) {
                if (path.endsWith(File.separator)) {
                    temp = new File(path + tempList[i]);
                } else {
                    temp = new File(path + File.separator + tempList[i]);
                }

                if (temp.isFile()) {
                    temp.delete();
                }

                if (temp.isDirectory()) {
                    delAllFile(path + "/" + tempList[i]);
                    delFolder(path + "/" + tempList[i]);
                    flag = true;
                }
            }
        }
        return flag;
    }


    /**
     * 删除文件，可以删除单个文件或文件夹
     *
     * @param fileName 被删除的文件名
     * @return 如果删除成功，则返回true，否是返回false
     */
    public static boolean delFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            logger.info(fileName + " 文件不存在!");
            return true;
        } else {
            if (file.isFile()) {
                return deleteFile(fileName);
            } else {
                return deleteDirectory(fileName);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 被删除的文件名
     * @return 如果删除成功，则返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                logger.info("删除单个文件 " + fileName + " 成功!");
                return true;
            } else {
                logger.info("删除单个文件 " + fileName + " 失败!");
                return false;
            }
        } else {
            logger.info(fileName + " 文件不存在!");
            return true;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dirName 被删除的目录所在的文件路径
     * @return 如果目录删除成功，则返回true，否则返回false
     */
    public static boolean deleteDirectory(String dirName) {
        String dirNames = dirName;
        if (!dirNames.endsWith(File.separator)) {
            dirNames = dirNames + File.separator;
        }
        File dirFile = new File(dirNames);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            logger.info(dirNames + " 目录不存在!");
            return true;
        }
        boolean flag = true;
        // 列出全部文件及子目录
        File[] files = dirFile.listFiles();
        if (CollectionUtils.isNotEmpty(files)) {
            for (int i = 0; i < files.length; i++) {
                // 删除子文件
                if (files[i].isFile()) {
                    flag = deleteFile(files[i].getAbsolutePath());
                    // 如果删除文件失败，则退出循环
                    if (!flag) {
                        break;
                    }
                }
                // 删除子目录
                else if (files[i].isDirectory()) {
                    flag = deleteDirectory(files[i]
                            .getAbsolutePath());
                    // 如果删除子目录失败，则退出循环
                    if (!flag) {
                        break;
                    }
                }
            }
        }
        if (!flag) {
            logger.info("删除目录失败!");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            logger.info("删除目录 " + dirName + " 成功!");
            return true;
        } else {
            logger.info("删除目录 " + dirName + " 失败!");
            return false;
        }

    }

    public static boolean exists(String path) {
        return new File(path).exists();
    }

    public static void checkAndMkdirs(File file) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    public static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    }

    /**
     * 获取文件名称，不包含后缀
     * @param fileName
     * @return
     */
    public static String getFileName(String fileName) {
        // 某些未知情况下（不知如何重现），传入的文件名称会包含文件路径，这个地方把文件路径去掉。
        int fileSepIndex = fileName.lastIndexOf("\\");
        if (fileSepIndex > 0) {
            fileName = fileName.substring(fileSepIndex + 1, fileName.length());
        }
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    /**
     * 用文件的默认打开方式打开文件
     *
     * @param fileName
     */
    public static void openFile(String fileName) {
        try {
            if (new File(fileName).exists()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(new File(fileName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateFilePath(String fileType) {
        String path = DateUtils.dateToString(new Date(), "yyyy" + FILE_SEPARATOR + "MM" + FILE_SEPARATOR + "dd");
        return path + FILE_SEPARATOR + UUID.randomUUID() + "." + fileType;
    }
}
