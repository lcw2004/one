package com.lcw.one.sys.service;

import com.lcw.one.sys.entity.TreeEntity;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 2016-11-27.
 * @auth Licw
 */
public class TreeEntityUtil<T extends TreeEntity> {

    /**
     * 将菜单列表组织为菜单树
     *
     * @param topElement
     * @param list
     * @return
     */
    public T organizeListAsTree(T topElement, List<T> list) {
        // 按父ID将菜单归类
        Map<String, List<T>> childListByParentId = organizeListAsMapByParentId(list);

        // 递归组织菜单结构
        recursionChildList(childListByParentId, topElement);

        return topElement;
    }

    /**
     * 将菜单列表重新组织为Map，以父ID为键，Menu List为值
     *
     * @param list
     * @return
     */
    private Map<String, List<T>> organizeListAsMapByParentId(List<T> list) {
        Map<String, List<T>> childListMap = new HashMap<>();
        for (T treeEntity : list) {
            if (treeEntity.getParent() != null) {
                String parentId = treeEntity.getParentId();
                List<T> childList;
                if (childListMap.containsKey(parentId)) {
                    childList = childListMap.get(parentId);
                } else {
                    childList = new ArrayList<>();
                }

                childList.add(treeEntity);
                childListMap.put(parentId, childList);
            }

            // 清空childList属性和parent属性，减少重复数据
            treeEntity.setChildList(null);
            treeEntity.setParent(null);
        }
        return childListMap;
    }

    /**
     * 递归从Map中将Menu List设置到对应的Menu的childList属性中
     *
     * @param childListMap
     * @param parent
     */
    private void recursionChildList(Map<String, List<T>> childListMap, T parent) {
        if (childListMap != null && childListMap.containsKey(parent.getId())) {
            parent.setChildList(childListMap.get(parent.getId()));
        }

        if (parent.getChildList() == null || parent.getChildList().isEmpty()) {
            return;
        }

        for (int i = 0; i < parent.getChildList().size(); i++) {
            T child = (T) parent.getChildList().get(i);
            recursionChildList(childListMap, child);
        }
    }


    @Transient
    public void sortList(List<T> list, List<T> sourcelist, String parentId) {
        for (int i = 0; i < sourcelist.size(); i++) {
            T e = sourcelist.get(i);
            if (e.getParent() != null && e.getParent().getId() != null
                    && e.getParent().getId().equals(parentId)) {
                list.add(e);
                // 判断是否还有子节点, 有则继续获取子节点
                for (int j = 0; j < sourcelist.size(); j++) {
                    T childe = sourcelist.get(j);
                    if (childe.getParent() != null && childe.getParent().getId() != null && childe.getParent().getId().equals(e.getId())) {
                        sortList(list, sourcelist, e.getId());
                        break;
                    }
                }
            }
        }
    }
}
