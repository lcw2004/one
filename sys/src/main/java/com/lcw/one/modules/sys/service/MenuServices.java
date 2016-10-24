package com.lcw.one.modules.sys.service;

import com.lcw.one.modules.sys.dao.MenuDao;
import com.lcw.one.modules.sys.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class MenuServices extends BaseService {

    @Autowired
    private MenuDao menuDao;

    public Menu get(String id) {
        return menuDao.get(id);
    }

    public List<Menu> findByUserId(String userId){
        return menuDao.findByUserId(userId);
    }

    public List<Menu> findAllList(){
        return menuDao.findAllList();
    }

    /**
     * 将菜单列表组织为菜单树
     * @param topMenu
     * @param list
     * @return
     */
    public Menu organizeMenuListAsMenuTree(Menu topMenu, List<Menu> list) {
        // 按父ID将菜单归类
        Map<String, List<Menu>> childMenuListMap = organizeListAsMapByParentId(list);

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
    private Map<String, List<Menu>> organizeListAsMapByParentId(List<Menu> list) {
        Map<String, List<Menu>> childMenuListMap = new HashMap<>();
        for (Menu menu : list) {
            if (menu.getParent() != null) {
                String parentId = menu.getParent().getId();
                List<Menu> menuList;
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
    private void recursionChildMenuList(Map<String, List<Menu>> childMenuListMap, Menu parentMenu) {
        if(childMenuListMap != null && childMenuListMap.containsKey(parentMenu.getId())) {
            parentMenu.setChildList(childMenuListMap.get(parentMenu.getId()));
        }

        if(parentMenu.getChildList() != null &&parentMenu.getChildList().size() > 0) {
            for (Menu childMenu : parentMenu.getChildList()) {
                recursionChildMenuList(childMenuListMap, childMenu);
            }
        }
    }
}
