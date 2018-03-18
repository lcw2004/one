package com.lcw.one.codegen.dao;

import com.lcw.one.codegen.entity.CodeGenTableEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CodeGenTableEODao extends BaseRepositoryImpl<CodeGenTableEO, String> {

    @Autowired
    public CodeGenTableEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(CodeGenTableEO.class, entityManager), entityManager);
    }

    public List<CodeGenTableEO> list(String[] tableList) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        sql.append("from CodeGenTableEO where tableId in :tableList");
        params.put("tableList", Arrays.asList(tableList));
        return list(sql.toString(), params);
    }

}