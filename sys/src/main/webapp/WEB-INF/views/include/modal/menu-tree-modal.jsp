<%@ page contentType="text/html;charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>

<%--菜单树模态窗--%>
<script>
    Vue.component("menu-tree-modal", {
        template: "#menuTreeModal",
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
                this.menu = response.json();
            });
        },
        data: function () {
            return {
                menu : {},
                selected: {}
            }
        },
        methods: {
            selectOk : function () {
                this.value = {};
                this.value.id = this.selected.id;
                this.value.name = this.selected.name;
                this.config.show = false;
            }
        }
    });
</script>
<template id="menuTreeModal">
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
                        <tree :element="menu" :value.sync="selected" select-type="radio"></tree>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="pull-left">选择：{{ selected.name }}</span>
                    <button type="button" class="btn btn-default " data-dismiss="modal" @click="config.show = false">取消</button>
                    <button type="button" class="btn btn-primary" @click="selectOk()">确认</button>
                </div>
            </div>
        </div>
    </div>
</template>
<%--菜单树模态窗--%>