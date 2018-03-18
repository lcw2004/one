<template>
  <div>
    <KindEditor
      v-model="innerValue"
      :id="id"
      :height="height"
      width="100%"
      pluginsPath="/static/kindeditor/plugins/"
      :loadStyleMode="false">
    </KindEditor>
  </div>
</template>

<script>
import { uuid } from '@utils/common.js'

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
  data: function () {
    return {
      id: uuid(),
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
