package com.lcw.one.codegen.util;

import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.FileUtil;

import java.io.File;

/**
 * @version 2017-04-18.
 * @auth Licw
 */
public class CodeGenUtil {

    public static String BASE_PATH;
    public static final String PACKAGE_PREFIX = "com" + File.separator + "lcw" + File.separator + "one";

    static {
        BASE_PATH = System.getProperty("user.dir");
        if (BASE_PATH.endsWith("one-code-gen")) {
            int index = BASE_PATH.lastIndexOf("one-code-gen");
            BASE_PATH = BASE_PATH.substring(0, index - 1);
        }
    }

    public static String getCodeSavePath(String moduleName, String packageName, String classSimpleName) {
        String savePathString = BASE_PATH + File.separator + moduleName + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
        String path = savePathString + packageName.replaceAll("\\.", "\\" + File.separator);
        return path + File.separator + classSimpleName + ".java";
    }

    public static String getUICodeSavePath() {
        String savePathString = BASE_PATH + File.separator + "one-ui" + File.separator + "src" + File.separator + "module" + File.separator + "index" + File.separator;
        return savePathString;
    }

    /**
     * 获取模块的包路径
     * 1.   如果模块已经存在，则解析当前模块的包路径即可
     * 2.   如果模块不存在，则按约定规则，将模块名当作包路径
     *
     * @param moduleName
     * @return
     */
    public static String getPackageFromModule(String moduleName) {
        String modulePackage = "";
        if (FileUtil.exists(BASE_PATH + File.separator + moduleName)) {
            // 模块存在
            String savePathString = BASE_PATH + File.separator + moduleName + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
            String path = savePathString + PACKAGE_PREFIX;
            if (FileUtil.exists(path)) {
                String[] fileList = new File(path).list();
                if (CollectionUtils.isNotEmpty(fileList)) {
                    modulePackage = fileList[0];
                }
            }
        } else {
            // 模块不存在
            modulePackage = moduleName.substring(4, moduleName.length());
            modulePackage = NameUtils.lowerHyphenToLowerCamel(modulePackage);
        }
        String modulePath = PACKAGE_PREFIX + File.separator + modulePackage;
        modulePackage = modulePath.replaceAll("\\" + File.separator, "\\.");
        return modulePackage;
    }

}
