package com.lcw.one.codegen.core.form.impl;

import com.lcw.one.codegen.bean.TableFieldGen;
import com.lcw.one.codegen.core.form.IFormTemplate;

/**
 * 选择用户组件
 */
public class UserFormTemplate implements IFormTemplate {

    @Override
    public String editForm(TableFieldGen tableFieldGen) {
        return "/edit/UserForm.vue";
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
