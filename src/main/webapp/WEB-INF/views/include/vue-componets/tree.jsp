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
        ready: function () {
            console.log(this.element);
        },
        methods: {
            /**
             * 遍历所有的节点，获取选中节点的值
             */
            getValue: function () {
                var self = this;
                self.value = [];
                var getValueRecursion = function (element) {
                    if(element.isSelected) {
                        self.value.push(element.id);
                    }

                    var childList = element.childList;
                    if (childList) {
                        for (var i = 0; i < childList.length; i++) {
                            getValueRecursion(childList[i]);
                        }
                    }
                };
                getValueRecursion(self.element);
            }
        },
        watch: {
            /**
             * 修改选中状态的时候自动修改Value的值
             */
            "element": {
                handler: function () {
                     this.getValue();
                },
                deep :true
            }
        }
    });


</script>
<template id="tree">
    <tree-element :element="element" :level="1" :value.sync="value"></tree-element>
</template>
<%--菜单树组件--%>