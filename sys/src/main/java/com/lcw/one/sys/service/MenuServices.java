package com.lcw.one.sys.service;

import com.lcw.one.sys.dao.LogDao;
import com.lcw.one.sys.dao.MenuDao;
import com.lcw.one.sys.entity.Log;
import com.lcw.one.sys.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class MenuServices extends CrudService<MenuDao, Menu> {

    public List<Menu> findByUserId(String userId){
        return dao.findByUserId(userId);
    }

    public Menu organizeMenuListAsMenuTree(Menu menu, List<Menu> menuList) {
        return new TreeEntityUtil<Menu>().organizeMenuListAsMenuTree(menu, menuList);
    }

}
