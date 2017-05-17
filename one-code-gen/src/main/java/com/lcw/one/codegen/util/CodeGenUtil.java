package com.lcw.one.codegen.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @version 2017-04-18.
 * @auth Licw
 */
public class CodeGenUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeGenUtil.class);

    public static File getCodeSaveFile(String basePath, String moduleName, String packageName, String classSimpleName) {
        String savePathString = basePath + "\\" + moduleName + "\\src\\main\\java\\";
        String path = savePathString + packageName.replaceAll("\\.", "/");
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File codeFile = new File(path + "\\" + classSimpleName + ".java");
        return codeFile;
    }

    public static String getCodePackageName(String entityClassName, String packageName) {
        int index = entityClassName.lastIndexOf(".");
        return entityClassName.substring(0, index).replaceAll("entity", packageName);
    }

    public static String lowerFirstChar(String className) {
        if (StringUtils.isEmpty(className)) {
            return "";
        }

        String firstCharLower = String.valueOf(className.charAt(0)).toLowerCase();
        return firstCharLower + className.substring(1, className.length());
    }

}
