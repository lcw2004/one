package com.lcw.one.workflow.dao;

import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.workflow.entity.FlowTaskInfoEO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class FlowTaskInfoEODao extends BaseRepositoryImpl<FlowTaskInfoEO, String> {

    @Autowired
    public FlowTaskInfoEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(FlowTaskInfoEO.class, entityManager), entityManager);
    }

    public FlowTaskInfoEO getFlowTaskInfoEO(String processKey, String taskKey) {
        return getByHql("from FlowTaskInfoEO where processKey = ?1 and taskKey = ?2", processKey, taskKey);
    }

    public List<FlowTaskInfoEO> listFlowTaskInfoEOByProcessKey(String processKey) {
        return super.list("from FlowTaskInfoEO where processKey = ?1", processKey);
    }

    public void updateFlowTaskInfoValid(String processKey, int valid) {
        super.executeUpdate("update FlowTaskInfoEO set valid = ?1 where processKey = ?2", valid, processKey);
    }

    public void deleteByProcessKey(String processKey) {
        super.executeUpdate("delete from FlowTaskInfoEO where processKey = ?1", processKey);
    }

}