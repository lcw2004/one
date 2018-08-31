<template>
  <div class="col-md-3 col-sm-6 col-xs-12">
    <div class="info-box" @click="goToPage()">
      <span class="info-box-icon" :class="bgClass"><i class="ion" :class="iconClass"></i></span>
      <div class="info-box-content">
        <span class="info-box-text">{{ value.componentTitle }}</span>
        <span class="info-box-number">{{ value.result.totalCount }}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    value: {
      type: Object,
      required: true
    }
  },
  computed: {
    config: function () {
      if (this.value.config) {
        return JSON.parse(this.value.config)
      } else {
        return {}
      }
    },
    bgClass: function () {
      return this.config.bg ? this.config.bg : 'bg-yellow'
    },
    iconClass: function () {
      return this.config.icon ? this.config.icon : ''
    }
  },
  methods: {
    goToPage () {
      if (this.value.componentLink) {
        this.$router.push(this.value.componentLink)
      }
    }
  }
}
</script>

<style lang="less" type="text/less">
.ion {
  font-size: 45px;
}
.info-box {
  &:hover {
    box-shadow: 0 1px 10px rgba(0, 0, 0, .3);
  }
}
</style>
