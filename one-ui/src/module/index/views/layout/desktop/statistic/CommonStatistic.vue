<template>
  <div class="col-lg-3 col-xs-6">
    <div class="small-box" :class="bgClass">
      <div class="inner">
        <h3>{{ value.result.totalCount }}</h3>
        <p>{{ value.componentTitle }}</p>
      </div>
      <div class="icon">
        <i class="ion" :class="iconClass"></i>
      </div>
      <template v-if="value.componentLink">
        <router-link :to='value.componentLink' class="small-box-footer">查看 <i class="fa fa-arrow-circle-right"></i></router-link>
      </template>
      <template v-else>
        <a class="small-box-footer"> <i class="fa fa-arrow-circle-right"></i></a>
      </template>
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
  }
}
</script>

<style>
.ion {
  font-size: 90px;
}
</style>
