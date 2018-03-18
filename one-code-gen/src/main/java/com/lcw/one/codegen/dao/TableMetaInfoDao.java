package com.lcw.one.codegen.dao;

import com.lcw.one.codegen.bean.TableMetaInfo;
import com.lcw.one.codegen.bean.TableFieldMetaInfo;
import com.lcw.one.codegen.util.JdbcFieldTypeMapper;
import com.lcw.one.codegen.util.NameUtils;
import com.lcw.one.util.persistence.RepositoryUtils;
import com.lcw.one.util.utils.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class TableMetaInfoDao {

    @Autowired
    private EntityManager entityManager;

    public List<String> listTableName(String tableName) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT t.TABLE_NAME AS TABLE_NAME,t.TABLE_COMMENT AS TABLE_COMMENT FROM information_schema.TABLES t ");
        sql.append(" WHERE t.TABLE_SCHEMA = (select database())");
        if(StringUtils.isNotEmpty(tableName)) {
            sql.append(" AND t.TABLE_NAME like :tableName");
            params.put("tableName", tableName + "%");
        }
        sql.append(" ORDER BY t.TABLE_NAME");

        Query query = RepositoryUtils.getNativeQueryWithParamMap(entityManager, sql.toString(), params);
        List<Object[]> tableInfo = query.getResultList();

        List<String> tableList = new LinkedList<>();
        for (Object[] objects: tableInfo) {
            tableList.add(String.valueOf(objects[0]));
        }
        Collections.sort(tableList);
        return tableList;
    }

    public TableMetaInfo getTableByTableName(final String tableName) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT t.TABLE_NAME AS TABLE_NAME,t.TABLE_COMMENT AS TABLE_COMMENT FROM information_schema.TABLES t ");
        sql.append(" WHERE t.TABLE_SCHEMA = (select database())");
        sql.append(" AND t.TABLE_NAME = :tableName");
        sql.append(" ORDER BY t.TABLE_NAME");

        Map<String, Object> params = new HashMap<>();
        params.put("tableName", tableName);

        Query query = RepositoryUtils.getNativeQueryWithParamMap(entityManager, sql.toString(), params);
        Object[] tableInfo = (Object[]) query.getSingleResult();

        TableMetaInfo tableMetaInfo = new TableMetaInfo();
        tableMetaInfo.setTableName(String.valueOf(tableInfo[0]));
        tableMetaInfo.setTableNameCn(String.valueOf(tableInfo[1]));
        tableMetaInfo.setTableFieldMetaInfoList(listTableField(tableName));
        return tableMetaInfo;
    }

    public List<TableFieldMetaInfo> listTableField(final String tableName) {
        final List<TableFieldMetaInfo> tableList = new LinkedList<>();
        entityManager.unwrap(Session.class).doWork(connection -> {
            try {
                DatabaseMetaData metadata = connection.getMetaData();

                // 主键
                ResultSet primaryKeyResultSet = metadata.getPrimaryKeys(connection.getCatalog(), null, tableName);
                Set<String> primarySet = new HashSet<>();
                while (primaryKeyResultSet.next()) {
                    primarySet.add(primaryKeyResultSet.getString("COLUMN_NAME"));
                }

                // 字段
                ResultSet resultSet = metadata.getColumns(null, "%", tableName, "%");
                while (resultSet.next()) {
                    TableFieldMetaInfo tableFieldMetaInfo = new TableFieldMetaInfo();
                    tableFieldMetaInfo.setTableField(resultSet.getString("COLUMN_NAME"));
                    tableFieldMetaInfo.setClassField(NameUtils.toLowerCamel(tableFieldMetaInfo.getTableField()));
                    tableFieldMetaInfo.setComment(resultSet.getString("REMARKS"));
                    tableFieldMetaInfo.setTableFieldType(resultSet.getString("TYPE_NAME"));
                    tableFieldMetaInfo.setLength(resultSet.getInt("COLUMN_SIZE"));
                    tableFieldMetaInfo.setPrecision(resultSet.getInt("DECIMAL_DIGITS"));
                    tableFieldMetaInfo.setIsRequired(!resultSet.getBoolean("NULLABLE"));
                    tableFieldMetaInfo.setIsPrimaryKey(primarySet.contains(tableFieldMetaInfo.getTableField()));
                    tableFieldMetaInfo.setIsAutoincrement("YES".equals(resultSet.getString("IS_AUTOINCREMENT")));
                    tableFieldMetaInfo.setInclude(JdbcFieldTypeMapper.getClassTypeByJdbcType(resultSet.getInt("DATA_TYPE")).getName());
                    tableFieldMetaInfo.setJavaType(JdbcFieldTypeMapper.getClassTypeByJdbcType(resultSet.getInt("DATA_TYPE")).getSimpleName());
                    tableList.add(tableFieldMetaInfo);
                }
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        return tableList;
    }
}
