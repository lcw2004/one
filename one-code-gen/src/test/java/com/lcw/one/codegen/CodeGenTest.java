package com.lcw.one.codegen;

import com.lcw.one.codegen.bean.*;
import com.lcw.one.codegen.core.generate.*;
import com.lcw.one.codegen.core.generate.impl.*;
import com.lcw.one.codegen.core.form.FormTemplateBuilder;
import com.lcw.one.codegen.util.NameUtils;
import com.lcw.one.util.utils.FileUtil;
import com.lcw.one.util.utils.GsonUtil;
import com.lcw.one.util.utils.IOUtils;
import com.lcw.one.util.utils.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class CodeGenTest {

    private TableGen table;
    private CodeGenStrategy codeGenStrategy;
    private String moduleName = "one-sys";

    @Before
    public void before() {
        codeGenStrategy = new CodeGenStrategy();
        codeGenStrategy.setEntitySuffix("EO");
        codeGenStrategy.setIsOverWrite(1);
        codeGenStrategy.setModuleName("one-code-gen");
        codeGenStrategy.setModuleType(1);
        codeGenStrategy.setModulePackage("com.lcw.one.codegen");

        String json = readJsonFile("OperateLog.json");
        table = GsonUtil.fromJson(json, TableGen.class);
        table.setClassName(NameUtils.toUpperCamel(table.getTableId()) + "EO");
        table.setPackageName(codeGenStrategy.getModulePackage());
    }

    public void generate(IGenerator iGenerator) {
        List<CodeBean> codeBeanList = iGenerator.generate(table, codeGenStrategy);

        for (CodeBean codeBean : codeBeanList) {
            System.out.println("文件路径：" + codeBean.getSavePath());
            System.out.println(codeBean.getCode());
        }
    }

    @Test
    public void testGenerateBean() {
        generate(new BeanGenerator());
    }

    @Test
    public void testGenerateDao() {
        generate(new DaoGenerator());
    }

    @Test
    public void testGenerateService() {
        generate(new ServiceGenerator());
    }

    @Test
    public void testGenerateController() {
        generate(new ControllerGenerator());
    }

    @Test
    public void testGenerateVueList() {
        generate(new VueListGenerator());
    }

    @Test
    public void testGenerateVueView() {
        generate(new VueViewGenerator());
    }

    @Test
    public void testGenerateVueEdit() {
        generate(new VueEditGenerator());
    }

    @Test
    public void testFormTemplate() {
        TableFieldGen tableFieldGen = new TableFieldGen();
        tableFieldGen.setJavaFieldName("thisIsTest");
        tableFieldGen.setFieldName("测试字段");

        for (int i = 1; i <= 5; i++) {
            tableFieldGen.setShowType(String.valueOf(i));
            tableFieldGen.setIsRequired(1);
            System.out.println("-----------------------------------");
            System.out.println("Edit Form");
            System.out.println(FormTemplateBuilder.getEditFormHtml(tableFieldGen));
            System.out.println("View Form");
            System.out.println(FormTemplateBuilder.getViewFormHtml(tableFieldGen));
            System.out.println("Query Form");
            System.out.println(FormTemplateBuilder.getQueryFormHtml(tableFieldGen));
            System.out.println("-----------------------------------");
        }
    }


    private File getTestResourceFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

    private String readJsonFile(String fileName) {
        File file = getTestResourceFile(fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = IOUtils.readFully(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return StringUtils.valueOf(bytes);
    }

}
