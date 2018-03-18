package com.lcw.one.codegen.service;

import com.lcw.one.codegen.bean.CodeGenStrategy;
import com.lcw.one.codegen.bean.TableFieldGen;
import com.lcw.one.codegen.bean.TableMetaInfo;
import com.lcw.one.codegen.bean.TableGen;
import com.lcw.one.codegen.dao.CodeGenTableEODao;
import com.lcw.one.codegen.entity.CodeGenTableEO;
import com.lcw.one.codegen.entity.CodeGenTableFieldEO;
import com.lcw.one.codegen.util.NameUtils;
import com.lcw.one.util.constant.YesOrNoEnum;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CodeGenTableEOService extends CrudService<CodeGenTableEODao, CodeGenTableEO, String> {

    @Autowired
    private TableMetaInfoService tableMetaInfoService;

    @Autowired
    private CodeGenTableFieldEOService codeGenTableFieldEOService;

    public TableGen getTableByTableId(String tableId, CodeGenStrategy codeGenStrategy) {
        CodeGenTableEO codeGenTableEO = this.get(tableId);
        TableGen tableGen = ObjectUtils.cloneAs(codeGenTableEO, TableGen.class);
        tableGen.setClassName(codeGenStrategy.getEntityPrefix() + NameUtils.toUpperCamel(tableGen.getTableId()) + codeGenStrategy.getEntitySuffix());
        tableGen.setPackageName(codeGenStrategy.getModulePackage());

        List<CodeGenTableFieldEO> codeGenTableFieldEOList = codeGenTableFieldEOService.listByTableId(tableId);
        List<TableFieldGen> tableFieldGenList = ObjectUtils.cloneAs(codeGenTableFieldEOList, TableFieldGen.class);
        tableGen.setFieldList(tableFieldGenList);
        return tableGen;
    }

    public List<CodeGenTableEO> list(String[] tableList) {
        List<CodeGenTableEO> codeGenTableEOList = dao.list(tableList);
        Map<String, CodeGenTableEO> codeGenTableEOMap = ObjectUtils.asMapByFiled(codeGenTableEOList, "tableId");

        for (String tableId : tableList) {
            if (codeGenTableEOMap == null || !codeGenTableEOMap.containsKey(tableId)) {
                TableMetaInfo table = tableMetaInfoService.getTableByTableName(tableId);
                CodeGenTableEO codeGenTableEO = new CodeGenTableEO();
                codeGenTableEO.setTableName(getTableName(table.getTableNameCn()));
                codeGenTableEO.setTableId(tableId);
                codeGenTableEO.setIsBean(YesOrNoEnum.YES.getValue());
                codeGenTableEO.setIsDao(YesOrNoEnum.YES.getValue());
                codeGenTableEO.setIsService(YesOrNoEnum.YES.getValue());
                Integer yesOrNo = YesOrNoEnum.YES.getValue();
                if (table.getIsJointKey()) {
                    // 联合主键的数据不生成Rest接口，应该依托于其他页面
                    yesOrNo = YesOrNoEnum.NO.getValue();
                }
                codeGenTableEO.setIsRest(yesOrNo);
                codeGenTableEO.setIsPageList(yesOrNo);
                codeGenTableEO.setIsPageForm(yesOrNo);
                codeGenTableEO.setIsPageModal(yesOrNo);

                this.save(codeGenTableEO);
                codeGenTableEOList.add(codeGenTableEO);

                codeGenTableFieldEOService.fetchTableInfo(tableId);
            }
        }
        return codeGenTableEOList;
    }

    private String getTableName(String tableName) {
        if (tableName.endsWith("表")) {
            tableName = tableName.substring(0, tableName.indexOf("表"));
        }
        return tableName;
    }

}
