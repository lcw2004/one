<template>
  <span>
    <el-switch
      v-model="valueCopy"
      active-color="#00a65a"
      inactive-color="#d2d6de"
      :active-value="1"
      :inactive-value="0"
      @change="onChange(arguments[0])">
    </el-switch>
    <span class="switch-text">
      <template v-if="value == 0"><span class="label label-default">{{ inactiveText }}</span></template>
      <template v-if="value == 1"><span class="label label-success">{{ activeText }}</span></template>
    </span>
  </span>
</template>

<script>
export default {
  name: 'OneSwitch',
  props: {
    activeText: {
      type: String,
      default: '是'
    },
    inactiveText: {
      type: String,
      default: '否'
    },
    value: {}
  },
  computed: {
    valueCopy: {
      get: function () {
        return this.value
      },
      set: function (newValue) {
        this.$emit('input', newValue)
      }
    }
  },
  methods: {
    onChange (arg) {
      this.$emit('change', arg)
    }
  }
}
</script>

<style scoped lang="less">
.switch-text {
  margin-left: 10px;
  vertical-align: middle;
}
</style>
