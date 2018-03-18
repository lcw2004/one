package com.lcw.one.util.service;

import com.lcw.one.util.constant.DeleteFlagEnum;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.persistence.entity.TreeEntity;
import com.lcw.one.util.persistence.entity.TreeEntityUtil;
import com.lcw.one.util.utils.UUID;

import java.io.Serializable;
import java.util.List;

public abstract class TreeEntityService<D extends BaseRepositoryImpl, T extends TreeEntity, ID extends Serializable> extends CrudService<D, T, ID> {

    @Override
    public T save(T t) {
        t.setId(UUID.randomUUID(10));
        t.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        dao.saveTreeEntity(t);
        return t;
    }

    @Override
    public T update(T t) {
        dao.saveTreeEntity(t);
        return t;
    }

    @Override
    public void delete(ID id) {
        dao.deleteTreeEntity(id);
    }

    @Override
    public List<T> findAll() {
        return dao.findAllWithoutDeleted();
    }

    public T organizeListAsTree(T menu, List<T> menuList) {
        return new TreeEntityUtil<T>().organizeListAsTree(menu, menuList);
    }

    public void deleteTreeEntity(String id) {
        dao.deleteTreeEntity(id);
    }

    public abstract T getTopEntity();

    public TreeEntity listAsTree() {
        TreeEntity treeEntity = organizeListAsTree(getTopEntity(), findAll());
        return treeEntity;
    }
}
