package com.lcw.one.sys.rest.bean;

import com.lcw.one.sys.entity.Office;
import com.lcw.one.sys.entity.Role;
import com.lcw.one.sys.utils.DictUtils;

import javax.persistence.Transient;
import java.util.List;

public class Role4Rest {

    private String id;
    private String name;
    private String dataScope;
    private String dataScopeCn;
    private Office office;
    private List<String> menuIdList;

    public Role4Rest() {
    }

    public Role4Rest(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.dataScope = role.getDataScope();
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

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public List<String> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<String> menuIdList) {
        this.menuIdList = menuIdList;
    }

    public String getDataScopeCn() {
        return DictUtils.getDictLabel(dataScope, "sys_data_scope", "");
    }

    public void setDataScopeCn(String dataScopeCn) {
        this.dataScopeCn = dataScopeCn;
    }
}
