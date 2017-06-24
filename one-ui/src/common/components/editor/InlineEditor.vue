<template>
  <Editor v-model="data" type="inline"></Editor>
</template>

<script>
  import Editor from './Editor'

  export default {
    name: 'InlineEditor',
    props: {
      value: {}
    },
    components: {
      Editor
    },
    computed: {
      data: {
        get: function () {
          return this.value
        },
        set: function (newValue) {
          this.$emit('input', newValue)
        }
      }
    }
  }
</script>
