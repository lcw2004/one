<template>
  <span>
    <template v-if="!isHideMore">
      {{ text }}
    </template>
    <template v-if="isHideMore">
      <span slot="outer" @click="isShow = true">{{ text | limitLength(maxLength) }}</span>

      <el-popover
        placement="bottom"
        :width="400"
        :title="title"
        trigger="click"
        v-model="isShow"
        @show="onShow"
        @hide="onHide">
        <div class="row">
          <div class="col-md-12">
            {{ text }}
          </div>
        </div>
        <i :class="iconClass" slot="reference"></i>
      </el-popover>
    </template>
  </span>
</template>

<script>
export default {
  name: 'LongText',
  props: {
    length: {
      default: '15'
    },
    title: {
      type: String,
      default: '内容'
    },
    text: {
      type: String,
      required: false
    }
  },
  data: function () {
    return {
      isShow: false
    }
  },
  computed: {
    iconClass: function () {
      return ['fa', 'fa-fw', this.isShow ? 'fa-angle-down' : 'fa-angle-right']
    },
    maxLength: function () {
      return Number.parseInt(this.length)
    },
    isHideMore: function () {
      if (!this.text) {
        return false
      }
      return this.text.length > this.maxLength
    }
  },
  methods: {
    onShow () {
      this.isShow = true
    },
    onHide () {
      this.isShow = false
    }
  }
}
</script>
