<template>
  <TreeElement :element="element" :level="1" :value="value" :select-type="selectType" :tree-bus="treeBus" :isCanSelect="isCanSelect"></TreeElement>
</template>

<script>
// import * as treeUtils from './tree-utils'
import Vue from 'vue'
import TreeElement from './TreeElement'

export default {
  name: 'Tree',
  components: {
    TreeElement
  },
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
    /**
     * 是否级联选中父节点
     */
    isSelectParent: {
      type: Boolean,
      default: true
    },
    isCanSelect: {
      type: Boolean,
      default: true
    },
    value: [Object, Array]
  },
  computed: {
    elementMap: function () {
      let map = {}
      const asListRecursion = function (element, map) {
        map[element.id] = element
        const childList = element.childList
        if (childList) {
          for (let child of childList) {
            asListRecursion(child, map)
          }
        }
      }
      asListRecursion(this.element, map)
      return map
    }
  },
  data: function () {
    return {
      // 消息总线，用于父子组件间通讯
      // 不能注册为公共的消息组件，否则如果一个页面引用了多个树，会乱发事件
      treeBus: new Vue()
    }
  },
  mounted () {
    this.initEvent()
    this.initEventOfRadio()
    this.initEventOfCheckbox()
  },
  methods: {
    initEvent () {
      this.treeBus.$on('element-click', (data) => {
        this.$emit('element-click', data)
      })
    },
    initEventOfRadio () {
      if (this.selectType === 'radio') {
        // 单选的情况下，直接将数据发给Modal
        this.treeBus.$on('select-value-radio', (data) => {
          this.$emit('input', data)
        })
      }
    },
    initEventOfCheckbox () {
      if (this.selectType === 'checkbox') {
        this.treeBus.$on('select-value-ckbox', (selectElement, isSelect) => {
          /**
          * 遍历添加/删除子节点
          **/
          const addOrRemoveRecursion = function (element, value, isSelect) {
            var index = value.indexOf(element.id)
            if (isSelect && index < 0 && parent.isOptional !== 0) {
              value.push(element.id)
            } else if (!isSelect && index >= 0) {
              value.splice(index, 1)
            }
            if (element.childList) {
              for (let child of element.childList) {
                addOrRemoveRecursion(child, value, isSelect)
              }
            }
          }

          /**
          * 遍历添加父节点
          **/
          let self = this
          const addParentRecursion = function (element, value) {
            let parent = self.elementMap[element.parentId]
            if (parent) {
              if (value.indexOf(parent.id) < 0 && parent.isOptional !== 0) {
                value.push(parent.id)
              }
              addParentRecursion(parent, value)
            }
          }
          let newValue = this.value
          if (isSelect) {
            if (this.isSelectParent) {
              addParentRecursion(selectElement, newValue)
            }
          }
          addOrRemoveRecursion(selectElement, newValue, isSelect)
          this.$emit('input', newValue)
        })
      }
    }
  }
}
</script>

<style lang="less" type="text/less">
  ul,
  li {
    margin-top: 0;
    margin-bottom: 0;
  }
</style>
