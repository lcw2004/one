package com.lcw.one.codegen.core;

import com.lcw.one.codegen.bean.CodeGenInfo;
import com.lcw.one.codegen.bean.CodeMetaInfo;

/**
 * @version 2017-04-19.
 * @auth Licw
 */
public class ControllerGenerate extends BaseGenerate {

    @Override
    CodeMetaInfo getMainCodeMetaInfo(CodeGenInfo codeGenInfo) {
        return codeGenInfo.getRestCodeMetaInfo();
    }

    @Override
    public String templateFile() {
        return "Controller.java";
    }

    @Override
    boolean isNeedGenCode(CodeGenInfo codeGenInfo) {
        if (codeGenInfo.getEntityMetaInfo().isRelationTable()) {
            return false;
        }

        return true;
    }
}
