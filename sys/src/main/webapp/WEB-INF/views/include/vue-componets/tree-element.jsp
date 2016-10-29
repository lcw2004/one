<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<%--菜单树组件--%>
<script>
    Vue.component("tree-element", {
        template: "#tree-element",
        props: {
            /**
             * 元素
             */
            element: {
                type: Object,
                require: true
            },
            /**
             * 当前遍历的级别
             */
            level: {
                type: Number,
                require: true
            },
            /**
             * 选中的项
             */
            value: {},
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
                /**
                 * 展开的菜单的级数，默认展开三级
                 */
                isExpanded:  this.level <= 2,
                selected : true
            }
        },
        methods: {
            /**
             * 切换展开 / 关闭状态
             */
            toggole: function () {
                this.isExpanded = !this.isExpanded
            },
            /**
             * 处理复选框选择事件，修改状态之后递归修改复选框的状态
             */
            handlerSelectChange: function () {
                var setSelectRecursion = function (element, isSelected) {
                    var childList = element.childList;

                    if (childList) {
                        for (var i = 0; i < childList.length; i++) {
                            var child = childList[i];
                            child.isSelected = isSelected;
                            setSelectRecursion(child, isSelected);
                        }
                    }
                };

                setSelectRecursion(this.element, this.element.isSelected);
            }
        },
        computed: {
            /**
             * 是否显示文件夹图片
             * @returns {boolean}
             */
            isFolder: function () {
                return this.element.childList != null && this.element.childList.length > 0;
            },
            /**
             * 文件夹按钮的样式
             */
            folderClass: function () {
                return {
                    'fa fa-folder-open':this.isFolder && this.isExpanded,
                    'fa fa-folder':this.isFolder && !this.isExpanded
                };
            }
        }
    });
</script>
<template id="tree-element">
    <div>
        <input type="checkbox" v-if='selectType == "checkbox"'v-model="element.isSelected" @change="handlerSelectChange()">
        <input type="radio" v-if='selectType == "radio"' v-model="value" :value="element">
        <i @click="toggole()" v-show="isFolder" :class="folderClass"></i>
        <span @click="toggole()" v-text="element.name"></span>

        <ul v-for="child of element.childList" v-show="isExpanded">
            <li>
                <tree-element :element="child" :level="level + 1" :value="value" :select-type="selectType"></tree-element>
            </li>
        </ul>
    </div>
</template>
<%--菜单树组件--%>