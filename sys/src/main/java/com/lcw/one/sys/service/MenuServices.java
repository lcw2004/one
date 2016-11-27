package com.lcw.one.sys.service;

import com.lcw.one.sys.dao.MenuDao;
import com.lcw.one.sys.entity.Menu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MenuServices extends CrudService<MenuDao, Menu> {

    public List<Menu> findByUserId(String userId){
        return dao.findByUserId(userId);
    }

    public Menu organizeListAsTree(Menu menu, List<Menu> menuList) {
        return new TreeEntityUtil<Menu>().organizeListAsTree(menu, menuList);
    }

}
