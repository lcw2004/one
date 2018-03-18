package com.lcw.one.codegen.core.generate.impl;

import com.lcw.one.codegen.bean.*;
import com.lcw.one.codegen.core.generate.IGenerator;
import com.lcw.one.codegen.core.template.impl.BeeTlTemplateImpl;
import com.lcw.one.codegen.core.template.IFillTemplate;
import com.lcw.one.codegen.util.CodeGenUtil;

import java.io.File;
import java.util.*;

public class VueListGenerator implements IGenerator {

    private IFillTemplate iFillTemplate = new BeeTlTemplateImpl();

    @Override
    public List<CodeBean> generate(TableGen table, CodeGenStrategy strategy) {
        Map<String, Object> params = new HashMap<>();
        params.put("table", table);
        params.put("strategy", strategy);
        params.put("restPath", "api");
        params.put("moduleName", strategy.getModuleSimpleName());
        params.put("queryFieldList", table.getQueryFieldList());
        params.put("queryFieldListLength", table.getQueryFieldList().size());

        CodeBean vueCodeBean = generateVue(table, strategy.getModuleSimpleName(), params);
        CodeBean apiCodeBean = generateApi(table, strategy.getModuleSimpleName(), params);
        CodeBean routerCodeBean = generateRouter(table, strategy.getModuleSimpleName(), params);
        return Arrays.asList(apiCodeBean, vueCodeBean, routerCodeBean);
    }

    public CodeBean generateApi(TableGen table, String moduleName, Map<String, Object> params) {
        String code = iFillTemplate.fill("VueApi.js", params);
        String path = getApiPath(table, moduleName);
        return new CodeBean(code, path);
    }

    public CodeBean generateVue(TableGen table, String moduleName, Map<String, Object> params) {
        String code = iFillTemplate.fill("VueList.vue", params);
        String path = getVuePath(table, moduleName);
        return new CodeBean(code, path);
    }

    public CodeBean generateRouter(TableGen table, String moduleName, Map<String, Object> params) {
        String code = iFillTemplate.fill("VueRouter.js", params);
        String path = getRouterPath(table, moduleName);
        return new CodeBean(code, path);
    }

    public String getApiPath(TableGen table, String moduleName) {
        return CodeGenUtil.getUICodeSavePath() + "api" + File.separator + moduleName + File.separator + table.getRestPath() + ".js";
    }

    public String getRouterPath(TableGen table, String moduleName) {
        return CodeGenUtil.getUICodeSavePath() + "router" + File.separator + moduleName + File.separator + table.getRestPath() + ".js";
    }

    public String getVuePath(TableGen table, String moduleName) {
        return CodeGenUtil.getUICodeSavePath() + "views" + File.separator + moduleName + File.separator + table.getRestPath() + File.separator + table.getClassNameNoEO() + "List.vue";
    }

}
