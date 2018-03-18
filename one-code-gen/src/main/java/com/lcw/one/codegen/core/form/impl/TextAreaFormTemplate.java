package com.lcw.one.codegen.core.form.impl;

import com.lcw.one.codegen.bean.TableFieldGen;
import com.lcw.one.codegen.core.form.IFormTemplate;

/**
 * TextArea组件
 */
public class TextAreaFormTemplate implements IFormTemplate {

    @Override
    public String editForm(TableFieldGen tableFieldGen) {
        return "/edit/TextAreaForm.vue";
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
