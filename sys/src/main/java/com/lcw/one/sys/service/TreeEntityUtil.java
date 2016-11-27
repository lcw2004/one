package com.lcw.one.sys.service;

import com.lcw.one.sys.entity.Area;
import com.lcw.one.sys.entity.Menu;
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
     * @param topMenu
     * @param list
     * @return
     */
    public  T organizeMenuListAsMenuTree(T topMenu, List<T> list) {
        // 按父ID将菜单归类
        Map<String, List<T>> childMenuListMap = organizeListAsMapByParentId(list);

        // 递归组织菜单结构
        recursionChildMenuList(childMenuListMap, topMenu);

        return topMenu;
    }

    /**
     * 将菜单列表重新组织为Map，以父ID为键，Menu List为值
     *
     * @param list
     * @return
     */
    private  Map<String, List<T>> organizeListAsMapByParentId(List<T> list) {
        Map<String, List<T>> childMenuListMap = new HashMap<>();
        for (T menu : list) {
            if (menu.getParent() != null) {
                String parentId = menu.getParentId();
                List<T> menuList;
                if (childMenuListMap.containsKey(parentId)) {
                    menuList = childMenuListMap.get(parentId);
                } else {
                    menuList = new ArrayList<>();
                }

                menuList.add(menu);
                childMenuListMap.put(parentId, menuList);
            }

            menu.setChildList(null);
            menu.setParent(null);
        }
        return childMenuListMap;
    }

    /**
     * 递归从Map中将Menu List设置到对应的Menu的childList属性中
     *
     * @param childMenuListMap
     * @param parentMenu
     */
    private  void recursionChildMenuList(Map<String, List<T>> childMenuListMap, T parentMenu) {
        if(childMenuListMap != null && childMenuListMap.containsKey(parentMenu.getId())) {
            parentMenu.setChildList(childMenuListMap.get(parentMenu.getId()));
        }

        if(parentMenu.getChildList() != null &&parentMenu.getChildList().size() > 0) {
            for (int i = 0; i < parentMenu.getChildList().size(); i++) {
                T childMenu = (T)parentMenu.getChildList().get(i);
                recursionChildMenuList(childMenuListMap, childMenu);
            }
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
