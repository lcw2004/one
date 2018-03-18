package com.lcw.one.codegen.core.generate.impl;

import com.lcw.one.codegen.bean.*;
import com.lcw.one.codegen.core.generate.IGenerator;
import com.lcw.one.codegen.core.template.impl.BeeTlTemplateImpl;
import com.lcw.one.codegen.core.template.IFillTemplate;
import com.lcw.one.codegen.entity.CodeGenTableFieldEO;
import com.lcw.one.codegen.util.CodeGenUtil;

import java.util.*;

public class BeanGenerator implements IGenerator {

    private IFillTemplate iFillTemplate = new BeeTlTemplateImpl();

    @Override
    public List<CodeBean> generate(TableGen table, CodeGenStrategy strategy) {
        Map<String, Object> params = new HashMap<>();
        params.put("table", table);
        params.put("strategy", strategy);
        params.put("imports", getImportOfTable(table));
        params.put("idFieldList", listIdTableField(table));

        List<CodeBean> codeBeanList = new ArrayList<>();

        // 主Bean代码
        String code = iFillTemplate.fill("Bean.java", params);
        String path = getSavePath(table, table.getClassName());
        codeBeanList.add(new CodeBean(code, path));

        // PK Bean代码
        if (table.getIsJointKey()) {
            String codePk = iFillTemplate.fill("BeanPK.java", params);
            String pathPk = getSavePath(table, table.getClassName() + "PK");
            codeBeanList.add(new CodeBean(codePk, pathPk));
        }

        return codeBeanList;
    }

    public String getSavePath(TableGen table, String className) {
        // entity统一存储在entity工程下面
        String moduleName = "one-entity";
        String packageName = table.getPackageName() + ".entity";
        return CodeGenUtil.getCodeSavePath(moduleName, packageName, className);
    }

    private static Set<String> getImportOfTable(TableGen table) {
        Set<String> imports = new HashSet<>();
        for (CodeGenTableFieldEO tableField : table.getFieldList()) {
            imports.add(tableField.getJavaFieldInclude());
        }
        return imports;
    }

    private static List<CodeGenTableFieldEO> listIdTableField(TableGen table) {
        List<CodeGenTableFieldEO> idTableFieldList = new ArrayList<>();
        for (CodeGenTableFieldEO tableField : table.getFieldList()) {
            if (tableField.isPrimaryKey()) {
                idTableFieldList.add(tableField);
            }
        }
        return idTableFieldList;
    }
}
