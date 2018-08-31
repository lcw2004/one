<template>
  <div class="fix-container">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: 'FixedContainer'
}
</script>

<style lang="less" type="text/less">
  .fix-container {
    position: fixed;
    top: 60px;
    right: 10px;
    width: 300px;
    z-index: 999;
  }
</style>
