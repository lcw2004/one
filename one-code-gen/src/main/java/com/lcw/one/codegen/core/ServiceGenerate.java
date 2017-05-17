package com.lcw.one.codegen.core;

import com.lcw.one.codegen.bean.CodeGenInfo;
import com.lcw.one.codegen.bean.CodeMetaInfo;

/**
 * @version 2017-04-19.
 * @auth Licw
 */
public class ServiceGenerate extends BaseGenerate {

    @Override
    CodeMetaInfo getMainCodeMetaInfo(CodeGenInfo codeGenInfo) {
        return codeGenInfo.getServiceCodeMetaInfo();
    }

    @Override
    public String templateFile() {
        return "Service.java";
    }

    @Override
    boolean isNeedGenCode(CodeGenInfo codeGenInfo) {
        return true;
    }
}
