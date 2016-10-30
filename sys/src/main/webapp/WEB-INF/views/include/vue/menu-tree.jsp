<%@ page contentType="text/html;charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>

<%--菜单树模态窗--%>
<script>
    Vue.component("menu-tree", {
        template: "#menuTree",
        props: {
            // 模态窗属性配置
            config: {
                type: Object,
                required: true
            },
            // 选中的数据的值
            value: {
                type: Object,
                required: true
            }
        },
        ready : function () {
            var actions = {
                getMenuTree: {method: "get", url: '${ctxRest}/sys/menu/tree'}
            };
            var resource = this.$resource(null, {}, actions);
            resource.getMenuTree().then(function (response) {
                this.menu = response.body;
            });
        },
        data: function () {
            return {
                menu : {},
                value: {}
            }
        },
        methods: {
            selectOk : function () {
                this.config.show = false;

                // 只返回ID和名称
                var returnValue = {
                    id: this.value.id,
                    name : this.value.name
                };

                this.$emit("input", returnValue);
            }
        }
    });
</script>
<template id="menuTree">
    <div class="modal" v-show="config.show">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="config.show = false">
                        <span aria-hidden="true">×</span></button>
                    <h4 class="modal-title" v-text="config.title"></h4>
                </div>
                <div class="modal-body" style="overflow-y: auto">
                    <div class="menu-tree">
                        <tree-element :element="menu" :level="1" v-model="value"></tree-element>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="pull-left">选择：{{ value.name }}</span>
                    <button type="button" class="btn btn-default " data-dismiss="modal" @click="config.show = false">取消</button>
                    <button type="button" class="btn btn-primary" @click="selectOk()">确认</button>
                </div>
            </div>
        </div>
    </div>
</template>
<%--菜单树模态窗--%>