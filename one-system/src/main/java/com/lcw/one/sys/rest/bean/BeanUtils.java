package com.lcw.one.sys.rest.bean;


import com.lcw.one.sys.entity.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 2016-10-05.
 * @auth Licw
 */
public class BeanUtils {

    public static List to(List<Role> list) {
        List newList = new ArrayList(list.size());
        for (Role role : list) {
            newList.add(new Role4Rest(role));
        }
        return newList;
    }

}
