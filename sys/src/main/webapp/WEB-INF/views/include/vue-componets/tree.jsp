<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<%--菜单树组件--%>
<script>
    var treeBus = new Vue();

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
            },
            value: [Object, Array]
        },
        mounted: function () {
            // 接收到数据之后，将数据发送给Modal层
            var self = this;
            if(self.selectType == 'radio') {
                treeBus.$on("select-value", function (data) {
                    self.$emit("input", data);
                });
            }
            if(self.selectType == 'checkbox') {
                treeBus.$on("select-value-ckbox", function (selectElement, isSelect) {
                    var addOrRemoveRecursion = function (element, isSelect) {
                        var index = $.inArray(element.id, self.value);
                        if(isSelect && index < 0) {
                            self.value.push(element.id);
                        } else if(!isSelect && index >= 0){
                            self.value.splice(index, 1);
                        }

                        var childList = element.childList;
                        if (childList) {
                            for (var i = 0; i < childList.length; i++) {
                                addOrRemoveRecursion(childList[i], isSelect);
                            }
                        }
                    };
                    addOrRemoveRecursion(element, isSelect);
                });
            }
        }
    });
</script>
<template id="tree">
    <div>
        <tree-element :element="element" :level="1" :value="value" :select-type="selectType"></tree-element>
    </div>
</template>
<%--菜单树组件--%>