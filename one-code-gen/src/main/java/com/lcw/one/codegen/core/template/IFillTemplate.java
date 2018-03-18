package com.lcw.one.codegen.core.template;

import java.util.Map;

public interface IFillTemplate {

    /**
     * 填充模板
     * @param tempFileName 模板文件路径（在resource/template下面）
     * @param params 传入的变量
     * @return
     */
    String fill(String tempFileName, Map<String, Object> params);

}
