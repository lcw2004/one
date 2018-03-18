package com.lcw.one.codegen.bean.consts;

public enum CodeTypeEnum {

    /**
     * 实体类
     */
    Bean("Bean"),

    /**
     * Dao层
     */
    Dao("Dao"),

    /**
     * Service层
     */
    Service("Service"),

    /**
     * Rest Controller层
     */
    Controller("Controller"),

    /**
     * Vue 列表页面
     */
    VueList("VueList"),

    /**
     * Vue 查看详情页面
     */
    VueView("VueView"),

    /**
     * Vue 编辑/添加页面
     */
    VueEdit("VueEdit"),

    /**
     * Vue 模态框选择器页面
     */
    VueModal("VueModal");

    private String value;

    CodeTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CodeTypeEnum get(String value) {
        CodeTypeEnum[] types = CodeTypeEnum.values();

        for (CodeTypeEnum type : types) {
            if (value.equals(type.getValue())) {
                return type;
            }
        }
        return null;
    }
}
