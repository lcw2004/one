<%@ page contentType="text/html;charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>

<%--菜单树模态窗--%>
<script>
    Vue.component("menuTree", {
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
            // Init
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
                    <tree-element :element="menu" :level="1" :value.sync="selected"></tree-element>
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

<%--菜单树组件--%>
<script>
    Vue.component("treeElement", {
        template: "#treeElement",
        props: {
            /**
             * 元素
             */
            element: {
                type: Object,
                require: true
            },
            /**
             * 当前遍历的级别
             */
            level: {
                type: Number,
                require: true
            },
            /**
             * 选中的项
             */
            value: {
                type: Object,
                require: true
            }
        },
        data: function () {
            return {
                isExpanded: this.level < 2
            }
        },
        methods: {
            /**
             * 切换展开 / 关闭状态
             */
            toggole: function () {
                this.isExpanded = !this.isExpanded
            },
            /**
             * 选中元素
             */
            select: function () {
                this.value = this.element;
            }
        },
        computed: {
            /**
             * 是否显示文件夹图片
             * @returns {boolean}
             */
            isFolder: function () {
                return this.element.childList != null && this.element.childList.length > 0;
            }
        }
    });
</script>
<template id="treeElement">
    <span v-show="element.id != 1">
        <i @click="toggole()" v-show="isFolder && isExpanded" class="fa fa-folder-open-o"></i>
        <i @click="toggole()" v-show="isFolder && !isExpanded" class="fa  fa-folder-o"></i>
        <span @click="toggole()" v-text="element.name"></span>
        <a @click="select()">选中</a>
    </span>

    <ul v-for="child of element.childList" v-show="isExpanded">
        <li>
            <tree-element :element="child" :level="level + 1" :value.sync="value"></tree-element>
        </li>
    </ul>
</template>