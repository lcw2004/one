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
             * 选择的类型：
             * radio:单选框，value的值为选中的元素
             * checkbox: 复选框，value的值为选中的元素的ID数组
             */
            selectType: {
                type: String
            },
            treeBus: [Object],
            value: [Object, Array]
        },
        data: function () {
            return {
                /**
                 * 展开的菜单的级数，默认展开三级
                 */
                isExpanded:  this.level <= 2,
                /**
                 * 树形结构中，选中的元素的ID，用于控制单项按钮的选中状态，该值有两个来源：1 - 选中单选按钮状态，2 - 接收广播
                 */
                selectElementId : ""
            }
        },
        mounted: function () {
            // 接收广播
            var self = this;
            self.treeBus.$on("select-value", function (selectElement) {
                self.selectElementId = selectElement.id;
            });
        },
        methods: {
            /**
             * 切换展开 / 关闭状态
             */
            toggole: function () {
                this.isExpanded = !this.isExpanded
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
            },
            /**
             * 复选框的选中状态
             */
            isChecked: {
                get: function () {
                    // 检查当前元素的ID有没有再选中的值的列表中
                    return $.inArray(this.element.id, this.value) >= 0;
                },
                set: function (newValue) {
                    // 如果选中了新值，将新选中的元素广播出去
                    this.treeBus.$emit("select-value-ckbox", this.element, newValue);
                }
            },
            halfChecked: function () {
                var self = this;

                // 检查元素是否有子元素没选中
                var hasNotSelectedChild = false;
                var checkStatusRecursion = function (element) {
                    var index = $.inArray(element.id, self.value);
                    if (index < 0) {
                        hasNotSelectedChild = true;
                    }

                    var childList = element.childList;
                    if (childList) {
                        for (var i = 0; i < childList.length; i++) {
                            checkStatusRecursion(childList[i]);
                        }
                    }
                };
                checkStatusRecursion(self.element);

                // 返回半选中状态
                if (hasNotSelectedChild) {
                    return {
                        opacity: 0.4
                    }
                }
            }
        },
        watch: {
            "selectElementId": {
                handler: function () {
                    // 如果选中了新值，将新选中的元素广播出去
                    if(this.selectElementId == this.element.id) {
                        this.treeBus.$emit("select-value-radio", this.element);
                    }
                }
            }
        }
    });
</script>
<template id="tree-element">
    <div>
        <input type="checkbox" v-if='selectType == "checkbox"' v-model="isChecked" :style="halfChecked">
        <input type="radio" v-if='selectType == "radio"' v-model="selectElementId" :value="element.id">
        <i @click="toggole()" v-show="isFolder" :class="folderClass"></i>
        <span @click="toggole()" v-text="element.name"></span>

        <ul v-for="child of element.childList" v-show="isExpanded">
            <li>
                <tree-element :element="child" :level="level + 1" :value="value" :select-type="selectType" :tree-bus="treeBus"></tree-element>
            </li>
        </ul>
    </div>
</template>
<%--菜单树组件--%>