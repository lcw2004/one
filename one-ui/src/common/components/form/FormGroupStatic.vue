<template>
  <FormGroup :label="label" :width="width">
    <FormControllStatic><slot></slot></FormControllStatic>
  </FormGroup>
</template>

<script>
export default {
  name: 'FormGroupStatic',
  props: {
    label: {
      type: String
    },
    width: {
      type: Number,
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
