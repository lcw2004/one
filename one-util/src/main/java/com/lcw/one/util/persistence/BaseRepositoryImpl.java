package com.lcw.one.util.persistence;

import com.lcw.one.util.constant.DeleteFlagEnum;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.entity.TreeEntity;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.PersistenceUtils;
import com.lcw.one.util.utils.UUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements IBaseRepository<T, ID> {

    private static final Logger logger = LoggerFactory.getLogger(BaseRepositoryImpl.class);
    protected final EntityManager entityManager;
    protected final JpaEntityInformation jpaEntityInformation;

    public BaseRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.jpaEntityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    @Override
    public T saveTreeEntity(T entity) {
        TreeEntity treeEntity = (TreeEntity) entity;
        if (StringUtils.isEmpty(treeEntity.getId())) {
            treeEntity.setId(UUID.randomUUID());
        }

        String oldParentIds = treeEntity.getParentIds();
        TreeEntity parent = treeEntity.getParent();
        if (parent != null) {
            treeEntity.setParentId(parent.getId());
            treeEntity.setParentIds(parent.getParentIds() + parent.getId() + ",");
        }

        if (StringUtils.isNotEmpty(oldParentIds)) {
            List<TreeEntity> treeEntityList = list("from " + entity.getClass().getSimpleName() + " where parent_ids like ?", "%," + treeEntity.getId() + ",%");
            if (CollectionUtils.isNotEmpty(treeEntityList)) {
                for (TreeEntity childTreeEntity : treeEntityList) {
                    childTreeEntity.setParentIds(childTreeEntity.getParentIds().replace(oldParentIds, treeEntity.getParentIds()));
                    this.save((T) childTreeEntity);
                }
            }
        }
        return (T) this.save(entity);
    }

    @Override
    public void deleteLogic(ID id) {
        String hql = "update " + jpaEntityInformation.getEntityName() + " set delFlag = '" + DeleteFlagEnum.DELETE.getValue() + "' where id = ?1";
        executeUpdate(hql, id);
    }

    @Override
    public void deleteTreeEntity(ID id) {
        T t = getOne(id);
        if (t != null && t instanceof TreeEntity) {
            TreeEntity treeEntity = (TreeEntity) t;
            treeEntity.setDelFlag(DeleteFlagEnum.DELETE.getValue());
            save((T) treeEntity);

            String hql = "update " + jpaEntityInformation.getEntityName() + " set delFlag = '" + DeleteFlagEnum.DELETE.getValue() + "' where parentIds like ?";
            executeUpdate(hql, "%" + treeEntity.getId() + ",%");
        }
    }

    @Override
    public T getByHql(String hql, Object... params) {
        Query query = getQueryWithParams(hql, params);
        return getFirst(query);
    }

    @Override
    public T getByHql(String hql, Map<String, Object> params) {
        Query query = getQueryWithParamMap(hql, params);
        return getFirst(query);
    }

    @Override
    public <T> List<T> list(String hql, Object... params) {
        Query query = getQueryWithParams(hql, params);
        return query.getResultList();
    }

    @Override
    public <T> List<T> list(String hql, Map<String, Object> params) {
        Query query = getQueryWithParamMap(hql, params);
        return query.getResultList();
    }

    @Override
    public <T> List<T> findAllWithoutDeleted() {
        return list("from " + jpaEntityInformation.getEntityName() + " where delFlag = 0");
    }

    @Override
    public PageInfo<T> page(PageInfo pageInfo, String hql, Object... params) {
        // 查询数据，统计条数
        Query query = getQueryWithParams(hql, params);
        query.setFirstResult((pageInfo.getPageNo() - 1) * pageInfo.getPageSize());
        query.setMaxResults(pageInfo.getPageSize());
        List<T> resultList = query.getResultList();

        Query pageQuery = getQueryWithParams(buildCountHQL(hql), params);
        long count = (long) pageQuery.getSingleResult();

        pageInfo.setList(resultList);
        pageInfo.setCount(count);
        return pageInfo;
    }

    @Override
    public PageInfo<T> page(PageInfo pageInfo, String hql, Map<String, Object> params) {
        // 查询数据，统计条数
        Query query = getQueryWithParamMap(hql, params);
        query.setFirstResult((pageInfo.getPageNo() - 1) * pageInfo.getPageSize());
        query.setMaxResults(pageInfo.getPageSize());
        List<T> resultList = query.getResultList();

        Query pageQuery = getQueryWithParamMap(buildCountHQL(hql), params);
        long count = (long) pageQuery.getSingleResult();

        pageInfo.setList(resultList);
        pageInfo.setCount(count);
        return pageInfo;
    }

    @Override
    public int executeUpdate(String hql, Object... params) {
        Query query = getQueryWithParams(hql, params);
        return query.executeUpdate();
    }

    private static String buildCountHQL(String hql) {
        StringBuilder countHql = new StringBuilder();
        if (hql.trim().startsWith("select")) {
            int fromIndex = hql.indexOf("from");
            int selectIndex = hql.indexOf("select");
            String hqlBetweenSelectAndForm = hql.substring(selectIndex + 6, fromIndex);
            String hqlNoSelect = hql.substring(fromIndex, hql.length());
            countHql.append(" select count(").append(hqlBetweenSelectAndForm).append(") ").append(hqlNoSelect);
        } else if (hql.trim().startsWith("from")) {
            countHql.append(" select count(*) ").append(hql);
        }
        return countHql.toString();
    }

    private Query getQueryWithParamMap(String hql, Map<String, Object> params) {
        logger.info("HQL [{}]", hql);
        if(CollectionUtils.isNotEmpty(params)) {
            logger.info("HQL Param: {}", params);
            logger.info("HQL Filled: [{}]", PersistenceUtils.fillParams(hql, params));
        }

        Query query = entityManager.createQuery(hql);
        if (CollectionUtils.isNotEmpty(params)) {
            for (String key : params.keySet()) {
                Object value = params.get(key);
                query.setParameter(key, value);
            }
        }
        return query;
    }

    private Query getQueryWithParams(String hql, Object[] params) {
        logger.info("HQL [{}]", hql);
        if(params != null && params.length > 0) {
            logger.info("HQL Param: {}", Arrays.asList(params));
            logger.info("HQL Filled: [{}]", PersistenceUtils.fillParams(hql, params));
        }

        Query query = entityManager.createQuery(hql);
        int index = 1;
        for (Object param : params) {
            query.setParameter(index, param);
            index++;
        }
        return query;
    }

    private T getFirst(Query query) {
        T obj = null;
        List<T> resultList = query.getResultList();
        if (CollectionUtils.isNotEmpty(resultList)) {
            obj = resultList.get(0);
        }
        return obj;
    }
}