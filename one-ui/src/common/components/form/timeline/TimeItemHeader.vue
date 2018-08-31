<template>
  <span>
    <slot></slot>
  </span>
</template>

<script>
export default {
  name: 'TimeItemHeader'
}
</script>

<style lang="less" type="text/less">
  .timeline-header > a {
    font-size: 15px;
    font-weight: normal;
  }
</style>
