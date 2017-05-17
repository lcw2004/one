<template>
  <div class="form-group">
    <label class="control-label" :class="labelWidth">{{ label }}</label>
    <div :class="formWidth">
      <slot></slot>
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
    }
  },
  data: function () {
    return {
    }
  },
  computed: {
    labelWidth: function () {
      return 'col-md-' + this.width
    },
    formWidth: function () {
      return 'col-md-' + (12 - this.width)
    }
  }
}
</script>
