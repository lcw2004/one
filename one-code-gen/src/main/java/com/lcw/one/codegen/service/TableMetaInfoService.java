package com.lcw.one.codegen.service;

import com.lcw.one.codegen.bean.*;
import com.lcw.one.codegen.core.generate.CodeGeneratorFactory;
import com.lcw.one.codegen.core.generate.IGenerator;
import com.lcw.one.codegen.dao.TableMetaInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableMetaInfoService {

    @Autowired
    private TableMetaInfoDao tableMetaInfoDao;

    public List<String> listTableName(String tableName) {
        return tableMetaInfoDao.listTableName(tableName);
    }

    public TableMetaInfo getTableByTableName(String tableName) {
        return tableMetaInfoDao.getTableByTableName(tableName);
    }

    public List<TableFieldMetaInfo> listTableField(final String tableName) {
        return tableMetaInfoDao.listTableField(tableName);
    }

}
