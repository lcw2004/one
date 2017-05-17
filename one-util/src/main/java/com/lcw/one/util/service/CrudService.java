package com.lcw.one.util.service;

import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.persistence.entity.TreeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class CrudService<D extends BaseRepositoryImpl, T> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected D dao;

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
    public T update(T entity) {
        if (entity instanceof TreeEntity) {
            entity = (T) dao.saveTreeEntity(entity);
        } else {
            entity = (T) dao.save(entity);
        }
        return entity;
    }

    @Transactional
    public void delete(String id) {
        dao.delete(id);
    }

    public T get(String id) {
        T t = (T) dao.findOne(id);
        loadTreeParent(t);
        return t;
    }

    private void loadTreeParent(T t) {
        if (t instanceof TreeEntity) {
            TreeEntity treeEntity = (TreeEntity) t;
            treeEntity.setParent((TreeEntity) dao.findOne(treeEntity.getParentId()));
        }
    }

    public List<T> findAll() {
        return dao.findAll();
    }


}