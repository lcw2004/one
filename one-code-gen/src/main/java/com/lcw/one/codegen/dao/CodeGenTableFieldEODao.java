package com.lcw.one.codegen.dao;

import com.lcw.one.codegen.entity.CodeGenTableFieldEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import java.util.List;

@Component
public class CodeGenTableFieldEODao extends BaseRepositoryImpl<CodeGenTableFieldEO, String> {

    @Autowired
    public CodeGenTableFieldEODao (EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(CodeGenTableFieldEO.class, entityManager), entityManager);
    }

    public List<CodeGenTableFieldEO> listByTableId(String tableId) {
        return list("from CodeGenTableFieldEO where tableId = ?1 order by orderIndex", tableId);
    }

}