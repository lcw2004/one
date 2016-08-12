<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<%--<modal is-show="true" msg="123123123" callback="okCallback"></modal>--%>

<script>
    Vue.component("menuTree", {
        template : "#menuTree",
        props: {
            menu : {
                type : Object,
                require : true
            }
        },
        methods : {

        }
    });
</script>
<template id="menuTree">
    <div class="modal modal-primary" style="display: block">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="hide()">
                        <span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">确认信息</h4>
                </div>
                <div class="modal-body" style="overflow-y: auto">
                    <tree-element :parent="menu" :level="1"></tree-element>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline" data-dismiss="modal"  @click="hide()">取消</button>
                    <button type="button" class="btn btn-outline" @click="">确认</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    Vue.component("treeElement", {
        template : "#treeElement",
        props: {
            parent: {
                type: Object,
                require: true
            },
            level: {
                type: Number,
                require: true
            }
        },
        data: function () {
            return {
                isExpanded : this.level < 2
            }
        },
        methods : {
            toggole : function () {
                this.isExpanded = !this.isExpanded
            }
        },
        computed: {
            isFolder : function () {
                return this.parent.childList != null && this.parent.childList.length > 0;
            }
        }
    });
</script>
<template id="treeElement">
    <span v-show="true" @click="toggole()">
        <i v-show="isFolder && isExpanded" class="fa fa-folder-open-o"></i>
        <i v-show="isFolder && !isExpanded" class="fa  fa-folder-o"></i>
        <span v-text="parent.name"></span>
    </span>

    <ul v-for="child of parent.childList"  v-show="isExpanded">
        <li>
            <tree-element :parent="child" :level="level + 1"></tree-element>
        </li>
    </ul>
</template>