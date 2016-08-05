<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<%-- Vue Treetable 组件--%>
<%-- 参考：http://blog.fengxiaotx.com/archives/545 --%>
<script>
    Vue.filter('fillSpace', function (value) {
        var space = "";
        for (var i = 0;i < value; i++) {
            space += "&nbsp;&nbsp;&nbsp;&nbsp;";
        }
        return space;
    });

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
                        childMenu.isShow = level <= 3; // 是否显示，1,2,3级菜单默认显示
                        childMenu.isExpanded = level <= 2; // 是否展开菜单，1,2,3级菜单默认展开
                        tempList.push(childMenu);

                        initData(childMenu, level + 1, childMenu);
                    }
                }
            },
            toggole : function (menu) {
                debugger;

                /**
                 * 递归切换菜单的展开和收缩状态
                 */
                var isExpanded = menu.isExpanded;
                toggleChildMenuList(menu, !isExpanded);
                menu.isExpanded = !isExpanded;

                function toggleChildMenuList(menu, isShow) {
                    var list = menu.childList;
                    if(list == null) {
                        return;
                    }
                    for (var i = 0; i < list.length; i++) {
                        var childMenu = list[i];
                        childMenu.isShow = isShow;
                        toggleChildMenuList(childMenu, isShow);
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
            <tr v-for="obj of dataList" v-show="obj.isShow">
                <td>
                    {{{ obj.level | fillSpace }}}
                    <a @click="toggole(obj)">
                        {{ obj.isExpanded }}
                        <i v-show="obj.isExpanded" class="fa fa-caret-right"></i>
                        <i v-show="!obj.isExpanded" class="fa fa-caret-down"></i>
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