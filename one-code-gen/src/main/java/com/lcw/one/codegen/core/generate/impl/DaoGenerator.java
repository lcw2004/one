package com.lcw.one.codegen.core.generate.impl;

import com.lcw.one.codegen.bean.CodeBean;
import com.lcw.one.codegen.bean.CodeGenStrategy;
import com.lcw.one.codegen.bean.TableGen;
import com.lcw.one.codegen.core.generate.IGenerator;
import com.lcw.one.codegen.core.template.impl.BeeTlTemplateImpl;
import com.lcw.one.codegen.core.template.IFillTemplate;
import com.lcw.one.codegen.util.CodeGenUtil;

import java.util.*;

public class DaoGenerator implements IGenerator {

    private IFillTemplate iFillTemplate = new BeeTlTemplateImpl();

    @Override
    public List<CodeBean> generate(TableGen table, CodeGenStrategy strategy) {
        Map<String, Object> params = new HashMap<>();
        params.put("table", table);
        params.put("strategy", strategy);
        params.put("queryFieldList", table.getQueryFieldList());
        params.put("queryFieldListLength", table.getQueryFieldList().size());
        params.put("queryFieldStr", table.getQueryFieldStr());
        params.put("queryParamStr", table.getQueryParamStr());

        String code = new BeeTlTemplateImpl().fill("Dao.java", params);
        String path = getSavePath(table, strategy.getModuleName());

        return Arrays.asList(new CodeBean(code, path));
    }

    public String getSavePath(TableGen table, String moduleName) {
        String packageName = table.getPackageName() + ".dao";
        String simpleClassName = table.getClassName() + "Dao";
        return CodeGenUtil.getCodeSavePath(moduleName, packageName, simpleClassName);
    }

}
