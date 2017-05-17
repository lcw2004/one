package com.lcw.one.sys.constant;

public enum SysDefaultRoleEnum {
    EXPERT_ROLE("20", "评标专家"),
    SUPPLIER_ROLE("30", "供应商");

    private String roleId;
    private String roleName;

    SysDefaultRoleEnum(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
