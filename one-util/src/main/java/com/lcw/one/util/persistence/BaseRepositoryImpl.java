package com.lcw.one.util.persistence;

import com.lcw.one.util.constant.DeleteFlagEnum;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.persistence.entity.TreeEntity;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.UUID;
import com.lcw.one.util.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
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

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public JpaEntityInformation getJpaEntityInformation() {
        return jpaEntityInformation;
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
        return this.save(entity);
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
        Query query = RepositoryUtils.getQueryWithParams(entityManager, hql, params);
        return RepositoryUtils.getFirst(query);
    }

    @Override
    public T getByHql(String hql, Map<String, Object> params) {
        Query query = RepositoryUtils.getQueryWithParamMap(entityManager, hql, params);
        return RepositoryUtils.getFirst(query);
    }

    @Override
    public <E> List<E> list(String hql, Object... params) {
        return executeList(hql, params);
    }

    @Override
    public <E> List<E> list(String hql, Map<String, Object> params) {
        return executeList(hql, params);
    }

    @Override
    public <E> List<E> findAllWithoutDeleted() {
        return list("from " + jpaEntityInformation.getEntityName() + " where delFlag = 0");
    }

    @Override
    public PageInfo<T> page(PageInfo pageInfo, String hql, Object... params) {
        return executePage(pageInfo, hql, params);
    }

    @Override
    public PageInfo<T> page(PageInfo pageInfo, String hql, Map<String, Object> params) {
        return executePage(pageInfo, hql, params);
    }

    @Override
    public int executeUpdate(String hql, Object... params) {
        Query query = RepositoryUtils.getQueryWithParams(entityManager, hql, params);
        return query.executeUpdate();
    }

    @Override
    public <E> E executeGet(String hql, Object... params) {
        Query query = RepositoryUtils.getQueryWithParams(entityManager, hql, params);
        return RepositoryUtils.getFirst(query);
    }

    @Override
    public <E> E executeGet(String hql, Map<String, Object> params) {
        Query query = RepositoryUtils.getQueryWithParamMap(entityManager, hql, params);
        return RepositoryUtils.getFirst(query);
    }

    @Override
    public <E> PageInfo<E> executePage(PageInfo pageInfo, String hql, Object... params) {
        // 查询数据，统计条数
        Query query = RepositoryUtils.getQueryWithParams(entityManager, hql, params);
        query.setFirstResult((pageInfo.getPageNo() - 1) * pageInfo.getPageSize());
        query.setMaxResults(pageInfo.getPageSize());
        List<E> resultList = query.getResultList();

        Query pageQuery = RepositoryUtils.getQueryWithParams(entityManager, RepositoryUtils.buildCountHQL(hql), params);
        long count = (long) pageQuery.getSingleResult();

        pageInfo.setList(resultList);
        pageInfo.setCount(count);
        return pageInfo;
    }

    @Override
    public <E> PageInfo<E> executePage(PageInfo pageInfo, String hql, Map<String, Object> params) {
        // 查询数据
        Query query = RepositoryUtils.getQueryWithParamMap(entityManager, hql, params);
        query.setFirstResult((pageInfo.getPageNo() - 1) * pageInfo.getPageSize());
        query.setMaxResults(pageInfo.getPageSize());
        List<T> resultList = query.getResultList();

        // 统计条数
        Query pageQuery = RepositoryUtils.getQueryWithParamMap(entityManager, RepositoryUtils.buildCountHQL(hql), params);
        long count = (long) pageQuery.getSingleResult();

        pageInfo.setList(resultList);
        pageInfo.setCount(count);
        return pageInfo;
    }

    @Override
    public <E> List<E> executeList(String hql, Object... params) {
        Query query = RepositoryUtils.getQueryWithParams(entityManager, hql, params);
        return query.getResultList();
    }

    @Override
    public <E> List<E> executeList(String hql, Map<String, Object> params) {
        Query query = RepositoryUtils.getQueryWithParamMap(entityManager, hql, params);
        return query.getResultList();
    }
}