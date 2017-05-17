package com.lcw.one.codegen.core;


import com.lcw.one.codegen.bean.CodeGenInfo;
import com.lcw.one.codegen.bean.CodeMetaInfo;
import com.lcw.one.codegen.bean.EntityMetaInfo;
import com.lcw.one.codegen.template.BeeTlTemplateImpl;
import com.lcw.one.codegen.template.IFillTemplate;
import com.lcw.one.codegen.util.ClassParse;
import com.lcw.one.codegen.util.CodeGenUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class BaseGenerate {

    private static final Logger LOGGER = LoggerFactory.getLogger(DaoGenerate.class);
    private static final IFillTemplate iFillTemplate = new BeeTlTemplateImpl();
    private static String BASE_PATH;
    private static boolean isCover = false;

    static {
        BASE_PATH = System.getProperty("user.dir");
    }


    abstract CodeMetaInfo getMainCodeMetaInfo(CodeGenInfo codeGenInfo);

    abstract String templateFile();

    abstract boolean isNeedGenCode(CodeGenInfo codeGenInfo);

    public void generate(String packageName, String moduleName) {
        List<EntityMetaInfo> classBeanList = ClassParse.parse(packageName);
        for (EntityMetaInfo classBean : classBeanList) {
            if (classBean != null) {
                generate(classBean, moduleName);
            }
        }
    }

    public CodeGenInfo preData(EntityMetaInfo entityMetaInfo, String moduleName) {
        CodeGenInfo codeGenInfo = new CodeGenInfo();
        codeGenInfo.setEntityMetaInfo(entityMetaInfo);
        codeGenInfo.setModuleName(moduleName);

        // dao
        CodeMetaInfo dao = new CodeMetaInfo();
        dao.setPackageName(CodeGenUtil.getCodePackageName(entityMetaInfo.getClassName(), "dao"));
        dao.setSimpleClassName(entityMetaInfo.getSimpleClassName() + "Dao");
        dao.setClassName(dao.getPackageName() + "." + dao.getSimpleClassName());
        codeGenInfo.setDaoCodeMetaInfo(dao);

        // service
        CodeMetaInfo service = new CodeMetaInfo();
        service.setPackageName(CodeGenUtil.getCodePackageName(entityMetaInfo.getClassName(), "service"));
        service.setSimpleClassName(entityMetaInfo.getSimpleClassName() + "Service");
        service.setClassName(service.getPackageName() + "." + service.getSimpleClassName());
        codeGenInfo.setServiceCodeMetaInfo(service);

        // rest controller
        CodeMetaInfo rest = new CodeMetaInfo();
        rest.setPackageName(CodeGenUtil.getCodePackageName(entityMetaInfo.getClassName(), "rest"));
        rest.setSimpleClassName(entityMetaInfo.getSimpleClassName() + "RestController");
        rest.setClassName(rest.getPackageName() + "." + rest.getSimpleClassName());
        codeGenInfo.setRestCodeMetaInfo(rest);

        return codeGenInfo;
    }

    public void generate(EntityMetaInfo classBean, String moduleName) {
        LOGGER.info("-----------------------------------------------");
        LOGGER.info("Gen Class : " + classBean.getClassName());
        try {
            // 生成代码
            CodeGenInfo genClassBean = preData(classBean, moduleName);

            if(!isNeedGenCode(genClassBean)) {
                LOGGER.warn("类[{}]不需要生成，忽略", genClassBean.getEntityMetaInfo().getClassName());
            }

            String code = iFillTemplate.fill(templateFile(), genClassBean);
            genClassBean.setCode(code);

            // 存储代码
            File codeFile = getSavePath(genClassBean);
            if (!isCover && codeFile.exists()) {
                LOGGER.warn("文件[{}]已经存在，忽略", codeFile.getAbsolutePath());
                return;
            }

            FileUtils.writeByteArrayToFile(codeFile, code.getBytes());
            LOGGER.info("Save as: [{}]", codeFile.getAbsolutePath());
        } catch (IOException e) {
            LOGGER.error("Save code error, Class: [{}]", classBean.getClassName());
            LOGGER.error(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            LOGGER.info("-----------------------------------------------");
        }
    }

    private File getSavePath(CodeGenInfo codeGenInfo) {
        CodeMetaInfo mainCodeGenInfo = getMainCodeMetaInfo(codeGenInfo);
        String moduleName = codeGenInfo.getModuleName();
        String packageName = mainCodeGenInfo.getPackageName();
        String simpleClassName = mainCodeGenInfo.getSimpleClassName();
        return CodeGenUtil.getCodeSaveFile(BASE_PATH, moduleName, packageName, simpleClassName);
    }
}
