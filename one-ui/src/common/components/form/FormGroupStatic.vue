<template>
  <FormGroup :label="label" :width="width">
    <FormControllStatic>
      <slot></slot>
    </FormControllStatic>
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
        type: String,
        default: '4'
      }
    },
    data: () => {
      return {}
    },
    computed: {
      labelWidth: function () {
        return 'col-md-' + Number.parseInt(this.width)
      },
      formWidth: function () {
        return 'col-md-' + (12 - Number.parseInt(this.width))
      }
    }
  }
</script>
