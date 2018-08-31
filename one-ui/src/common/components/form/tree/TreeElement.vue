<template>
  <div>
    <div style="min-height: 20px">
      <span class="tree-indent" v-for="index in level - 1"></span>
      <span class="tree-expander" v-if="isCanSelect">
        <input type="checkbox" v-if='selectType == "checkbox"' v-model="isChecked" :style="halfCheckedStyle" :disabled="element.isOptional == 0">
        <input type="radio" v-if='selectType == "radio"' v-model="selectValue" :value="element" :key="element.id" :disabled="element.isOptional == 0">
      </span>
      <span class="tree-expander" v-if="isFolder">
        <i @click="toggole()" :class="folderClass"></i>
      </span>
      <span class="cursor-pointer" @click="onClick()">{{ element.name }}</span>
    </div>

    <ul v-for="child of element.childList" v-show="isExpanded">
      <li>
        <TreeElement :element="child" :level="level + 1" :value="value" :select-type="selectType" :isCanSelect="isCanSelect" :tree-bus="treeBus"></TreeElement>
      </li>
    </ul>
  </div>
</template>

<script>
import { isInList } from '@utils/common'

export default {
  name: 'TreeElement',
  props: {
    /**
     * 元素
     */
    element: {
      type: Object,
      require: true
    },
    /**
     * 当前遍历的深度
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
    isCanSelect: {
      type: Boolean,
      default: true
    },
    treeBus: [Object],
    value: [Object, Array]
  },
  data: function () {
    return {
      /**
       * 展开的菜单的级数，默认展开三级
       */
      isExpanded: this.level <= 2
    }
  },
  methods: {
    /**
     * 切换展开 / 关闭状态
     */
    toggole () {
      this.isExpanded = !this.isExpanded
    },
    onClick () {
      // this.toggole()
      this.treeBus.$emit('element-click', this.element)
    }
  },
  computed: {
    /**
     * 是否显示文件夹图片
     * @returns {boolean}
     */
    isFolder: function () {
      return this.element.childList && this.element.childList.length > 0
    },
    /**
     * 文件夹按钮的样式
     */
    folderClass: function () {
      return {
        'fa fa-folder-open': this.isFolder && this.isExpanded,
        'fa fa-folder': this.isFolder && !this.isExpanded
      }
    },
    /**
     * CheckBox复选框的半选中状态
     */
    halfCheckedStyle: function () {
      // 检查元素是否有子元素没选中
      let hasChildNotSelected = false
      const checkIsAllChildSelected = function (element, elementIdList) {
        if (!isInList(element.id, elementIdList)) {
          hasChildNotSelected = true
          return
        }
        if (element.childList) {
          for (let child of element.childList) {
            checkIsAllChildSelected(child, elementIdList)
          }
        }
      }

      checkIsAllChildSelected(this.element, this.value)

      // 返回半选中状态
      if (hasChildNotSelected) {
        return {
          opacity: 0.4
        }
      }
    },
    /**
     * CheckBox复选框的选中状态
     */
    isChecked: {
      get: function () {
        // 检查当前元素的ID有没有再选中的值的列表中
        return this.value && isInList(this.element.id, this.value)
      },
      set: function (newValue) {
        // 如果选中了新值，将新选中的元素广播出去
        this.treeBus.$emit('select-value-ckbox', this.element, newValue)
      }
    },
    /**
     * Radio单选框选中后的值
     */
    selectValue: {
      get: function () {
        return this.value
      },
      set: function () {
        // 如果选中了新值，将新选中的元素广播到tree组件中
        this.treeBus.$emit('select-value-radio', this.element)
      }
    }
  }
}
</script>
