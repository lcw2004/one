<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<%-- Vue Treetable 组件--%>
<%-- 参考：http://blog.fengxiaotx.com/archives/545 --%>
<script>
    Vue.component("treetable", {
        template : "#treetable",
        props : {
            menu: {
                type : Object,
                require : true
            }
        },
        data : function () {
            return {
                dataList : []
            }
        },
        methods: {
            loadTreeTable : function () {
                var me = this;
                var tempList = [];

                if(me.menu){
                    initData(me.menu, 1, null);
                    me.dataList = tempList;
                }

                /**
                 * 递归初始化菜单列表
                 * */
                function initData(menu, level, parent) {
                    var childList = menu.childList;
                    if(!childList) {
                        return;
                    }
                    for(var i = 0; i < childList.length; i++) {
                        var childMenu = childList[i];
                        childMenu.level = level;
                        Vue.set(childMenu, "isShow", level <= 3);// 是否显示，1,2,3级菜单默认显示
                        Vue.set(childMenu, "isExpanded", level <= 2);// 是否展开菜单，1,2,3级菜单默认展开
                        tempList.push(childMenu);

                        initData(childMenu, level + 1, childMenu);
                    }
                }
            },
            toggole : function (menu) {
                var isExpanded = menu.isExpanded;
                if(isExpanded) {
                    toggleChildMenuListRecursion(menu, !isExpanded);
                } else {
                    toggleChildMenuList(menu, !isExpanded);
                }

                /**
                 * 切换下一级菜单的状态，主要用于打开，打开的时候只显示下一级，且只修改本级的显示状态
                 * @param menu
                 * @param isShow
                 */
                function toggleChildMenuList(menu, isShow) {
                    // 修改展开状态
                    menu.isExpanded = isShow;

                    // 修改子节点的现实状态
                    var list = menu.childList;
                    if(list == null) {
                        return;
                    }
                    for (var i = 0; i < list.length; i++) {
                        var childMenu = list[i];
                        childMenu.isShow = isShow;
                    }
                }

                /**
                 * 递归切换所有菜单的状态，主要用于关闭，关闭的时候需要修改所有子节点的展开及显示状态
                 * @param menu
                 * @param isShow
                 */
                function toggleChildMenuListRecursion(menu, isShow) {
                    // 修改展开状态
                    menu.isExpanded = isShow;

                    // 修改子节点的现实状态
                    var list = menu.childList;
                    if(list == null) {
                        return;
                    }
                    for (var i = 0; i < list.length; i++) {
                        var childMenu = list[i];
                        childMenu.isShow = isShow;
                        childMenu.isExpanded = isShow;

                        toggleChildMenuListRecursion(childMenu, isShow);
                    }
                }
            }
        },
        watch: {
            menu : function () {
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
                    <span v-text="obj.name"></span>
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