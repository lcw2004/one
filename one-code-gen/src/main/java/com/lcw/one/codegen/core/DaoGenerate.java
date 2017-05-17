package com.lcw.one.codegen.core;

import com.lcw.one.codegen.bean.CodeGenInfo;
import com.lcw.one.codegen.bean.CodeMetaInfo;

/**
 * @version 2017-04-19.
 * @auth Licw
 */
public class DaoGenerate extends BaseGenerate {

    @Override
    CodeMetaInfo getMainCodeMetaInfo(CodeGenInfo codeGenInfo) {
        return codeGenInfo.getDaoCodeMetaInfo();
    }

    @Override
    public String templateFile() {
        return "Dao.java";
    }

    @Override
    boolean isNeedGenCode(CodeGenInfo codeGenInfo) {
        return true;
    }
}
