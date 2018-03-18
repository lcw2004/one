package com.lcw.one.codegen.core.generate;

import com.lcw.one.codegen.bean.CodeBean;
import com.lcw.one.codegen.bean.CodeGenStrategy;
import com.lcw.one.codegen.bean.TableGen;

import java.util.List;

public interface IGenerator {

    List<CodeBean> generate(TableGen table, CodeGenStrategy strategy);

}
