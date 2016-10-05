package com.lcw.one.modules.sys.rest.bean;

import com.lcw.one.modules.sys.entity.Office;
import com.lcw.one.modules.sys.entity.Role;

public class Role4Rest {

    private String id;
    private String name;
    private Office office;

    public Role4Rest() {
    }

    public Role4Rest(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.office = role.getOffice();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

}
