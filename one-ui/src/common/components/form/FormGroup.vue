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
      <p class="help-block" v-if="isError" style="margin-bottom: -5px;">{{ errors.first(label) }}</p>
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
      errors: function () {
        return this.$parent.errors
      },
      isError: function () {
        if (!this.errors) {
          return false
        }
        return this.errors.has(this.label)
      },
      isPass: function () {
        const field = this.fields[this.label]
        return field.touched && field.valid
      },
      groupClass: function () {
        return {'has-error': this.isError}
      },
      isRequired: function () {
        return this.required === 'true'
      }
    }
  }
</script>
