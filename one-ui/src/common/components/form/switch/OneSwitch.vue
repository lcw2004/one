<template>
  <span>
    <el-switch
      v-model="valueCopy"
      active-color="#00a65a"
      inactive-color="#d2d6de"
      :active-value="activeValue"
      :inactive-value="inactiveValue"
      @change="onChange(arguments[0])">
    </el-switch>
    <span class="switch-text" v-if="showText">
      <template v-if="value == inactiveValue"><span class="label label-default">{{ inactiveText }}</span></template>
      <template v-if="value == activeValue"><span class="label label-success">{{ activeText }}</span></template>
    </span>
  </span>
</template>

<script>
export default {
  name: 'OneSwitch',
  props: {
    showText: {
      type: Boolean,
      default: true
    },
    activeText: {
      type: String,
      default: '是'
    },
    inactiveText: {
      type: String,
      default: '否'
    },
    activeValue: {
      type: Number,
      default: 1
    },
    inactiveValue: {
      type: Number,
      default: 0
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

<style lang="less" type="text/less" scoped>
.switch-text {
  margin-left: 10px;
  vertical-align: middle;
}
</style>
