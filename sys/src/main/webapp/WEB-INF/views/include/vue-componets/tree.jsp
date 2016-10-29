<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<%--菜单树组件--%>
<script>
    var treeBus = new Vue()

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
             * 选择的类型：
             * radio:单选框，value的值为选中的元素
             * checkbox: 复选框，value的值为选中的元素的ID数组
             */
            selectType: {
                type: String
            }
        },
        data: function () {
            return {
                value: {}
            }
        },
        mounted: function () {
            // 接收到数据之后，将数据发送给Modal层
            var self = this;
            treeBus.$on("select-value", function (data) {
                self.$emit("input", data);
            });
        },
        methods: {
            /**
             * 注册init事件，供父组件调用，根据value值修改复选框的状态
             */
            registerInitEvent: function () {
                var self = this;
                if(self.selectType == 'checkbox') {
                    this.$on("init", function () {
                        self.initSelectValueByMenuIdList();
                    })
                }
            },

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
             * 根据value值初始化复选框的状态
             */
            initSelectValueByMenuIdList: function () {
                var menuIdList = this.value;

                // 遍历检查菜单ID，是否存在于value中，如果在，则改为选中状态
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
            },

            /**
             * 判断element中是否包含isSelected属性，如果不包含，则初始化，默认值为false
             */
            initDefaultSelectValue: function () {
                var setPropOfElement = function (element, isSelected) {
                    Vue.set(element, "isSelected", isSelected);

                    var childList = element.childList;
                    if (childList) {
                        for (var i = 0; i < childList.length; i++) {
                            setPropOfElement(childList[i], isSelected);
                        }
                    }
                };

                var isSelected = this.$get("element.isSelected");
                if (isSelected == undefined) {
                    setPropOfElement(this.element, false);
                }
            }
        },
        watch: {
            /**
             * 修改选中状态的时候自动修改Value的值，即：复选框状态 -> value
             */
            "element": {
                handler: function () {
                    if(this.selectType == 'checkbox') {
                        this.initDefaultSelectValue();
                        this.getValue();
                    }
                },
                deep :true
            }
        }
    });
</script>
<template id="tree">
    <tree-element :element="element" :level="1" :select-type="selectType"></tree-element>
</template>
<%--菜单树组件--%>