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
            getSelectedValue: function () {
                var self = this;
                self.value = [];
                var getValueOfElement = function (element) {
                    if(element.isSelected) {
                        self.value.push(element.id);
                    }

                    var childList = element.childList;
                    if (childList) {
                        for (var i = 0; i < childList.length; i++) {
                            var child = childList[i];
                            getValueOfElement(child);
                        }
                    }
                };
                getValueOfElement(self.element);
            }
        },
        watch: {
            "element": {
                handler: function () {
                    this.getSelectedValue();
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