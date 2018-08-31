<template>
  <el-popover
    :title="title"
    :placement="placement"
    :width="width"
    trigger="hover">
    <slot></slot>
    <i :class="icon" :style="iconStyle" slot="reference"></i>
  </el-popover>
</template>

<script>
const PRIMARY_COLOR = '#3c8dbc'
const INFO_COLOR = '#00c0ef'
const SUCCESS_COLOR = '#00a65a'
const WARNING_COLOR = '#f39c12'
const DANGER_COLOR = '#f56954'
const GRAY_COLOR = '#d2d6de'

export default {
  name: 'OneTooltip',
  props: {
    title: {
      type: String
    },
    type: {
      type: String,
      default: 'primary'
    },
    icon: {
      type: String,
      default: 'fa fa-fw fa-question-circle'
    },
    placement: {
      type: String,
      default: 'right'
    },
    width: {
      type: Number
    }
  },
  data: function () {
    return {
      isShow: false,
      iconColor: PRIMARY_COLOR,
      iconOpcity: 1
    }
  },
  computed: {
    color: function () {
      let color = PRIMARY_COLOR
      if (this.type === 'primary') {
        color = PRIMARY_COLOR
      } else if (this.type === 'info') {
        color = INFO_COLOR
      } else if (this.type === 'success') {
        color = SUCCESS_COLOR
      } else if (this.type === 'warning') {
        color = WARNING_COLOR
      } else if (this.type === 'danger') {
        color = DANGER_COLOR
      } else if (this.type === 'gray') {
        color = GRAY_COLOR
      }
      return color
    },
    iconStyle: function () {
      return {
        'color': this.color,
        'opacity': this.iconOpcity,
        'font-size': '15px'
      }
    }
  },
  created () {
    this.iconColor = this.color
  },
  methods: {
    show () {
      this.isShow = true
      this.iconOpcity = 0.8
    },
    hide () {
      this.isShow = false
      this.iconOpcity = 1
    }
  }
}
</script>
