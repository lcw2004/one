<%@ page contentType="text/html;charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>

<script>
    Vue.component("menuTree", {
        template: "#menuTree",
        props: {
            menu: {
                type: Object,
                require: true
            },
            config: {
                type: Object,
                required: true
            },
            value: {
                type: Object,
                required: true
            }
        },
        data: function () {
            return {
                selected: ""
            }
        },
        methods: {
            selectOk : function () {
                this.value = this.selected;
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
                    <tree-element :parent="menu" :level="1" :value.sync="selected"></tree-element>
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

<script>
    Vue.component("treeElement", {
        template: "#treeElement",
        props: {
            parent: {
                type: Object,
                require: true
            },
            level: {
                type: Number,
                require: true
            },
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
            toggole: function () {
                this.isExpanded = !this.isExpanded
            },
            select: function () {
                this.value = this.parent;
            }
        },
        computed: {
            isFolder: function () {
                return this.parent.childList != null && this.parent.childList.length > 0;
            }
        }
    });
</script>
<template id="treeElement">
    <span v-show="parent.id != 1">
        <a @click="select()">选中</a>
        <i @click="toggole()" v-show="isFolder && isExpanded" class="fa fa-folder-open-o"></i>
        <i @click="toggole()" v-show="isFolder && !isExpanded" class="fa  fa-folder-o"></i>
        <span @click="toggole()" v-text="parent.name"></span>
    </span>

    <ul v-for="child of parent.childList" v-show="isExpanded">
        <li>
            <tree-element :parent="child" :level="level + 1" :value.sync="value"></tree-element>
        </li>
    </ul>
</template>