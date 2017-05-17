package com.lcw.one.codegen.template;

import com.lcw.one.codegen.bean.CodeGenInfo;

public interface IFillTemplate {

    String fill(String tempFileName, CodeGenInfo genClassBean);

}
