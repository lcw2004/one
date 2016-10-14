package com.lcw.one.modules.sys.service;

import com.lcw.one.common.persistence.Page;
import com.lcw.one.modules.sys.dao.RoleDao;
import com.lcw.one.modules.sys.entity.Dict;
import com.lcw.one.modules.sys.entity.Role;
import com.lcw.one.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Page<Role> find(Page<Role> page) {
        return roleDao.find(page);
    }

    public Role get(String id) {
        Role role = roleDao.get(id);
        role.getMenuIdList();
        return role;
    }

    public List<Role> findAll() {
        return UserUtils.getRoleList();
    }

    @Transactional(readOnly = false)
    public void save(Role role) {
        roleDao.clear();
        roleDao.save(role);
    }

    @Transactional(readOnly = false)
    public void delete(String id) {
        roleDao.deleteById(id);
    }
}
