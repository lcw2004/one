package com.lcw.one.codegen.core.form.impl;

import com.lcw.one.codegen.bean.TableFieldGen;
import com.lcw.one.codegen.core.form.IFormTemplate;

/**
 * 富文本编辑器组件
 */
public class EditorFormTemplate implements IFormTemplate {

    @Override
    public String editForm(TableFieldGen tableFieldGen) {
        return "/edit/EditorForm.vue";
    }

    @Override
    public String viewForm(TableFieldGen tableFieldGen) {
        return "/view/TextForm.vue";
    }

    @Override
    public String queryForm(TableFieldGen tableFieldGen) {
        return "/query/InputForm.vue";
    }

    @Override
    public int formWidth() {
        return 6;
    }
}
