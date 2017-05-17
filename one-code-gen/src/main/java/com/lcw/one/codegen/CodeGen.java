package com.lcw.one.codegen;

import com.lcw.one.codegen.core.ControllerGenerate;
import com.lcw.one.codegen.core.DaoGenerate;
import com.lcw.one.codegen.core.ServiceGenerate;

public class CodeGen {

    public static void main(String[] args) {
        String packageName = "com.lcw.one.sys.user";
        String moduleName = "one-user";

        DaoGenerate daoGenerate = new DaoGenerate();
        daoGenerate.generate(packageName, moduleName);

        ServiceGenerate serviceGenerate = new ServiceGenerate();
        serviceGenerate.generate(packageName, moduleName);

        ControllerGenerate controllerGenerate = new ControllerGenerate();
        controllerGenerate.generate(packageName, moduleName);
    }

}
