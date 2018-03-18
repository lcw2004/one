package com.lcw.one.codegen.service;

import com.lcw.one.codegen.bean.TableFieldMetaInfo;
import com.lcw.one.codegen.entity.CodeGenTableFieldEO;
import com.lcw.one.codegen.dao.CodeGenTableFieldEODao;
import com.lcw.one.util.constant.YesOrNoEnum;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CodeGenTableFieldEOService extends CrudService<CodeGenTableFieldEODao, CodeGenTableFieldEO, String> {

    @Autowired
    private TableMetaInfoService tableMetaInfoService;


    public List<CodeGenTableFieldEO> listByTableId(String tableId) {
        fetchTableInfo(tableId);
        return dao.listByTableId(tableId);
    }

    public void fetchTableInfo(String tableId) {
        Map<String, CodeGenTableFieldEO> map = ObjectUtils.asMapByFiled(dao.listByTableId(tableId), "fieldId");

        List<TableFieldMetaInfo> tableFieldMetaInfoList = tableMetaInfoService.listTableField(tableId);
        int index = 1;
        for (TableFieldMetaInfo tableFieldMetaInfo : tableFieldMetaInfoList) {
            if (!(map != null && map.containsKey(tableFieldMetaInfo.getTableField()))) {
                CodeGenTableFieldEO codeGenTableFieldEO = new CodeGenTableFieldEO();
                codeGenTableFieldEO.setFieldId(tableFieldMetaInfo.getTableField());
                codeGenTableFieldEO.setTableId(tableId);
                codeGenTableFieldEO.setFieldName(tableFieldMetaInfo.getComment());
                codeGenTableFieldEO.setFieldType(tableFieldMetaInfo.getTableFieldType());
                codeGenTableFieldEO.setIsPrimaryKey(YesOrNoEnum.valueOf(tableFieldMetaInfo.getIsPrimaryKey()).getValue());
                codeGenTableFieldEO.setIsAutoincrement(YesOrNoEnum.valueOf(tableFieldMetaInfo.getIsAutoincrement()).getValue());
                codeGenTableFieldEO.setIsRequired(YesOrNoEnum.valueOf(tableFieldMetaInfo.getIsRequired()).getValue());
                codeGenTableFieldEO.setIsEdit(YesOrNoEnum.YES.getValue());
                codeGenTableFieldEO.setIsInsert(YesOrNoEnum.YES.getValue());
                codeGenTableFieldEO.setIsList(YesOrNoEnum.YES.getValue());
                codeGenTableFieldEO.setIsQuery(YesOrNoEnum.NO.getValue());
                codeGenTableFieldEO.setJavaFieldName(tableFieldMetaInfo.getClassField());
                codeGenTableFieldEO.setJavaType(tableFieldMetaInfo.getJavaType());
                codeGenTableFieldEO.setJavaFieldInclude(tableFieldMetaInfo.getInclude());
                codeGenTableFieldEO.setShowType("1");
                codeGenTableFieldEO.setQueryType("1");
                codeGenTableFieldEO.setOrderIndex(index);
                index++;
                this.save(codeGenTableFieldEO);
            }
        }
    }

}
