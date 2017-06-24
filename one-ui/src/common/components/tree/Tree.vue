<template>
  <TreeElement :element="element" :level="1" :value="value" :select-type="selectType" :tree-bus="treeBus"></TreeElement>
</template>

<script>
  let $ = require('jquery')
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
      value: [Object, Array]
    },
    data: () => {
      return {
        // 消息总线，用于父子组件间通讯
        // 不能注册为公共的组件，否则如果一个页面引用了多个树，会乱发事件
        treeBus: new Vue()
      }
    },
    mounted: function () {
      this.initEventOnOfRadio()
      this.initEventOnOfCheckbox()
    },
    methods: {
      initEventOnOfRadio: function () {
        let self = this
        if (self.selectType === 'radio') {
          // 单选的情况下，直接将数据发给Modal
          self.treeBus.$on('select-value-radio', function (data) {
            self.$emit('input', data)
          })
        }
      },
      initEventOnOfCheckbox: function () {
        const self = this
        if (self.selectType === 'checkbox') {
          self.treeBus.$on('select-value-ckbox', function (selectElement, isSelect) {
            const addOrRemoveRecursion = function (element, isSelect) {
              var index = $.inArray(element.id, self.value)
              if (isSelect && index < 0) {
                self.value.push(element.id)
              } else if (!isSelect && index >= 0) {
                self.value.splice(index, 1)
              }

              const childList = element.childList
              if (childList) {
                for (let i = 0; i < childList.length; i++) {
                  addOrRemoveRecursion(childList[i], isSelect)
                }
              }
            }
            addOrRemoveRecursion(selectElement, isSelect)
          })
        }
      }
    }
  }
</script>

<style>
  ul,
  li {
    margin-top: 0;
    margin-bottom: 0;
  }
</style>
