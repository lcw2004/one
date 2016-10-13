<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<%--菜单树组件--%>
<script>
    Vue.component("tree", {
        template: "#tree",
        props: {
            /**
             * 元素
             */
            element: {
                type: Object,
                require: true
            },
            /**
             * 选中的项
             */
            value: {
                type: Array,
                require: true
            }
        },
        data: function () {
            return {

            }
        },
        methods: {

        },
        computed: {

        }
    });

    function equels(obj1, obj2) {
        if(obj1 != null && obj2 != null) {
            return obj1.id == obj2.id;
        } else {
            return false;
        }
    }

    function remove(arr, item) {
        for(var i = arr.length; i--;) {
            if(arr[i] === item) {
                arr.splice(i, 1);
            }
        }
    }
</script>
<template id="tree">
    <tree-element :element="element" :level="1" :value.sync="value"></tree-element>
</template>
<%--菜单树组件--%>