package com.lcw.one.codegen.core.generate;

import com.lcw.one.codegen.bean.consts.CodeTypeEnum;
import com.lcw.one.codegen.core.generate.impl.*;

public class CodeGeneratorFactory {

    /**
     * 根据代码类别构建不同的代码生成器
     * @param codeType
     * @return
     */
    public static IGenerator build(String codeType) {
        IGenerator iGenerator = null;

        CodeTypeEnum codeTypeEnum = CodeTypeEnum.get(codeType);
        if (codeTypeEnum == CodeTypeEnum.Bean) {
            iGenerator = new BeanGenerator();
        } else if (codeTypeEnum == CodeTypeEnum.Dao) {
            iGenerator = new DaoGenerator();
        } else if (codeTypeEnum == CodeTypeEnum.Service) {
            iGenerator = new ServiceGenerator();
        } else if (codeTypeEnum == CodeTypeEnum.Controller) {
            iGenerator = new ControllerGenerator();
        } else if (codeTypeEnum == CodeTypeEnum.VueList) {
            iGenerator = new VueListGenerator();
        } else if (codeTypeEnum == CodeTypeEnum.VueView) {
            iGenerator = new VueViewGenerator();
        } else if (codeTypeEnum == CodeTypeEnum.VueEdit) {
            iGenerator = new VueEditGenerator();
        } else if (codeTypeEnum == CodeTypeEnum.VueModal) {
            iGenerator = new VueModalGenerator();
        }
        return iGenerator;
    }
}
