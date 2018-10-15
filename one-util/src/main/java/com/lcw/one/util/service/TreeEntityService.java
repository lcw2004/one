package com.lcw.one.util.service;

import com.lcw.one.util.constant.DeleteFlagEnum;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import com.lcw.one.util.persistence.entity.TreeEntity;
import com.lcw.one.util.persistence.entity.TreeEntityUtil;
import com.lcw.one.util.utils.CollectionUtils;
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

    public T listAsTree() {
        TreeEntity treeEntity = organizeListAsTree(getTopEntity(), findAll());
        return (T) treeEntity;
    }

    public List<T> listByParentId(String parentId) {
        return dao.list("from " + getDao().getJpaEntityInformation().getEntityName() + " where parentId = ?1", parentId);
    }

    public void syncParentIds() {
        TreeEntity treeEntity = getTopEntity();
        if (treeEntity.getParentId().equals("0")) {
            treeEntity.setParentIds("0,");
        }
        recursionSetParentIds(treeEntity);
    }

    private void recursionSetParentIds (TreeEntity parentEntity) {
        logger.info("Sync parentIds of [{}]", parentEntity.getName());
        List<T> treeEntityList = listByParentId(parentEntity.getId());
        if (CollectionUtils.isNotEmpty(treeEntityList)) {
            // 更新子节点的parentIds
            String parentIds = getParentIds(parentEntity);
            for (TreeEntity treeEntityChild : treeEntityList) {
                treeEntityChild.setParentIds(parentIds);
            }
            this.getDao().save(treeEntityList);

            // 递归
            for (TreeEntity treeEntityChild : treeEntityList) {
                recursionSetParentIds(treeEntityChild);
            }
        }
    }

    private String getParentIds(TreeEntity parentEntity) {
        String newParentId = null;
        String parentIds = parentEntity.getParentIds();
        if (parentIds.endsWith(",")) {
            newParentId = parentIds + parentEntity.getId() + ",";
        } else {
            newParentId = "," + parentIds + parentEntity.getId() + ",";
        }
        return newParentId;
    }
}
