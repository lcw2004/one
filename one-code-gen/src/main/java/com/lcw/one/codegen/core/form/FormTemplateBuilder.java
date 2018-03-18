package com.lcw.one.codegen.core.form;

import com.lcw.one.codegen.bean.TableFieldGen;
import com.lcw.one.codegen.core.template.impl.BeeTlTemplateImpl;
import com.lcw.one.codegen.core.template.IFillTemplate;
import com.lcw.one.codegen.core.form.impl.*;
import com.lcw.one.util.utils.Reflections;

import java.util.HashMap;
import java.util.Map;

public class FormTemplateBuilder {

    private static IFillTemplate iFillTemplate = new BeeTlTemplateImpl();
    private static Map<String, Class> formTemplateMap = new HashMap<>();
    static {
        /**
         * Key对应字典（code_gen_form_tyoe）的值
         */
        formTemplateMap.put("1", InputFormTemplate.class);
        formTemplateMap.put("2", TextAreaFormTemplate.class);
        formTemplateMap.put("3", EditorFormTemplate.class);
        formTemplateMap.put("4", RadioFormTemplate.class);
        formTemplateMap.put("5", CheckboxFormTemplate.class);
        formTemplateMap.put("6", SelectFormTemplate.class);
        formTemplateMap.put("7", DateFormTemplate.class);
        formTemplateMap.put("8", DateTimeFormTemplate.class);
        formTemplateMap.put("9", FileUploadFormTemplate.class);
        formTemplateMap.put("10", UserFormTemplate.class);
        formTemplateMap.put("11", OfficeFormTemplate.class);
    }

    /**
     * 获取编辑页面
     * @param tableFieldGen
     * @return
     */
    public static String getEditFormHtml(TableFieldGen tableFieldGen) {
        IFormTemplate iFormTemplate = instanceFormTemplateImpl(tableFieldGen.getShowType());
        Map<String, Object> params = buildParams(tableFieldGen, iFormTemplate);

        String formTemplate = iFormTemplate.editForm(tableFieldGen);
        if (formTemplate != null) {
            return iFillTemplate.fill(formTemplate, params);
        } else {
            return null;
        }
    }

    /**
     * 获取查看页面
     * @param tableFieldGen
     * @return
     */
    public static String getViewFormHtml(TableFieldGen tableFieldGen) {
        IFormTemplate iFormTemplate = instanceFormTemplateImpl(tableFieldGen.getShowType());
        Map<String, Object> params = buildParams(tableFieldGen, iFormTemplate);

        String formTemplate = iFormTemplate.viewForm(tableFieldGen);
        if (formTemplate != null) {
            return iFillTemplate.fill(formTemplate, params);
        } else {
            return null;
        }
    }

    /**
     * 获取查询条件页面
     * @param tableFieldGen
     * @return
     */
    public static String getQueryFormHtml(TableFieldGen tableFieldGen) {
        IFormTemplate iFormTemplate = instanceFormTemplateImpl(tableFieldGen.getShowType());
        Map<String, Object> params = buildParams(tableFieldGen, iFormTemplate);

        String formTemplate = iFormTemplate.queryForm(tableFieldGen);
        if (formTemplate != null) {
            return iFillTemplate.fill(formTemplate, params);
        } else {
            return null;
        }
    }

    private static Map<String, Object> buildParams(TableFieldGen tableFieldGen, IFormTemplate iFormTemplate) {
        Map<String, Object> params = new HashMap<>();
        params.put("field", tableFieldGen);
        params.put("rowWidth", iFormTemplate.formWidth());
        params.put("formWidth", iFormTemplate.formWidth() == 6 ? 4 : 2);
        return params;
    }

    /**
     * 根据显示类型获取对应的组件模板
     * @param showType 显示类型（对应字典code_gen_form_type的值）
     * @return
     */
    private static IFormTemplate instanceFormTemplateImpl(String showType) {
        IFormTemplate iFormTemplate = new InputFormTemplate();
        Class clazz = formTemplateMap.get(showType);
        if (clazz != null) {
            iFormTemplate = Reflections.instance(clazz);
        }
        return iFormTemplate;
    }

}
