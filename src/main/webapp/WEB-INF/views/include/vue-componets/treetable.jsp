<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<%-- Vue Treetable 组件--%>
<%-- 参考：http://blog.fengxiaotx.com/archives/545 --%>
<script>
    Vue.component("treetable", {
        template: "#treetable",
        props: {
            menu: {
                type: Object,
                require: true
            }
        },
        data: function () {
            return {
                dataList: []
            }
        },
        methods: {
            loadTreeTable: function () {
                var me = this;
                var tempList = [];

                if (me.menu) {
                    initMenu(me.menu, 1, tempList);
                    me.dataList = tempList;
                }
            },
            toggole: function (menu) {
                var isExpanded = menu.isExpanded;
                if (isExpanded) {
                    toggleChildMenuListRecursion(menu, !isExpanded);
                } else {
                    toggleChildMenuList(menu, !isExpanded);
                }
            }
        },
        watch: {
            menu: function () {
                this.loadTreeTable();
            }
        }
    });
</script>
<template id="treetable">
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th>名称</th>
            <th>链接</th>
            <th>排序</th>
            <th>可见</th>
            <th>权限标识</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
            <tr v-for="obj of dataList" v-show="obj.isShow" track-by="id">
                <td>
                    {{{ obj.level | fillSpace }}}
                    <a @click="toggole(obj)">
                        <i v-show="!obj.isExpanded" class="fa fa-caret-right"></i>
                        <i v-show="obj.isExpanded" class="fa fa-caret-down"></i>
                    </a>
                    <span @click="toggole(obj)" v-text="obj.name"></span>
                </td>
                <td><span v-text="obj.href"></span></td>
                <td><span v-text="obj.sort"></span></td>
                <td><span v-text="obj.isShow"></span></td>
                <td><span v-text="obj.permission"></span></td>
                <td>
                    <a href="${ctx}/sys/dict/form?id={{obj.id}}">修改</a>
                    <a @click="deleteData(obj.id)">删除</a>
                </td>
            </tr>
        </tbody>
    </table>
</template>
<%-- Vue alert 组件 End --%>