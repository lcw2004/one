package com.lcw.one.codegen.core.form.impl;

import com.lcw.one.codegen.bean.TableFieldGen;
import com.lcw.one.codegen.core.form.IFormTemplate;

/**
 * 文件上传组件
 */
public class FileUploadFormTemplate implements IFormTemplate {

    @Override
    public String editForm(TableFieldGen tableFieldGen) {
        return "/edit/FileUploadForm.vue";
    }

    @Override
    public String viewForm(TableFieldGen tableFieldGen) {
        return "/view/FileDownloadForm.vue";
    }

    @Override
    public String queryForm(TableFieldGen tableFieldGen) {
        return null;
    }

    @Override
    public int formWidth() {
        return 6;
    }
}
