package com.lcw.one.util.service;

import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.persistence.entity.TreeEntity;
import com.lcw.one.util.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class CrudService<D extends BaseRepositoryImpl, T, ID extends Serializable> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected D dao;

    public D getDao() {
        return dao;
    }

    @Transactional
    public T save(T entity) {
        if (entity instanceof TreeEntity) {
            entity = (T) dao.saveTreeEntity(entity);
        } else {
            entity = (T) dao.save(entity);
        }
        return entity;
    }

    @Transactional
    public List<T> save(List<T> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        dao.saveAll(entityList);
        return entityList;
    }

    @Transactional
    public T update(T entity) {
        if (entity instanceof TreeEntity) {
            entity = (T) dao.saveTreeEntity(entity);
        } else {
            entity = (T) dao.save(entity);
        }
        return entity;
    }

    @Transactional
    public List<T> update(List<T> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        dao.save(entityList);
        return entityList;
    }

    @Transactional
    public void delete(ID id) {
        dao.deleteById(id);
    }

    @Transactional
    public void delete(ID... ids) {
        dao.deleteInBatch(Arrays.asList(ids));
    }

    public T get(ID id) {
        T t = null;
        Optional<T> optional = dao.findById(id);
        if (optional.isPresent()) {
            t = optional.get();
            loadTreeParent(t);
        }
        return t;
    }

    public T findById(ID id) {
        return (T) dao.findById(id).get();
    }

    public T getOne(ID id) {
        T t = (T) dao.getOne(id);
        loadTreeParent(t);
        return t;
    }

    private void loadTreeParent(T t) {
        if (t instanceof TreeEntity) {
            TreeEntity treeEntity = (TreeEntity) t;
            treeEntity.setParent((TreeEntity) this.get((ID) treeEntity.getParentId()));
        }
    }

    public List<T> findAll() {
        return dao.findAll();
    }

    public void flush() {
        dao.flush();
    }

}