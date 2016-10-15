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
        data: {
            isInited: false
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
            },

            /**
             * 第一次传入value值的时候，需要根据value值初始化复选框的状态
             */
            setValue: function () {
                // 如果值未传入或者已经初始化过状态，则不再初始化
                if(this.value == null || this.value.length == 0 || this.isInited) {
                    return;
                }

                // 遍历检查菜单ID，是否存在于value中，如果在，则改为选中状态
                var menuIdList = this.value;
                var setPropOfElement = function (element) {
                    var isSelected = $.inArray(element.id, menuIdList) > 0;
                    Vue.set(element, "isSelected", isSelected);

                    var childList = element.childList;
                    if (childList) {
                        for (var i = 0; i < childList.length; i++) {
                            setPropOfElement(childList[i]);
                        }
                    }
                };

                setPropOfElement(this.element);
                this.isInited = true;
            }
        },
        watch: {
            /**
             * 修改选中状态的时候自动修改Value的值，即：复选框状态 -> value
             */
            "element": {
                handler: function () {
                    this.getValue();
                },
                deep :true
            },
            /**
             * 根据传入的Value值，初始化复选框状态，即：value-> 选框状态
             */
            "value": {
                handler: function () {
                    this.setValue();
                }
            }
        }
    });
</script>
<template id="tree">
    <tree-element :element="element" :level="1" :value.sync="value"></tree-element>
</template>
<%--菜单树组件--%>