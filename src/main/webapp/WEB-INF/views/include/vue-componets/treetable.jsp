<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<%-- Vue Treetable 组件--%>
<%-- 参考：http://blog.fengxiaotx.com/archives/545 --%>
<script>
    Vue.filter('space', function (value) {
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

                function initData(menu, level, parent) {
                    var childList = menu.childList;
                    if(!childList) {
                        return;
                    }
                    for(var i = 0; i < childList.length; i++) {
                        var childMenu = childList[i];

                        childMenu.level = level;
                        tempList.push(childMenu);

                        initData(childMenu, level + 1, childMenu);
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
    <button>测试</button>
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
            <tr v-for="obj of dataList" v-show="obj.level <= 3">
                <td>
                    {{{ obj.level | space }}}
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