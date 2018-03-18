package com.lcw.one.codegen.core.form;

import com.lcw.one.codegen.bean.TableFieldGen;

/**
 * Form 组件模板
 */
public interface IFormTemplate {

    /**
     * 编辑组件模板
     * @param tableFieldGen
     * @return
     */
    String editForm(TableFieldGen tableFieldGen);

    /**
     * 查看组件模板
     * @param tableFieldGen
     * @return
     */
    String viewForm(TableFieldGen tableFieldGen);

    /**
     * 查询条件组件模板
     * @param tableFieldGen
     * @return
     */
    String queryForm(TableFieldGen tableFieldGen);

    /**
     * Form宽度，一般为6，富文本为12
     * @return
     */
    int formWidth();

}
