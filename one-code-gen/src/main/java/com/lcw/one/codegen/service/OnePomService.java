package com.lcw.one.codegen.service;

import com.lcw.one.codegen.bean.ModuleBean;
import com.lcw.one.codegen.util.CodeGenUtil;
import com.lcw.one.codegen.util.PomUtils;
import com.lcw.one.util.utils.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class OnePomService {

    public List<ModuleBean> listModules() {
        List<String> moduleNameList = PomUtils.listModule();
        List<ModuleBean> moduleList = new ArrayList<>();
        if (CollectionUtils.isEmpty(moduleNameList)) {
            return moduleList;
        }

        for (String moduleName : moduleNameList) {
            ModuleBean module = new ModuleBean();
            module.setModuleName(moduleName);
            module.setModulePackage(CodeGenUtil.getPackageFromModule(moduleName));
            moduleList.add(module);
        }
        Collections.sort(moduleList);
        return moduleList;
    }
}
