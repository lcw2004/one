package com.lcw.one.sys.service;

import com.lcw.one.common.persistence.Page;
import com.lcw.one.common.util.StringUtils;
import com.lcw.one.sys.dao.RoleDao;
import com.lcw.one.sys.entity.Dict;
import com.lcw.one.sys.entity.Role;
import com.lcw.one.sys.utils.UserUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleService extends CrudService<RoleDao, Role> {

    public Page<Role> find(Page<Role> page, String name) {
        DetachedCriteria dc = dao.createDetachedCriteria();
        dc.add(Restrictions.eq(Dict.FIELD_DEL_FLAG, Dict.DEL_FLAG_NORMAL));
        if (StringUtils.isNotEmpty(name)) {
            dc.add(Restrictions.like("name", "%" + name + "%"));
        }
        dc.addOrder(Order.desc("createDate"));
        return dao.find(page, dc);
    }

    public Role get(String id) {
        Role role = dao.get(id);
        role.getMenuIdList();
        return role;
    }

}
