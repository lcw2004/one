<!--

props：
  label:      label显示值
  width:      label宽度，默认为4
  inputWidth: 输入框宽度，默认为12-width
  required:   是否必填，true/false，如果为true，会在label前面添加一个*

示例：
<FormGroup label="">
  ...
</FormGroup>
-->

<template>
  <div class="form-group" :class="{ 'has-error': isError }" :style="groupStyle">
    <label class="control-label" :class="labelClass" v-if="width > 0">{{ label }}</label>
    <div :class="formWidth">
      <slot></slot>
      <!-- <p class="help-block" style="margin-bottom: -5px;" v-if="isError">{{ errorMessage }}</p> -->
      <p class="has-error" v-if="isError" :style="errorStyle">{{ errorMessage }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FormGroup',
  props: {
    label: {
      type: String
    },
    width: {
      type: [Number, String],
      default: 4
    },
    inputWidth: {
      type: [Number, String]
    },
    required: {
      type: [Boolean, String],
      default: false
    },
    name: {
      type: String,
      required: false
    },
    isContainer: {
      type: Boolean,
      default: false
    },
    noBottom: {
      type: Boolean,
      default: false
    }
  },
  mounted () {
    this.$nextTick(() => {
      let height = this.$el.offsetHeight
      this.errorTop = height > 0 ? height : 32
    })
  },
  data: function () {
    return {
      errorTop: 0
    }
  },
  computed: {
    validateName: function () {
      if (this.name) {
        return this.name
      } else {
        return this.label
      }
    },
    parent: function () {
      // 更新到VUe2.5.2之后，获取到的Parent是容器组件，需要再向上获取一级，才能获取到验证结果。遍历获取父节点，如果是容器元素则忽略
      let getParent = function (target) {
        let parent = target.$parent
        if (parent.isContainer) {
          return getParent(parent)
        } else {
          return parent
        }
      }
      return getParent(this)
    },
    parentErrors: function () {
      return this.parent.errors
    },
    parentFields: function () {
      return this.parent.fields
    },
    isError: function () {
      if (!this.parentErrors) {
        return false
      }
      return this.parentErrors.has(this.validateName)
    },
    isPass: function () {
      const field = this.parentFields[this.validateName]
      if (!field) {
        return false
      }
      return field.touched && field.valid
    },
    isRequired: function () {
      return this.required === 'true' || this.required === true
    },
    errorMessage: function () {
      return this.parentErrors.first(this.validateName)
    },
    labelClass: function () {
      return ['col-md-' + this.width, this.isRequired ? 'required' : '']
    },
    formWidth: function () {
      let formClass = ''
      if (this.inputWidth) {
        formClass = 'col-md-' + this.inputWidth
      } else {
        formClass = 'col-md-' + (12 - Number(this.width))
      }
      return formClass
    },
    groupClass: function () {
      return {'has-error': this.isError}
    },
    groupStyle: function () {
      let style = {}
      if (this.noBottom) {
        style['margin-bottom'] = '0'
      }
      return style
    },
    errorStyle: function () {
      return {
        top: (this.errorTop - 5) + 'px'
      }
    }
  }
}
</script>

<style lang="less" type="text/less">
p.has-error {
  left: 20px;
  top: auto;
  padding: 0 5px 1px;
  font-size: 11px;
  color: #fff !important;
  max-width: none;
  position: absolute;
  background: #ff6969;
  z-index: 1000;
  border-radius: 3px;
  -webkit-box-shadow: 0 5px 10px #dedede;
  box-shadow: 0 5px 10px #dedede
}

p.has-error:after {
  border-color: rgba(0, 0, 0, 0) rgba(0, 0, 0, 0) #ff6969;
  border-style: solid;
  border-width: 6px;
  content: "";
  height: 0;
  left: 15px;
  position: absolute;
  top: -11px
}
</style>
