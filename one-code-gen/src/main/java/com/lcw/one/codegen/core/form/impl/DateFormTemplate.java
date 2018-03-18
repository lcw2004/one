package com.lcw.one.codegen.core.form.impl;

import com.lcw.one.codegen.bean.TableFieldGen;
import com.lcw.one.codegen.core.form.IFormTemplate;

/**
 * 日期选择组件，对应格式（yyyy-MM-dd）
 */
public class DateFormTemplate implements IFormTemplate {

    @Override
    public String editForm(TableFieldGen tableFieldGen) {
        return "/edit/DateForm.vue";
    }

    @Override
    public String viewForm(TableFieldGen tableFieldGen) {
        return "/view/TextForm.vue";
    }

    @Override
    public String queryForm(TableFieldGen tableFieldGen) {
        return "/query/DateRangeForm.vue";
    }

    @Override
    public int formWidth() {
        return 6;
    }
}
