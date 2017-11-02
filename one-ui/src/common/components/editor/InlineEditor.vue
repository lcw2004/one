<template>
  <div>
    <KindEditor
      v-model="innerValue"
      id="editor_id"
      :height="height"
      width="100%"
      pluginsPath="/static/kindeditor/plugins/"
      :loadStyleMode="false">
    </KindEditor>
  </div>
</template>

<script>
  export default {
    name: 'InlineEditor',
    props: {
      value: {},
      height: {
        default: '500px'
      }
    },
    mounted () {
      this.innerValue1 = this.value
    },
    data: () => {
      return {
        innerValue1: ''
      }
    },
    watch: {
      'innerValue1': function (value) {
        this.$emit('input', value)
      }
    },
    computed: {
      innerValue: {
        get: function () {
          return this.value
        },
        set: function (value) {
          this.$emit('input', value)
        }
      }
    }
  }
</script>
