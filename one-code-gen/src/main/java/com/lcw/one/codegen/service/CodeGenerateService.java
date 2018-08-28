package com.lcw.one.codegen.service;

import com.lcw.one.codegen.bean.*;
import com.lcw.one.codegen.core.generate.CodeGeneratorFactory;
import com.lcw.one.codegen.core.generate.IGenerator;
import com.lcw.one.codegen.core.generate.impl.*;
import com.lcw.one.base.config.GlobalConfig;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CodeGenerateService {

    private static final Logger logger = LoggerFactory.getLogger(CodeGenerateService.class);

    @Autowired
    private TableMetaInfoService tableMetaInfoService;

    @Autowired
    private CodeGenTableEOService codeGenTableEOService;

    @Autowired
    private CodeGenTableFieldEOService codeGenTableFieldEOService;

    public void generate(CodeGenStrategy codeGenStrategy) {
        if (StringUtils.isEmpty(codeGenStrategy.getModuleName())) {
            throw new OneBaseException("模块名称不能为空");
        }
        if (StringUtils.isEmpty(codeGenStrategy.getModulePackage())) {
            throw new OneBaseException("包名不能为空");
        }

        for (String tableId : codeGenStrategy.getTableList()) {
            TableGen tableGen = codeGenTableEOService.getTableByTableId(tableId, codeGenStrategy);

            if (tableGen.getIsBean() == 1) {
                generate(new BeanGenerator(), tableGen, codeGenStrategy);
            }
            if (tableGen.getIsDao() == 1) {
                generate(new DaoGenerator(), tableGen, codeGenStrategy);
            }
            if (tableGen.getIsService() == 1) {
                generate(new ServiceGenerator(), tableGen, codeGenStrategy);
            }
            if (tableGen.getIsRest() == 1) {
                generate(new ControllerGenerator(), tableGen, codeGenStrategy);
            }
            if (tableGen.getIsPageForm() == 1) {
                generate(new VueEditGenerator(), tableGen, codeGenStrategy);
                generate(new VueViewGenerator(), tableGen, codeGenStrategy);
            }
            if (tableGen.getIsPageList() == 1) {
                generate(new VueListGenerator(), tableGen, codeGenStrategy);
            }
            if (tableGen.getIsPageModal() == 1) {
            }
        }
    }

    public void generate(IGenerator iGenerator, TableGen table, CodeGenStrategy codeGenStrategy) {
        List<CodeBean> codeBeanList = iGenerator.generate(table, codeGenStrategy);

        for (CodeBean codeBean : codeBeanList) {
            File codeFile = new File(codeBean.getSavePath());
            try {
                logger.info("表：{}, 代码路径：{}", table.getTableName(), codeBean.getSavePath());
                // 如果设置了不覆盖，则跳过已存在文件
                if (codeGenStrategy.getIsOverWrite() == 0 && codeFile.exists()) {
                    logger.info("已经存在，不覆盖");
                    continue;
                }
                FileUtils.writeByteArrayToFile(codeFile, codeBean.getCode().getBytes(GlobalConfig.DEFAULT_CHART_SET));
                logger.info("成功生成，表：{}, 代码路径：{}", table.getTableId(), codeBean.getSavePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<CodeBean> codePreview(String tableName, String codeType, CodeGenStrategy codeGenStrategy) {
        TableGen tableGen = codeGenTableEOService.getTableByTableId(tableName, codeGenStrategy);

        IGenerator iGenerator = CodeGeneratorFactory.build(codeType);
        return iGenerator.generate(tableGen, codeGenStrategy);
    }
}
