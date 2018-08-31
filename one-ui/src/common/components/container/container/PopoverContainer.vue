<template>
  <span>
    <slot name="outer" @click="isShow = true"></slot>

    <el-popover
      placement="bottom"
      :width="width"
      :title="title"
      trigger="click"
      v-model="isShow"
      @show="onShow"
      @hide="onHide">
        <slot></slot>

        <i :class="iconClass" slot="reference"></i>
      </el-popover>
  </span>
</template>

<script>
import Popover from './Popover'

export default {
  name: 'PopoverContainer',
  components: {
    Popover
  },
  props: {
    title: {
      type: String
    },
    width: {
      type: Number,
      default: 500
    }
  },
  data: function () {
    return {
      isShow: false
    }
  },
  methods: {
    getIconWidth () {
      return 9
    },
    getWidth () {
    },
    onShow () {
      this.isShow = true
    },
    onHide () {
      this.isShow = false
    }
  },
  mounted () {
    this.$nextTick(() => {
      this.getWidth()
    })
  },
  computed: {
    iconClass: function () {
      return ['fa', 'fa-fw', this.isShow ? 'fa-angle-down' : 'fa-angle-right']
    },
    left: function () {
      let left = 9 - this.width / 2
      return left
    }
  },
  watch: {
    'isShow': {
      handler: function () {
        this.getWidth()
      },
      deep: true
    }
  }
}
</script>

<style lang="less" type="text/less">
  i {
    font-size: 15px;
  }

  i:hover {
    font-weight: bold;
  }
</style>
