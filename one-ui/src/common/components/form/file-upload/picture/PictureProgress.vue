<template>
  <div class="picture" @mouseover="showAction = true" @mouseout="showAction = false">
    <div class="picture-progress">
      <el-progress type="circle" :percentage="progress"></el-progress>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    progress: {
      type: Number
    }
  },
  data: function () {
    return {
      showAction: false
    }
  }
}
</script>
