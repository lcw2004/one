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
  <div class="form-group" :class="{ 'has-error': isError }">
    <label class="control-label" :class="labelClass">{{ label }}</label>
    <div :class="formWidth">
      <slot></slot>
      <p class="help-block" style="margin-bottom: -5px;" v-if="isError">{{ errorMessage }}</p>
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
        default: 4
      },
      inputWidth: {},
      required: {}
    },
    data: () => {
      return {}
    },
    computed: {
      parent: function () {
        // 更新到VUe2.5.2之后，获取到的Parent是Transition组件，需要再向上获取一级，才能获取到验证结果。
        let parent = this.$parent
        if (parent.$options._componentTag === 'OneTransition') {
          parent = parent.$parent
        }
        return parent
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
        return this.parentErrors.has(this.label)
      },
      isPass: function () {
        const field = this.parentFields[this.label]
        if (!field) {
          return false
        }
        return field.touched && field.valid
      },
      isRequired: function () {
        return this.required === 'true'
      },
      errorMessage: function () {
        return this.parentErrors.first(this.label)
      },
      labelClass: function () {
        return ['col-md-' + this.width, this.isRequired ? 'required' : '']
      },
      formWidth: function () {
        let formClass = ''
        if (this.inputWidth) {
          formClass = 'col-md-' + this.inputWidth
        } else {
          formClass = 'col-md-' + (12 - this.width)
        }
        return formClass
      },
      groupClass: function () {
        return {'has-error': this.isError}
      }
    }
  }
</script>
