package com.lcw.one.codegen.core.generate.impl;

import com.lcw.one.codegen.bean.CodeBean;
import com.lcw.one.codegen.bean.CodeGenStrategy;
import com.lcw.one.codegen.bean.TableGen;
import com.lcw.one.codegen.core.generate.IGenerator;
import com.lcw.one.codegen.core.template.impl.BeeTlTemplateImpl;
import com.lcw.one.codegen.core.template.IFillTemplate;
import com.lcw.one.codegen.util.CodeGenUtil;

import java.util.*;

public class ControllerGenerator implements IGenerator {

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

        List<CodeBean> codeBeanList = new LinkedList<>();
        CodeBean controllerCodeBean = generateController(table, strategy.getModuleName(), params);
        codeBeanList.add(controllerCodeBean);

        if (table.getQueryFieldList().size() > 0) {
            CodeBean queryConditionCodeBean = generateQueryCondition(table, strategy.getModuleName(), params);
            codeBeanList.add(queryConditionCodeBean);
        }
        return codeBeanList;
    }

    public CodeBean generateController(TableGen table, String moduleName, Map<String, Object> params) {
        String code = iFillTemplate.fill("Controller.java", params);
        String path = getContollerSavePath(table, moduleName);
        return new CodeBean(code, path);
    }

    public CodeBean generateQueryCondition(TableGen table, String moduleName, Map<String, Object> params) {
        String code = iFillTemplate.fill("QueryCondition.java", params);
        String path = getQueryConditionSavePath(table, moduleName);
        return new CodeBean(code, path);
    }

    public String getContollerSavePath(TableGen table, String moduleName) {
        String packageName = table.getPackageName() + ".rest";
        String simpleClassName = table.getClassName() + "RestController";
        return CodeGenUtil.getCodeSavePath(moduleName, packageName, simpleClassName);
    }

    public String getQueryConditionSavePath(TableGen table, String moduleName) {
        String packageName = table.getPackageName() + ".bean";
        String simpleClassName = table.getClassNameNoEO() + "QueryCondition";
        return CodeGenUtil.getCodeSavePath(moduleName, packageName, simpleClassName);
    }
}
