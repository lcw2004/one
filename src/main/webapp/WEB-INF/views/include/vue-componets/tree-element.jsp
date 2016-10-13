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
            value: {
                type: Array,
                require: true
            }
        },
        data: function () {
            return {
                /**
                 * 展开的菜单的级数，默认展开三级
                 */
                isExpanded: this.level <= 2,
                selected : false
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
             * 选中元素
             */
            select: function () {
                this.value = this.element;
            }
        },
        computed: {
            /**
             * 是否显示文件夹图片
             * @returns {boolean}
             */
            isFolder: function () {
                return this.element.childList != null && this.element.childList.length > 0;
            }
        },
        watch: {
            value: function () {
                if(this.selected) {
                    this.value.push(this.element.id);

                    addChildTo(this.element.childList, this.value);
                } else {
                    remove(this.value, this.element.id);
                }
            }
        }
    });

    function addChildTo(childList, arr) {
        if(childList) {
            for(var i = 0; i < childList.length; i++) {
                var elmt = childList[i];
                arr.push(elmt.id);
                addChildTo(elmt.childList, arr);
            }
        }
    }
</script>
<template id="tree-element">
    <div>
        <i @click="toggole()" v-show="isFolder && isExpanded" class="fa fa-folder-open"></i>
        <i @click="toggole()" v-show="isFolder && !isExpanded" class="fa fa-folder"></i>

        <input type="checkbox" v-model="value" :value="element.id">

        <span @click="toggole()" v-text="element.name"></span>
        <a @click="select()">选中</a>
    </div>

    <ul v-for="child of element.childList" v-show="isExpanded">
        <li>
            <tree-element :element="child" :level="level + 1" :value.sync="value"></tree-element>
        </li>
    </ul>
</template>
<%--菜单树组件--%>