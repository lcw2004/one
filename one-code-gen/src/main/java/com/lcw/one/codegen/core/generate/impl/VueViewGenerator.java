package com.lcw.one.codegen.core.generate.impl;

import com.lcw.one.codegen.bean.CodeBean;
import com.lcw.one.codegen.bean.CodeGenStrategy;
import com.lcw.one.codegen.bean.TableGen;
import com.lcw.one.codegen.core.generate.IGenerator;
import com.lcw.one.codegen.core.template.impl.BeeTlTemplateImpl;
import com.lcw.one.codegen.core.template.IFillTemplate;
import com.lcw.one.codegen.util.CodeGenUtil;
import com.lcw.one.util.constant.GlobalConfig;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VueViewGenerator implements IGenerator {

    private IFillTemplate iFillTemplate = new BeeTlTemplateImpl();

    @Override
    public List<CodeBean> generate(TableGen table, CodeGenStrategy strategy) {
        Map<String, Object> params = new HashMap<>();
        params.put("table", table);
        params.put("strategy", strategy);
        params.put("restPath", GlobalConfig.getRestApiPath());
        params.put("moduleName", strategy.getModuleSimpleName());
        params.put("fieldListLength", table.getFieldList().size());

        CodeBean vueCodeBean = generateVue(table, strategy.getModuleSimpleName(), params);
        return Arrays.asList(vueCodeBean);
    }

    public CodeBean generateVue(TableGen table, String moduleName, Map<String, Object> params) {
        String code = iFillTemplate.fill("VueView.vue", params);
        String path = getVuePath(table, moduleName);
        return new CodeBean(code, path);
    }

    public String getVuePath(TableGen table, String moduleName) {
        return CodeGenUtil.getUICodeSavePath() + "views" + File.separator + moduleName + File.separator + table.getRestPath() + File.separator + table.getClassNameNoEO() + "View.vue";
    }
}
