<template>
  <div class="col-md-12 text-center" v-if="!data || data.length == 0">
    暂无数据
  </div>
</template>

<script>
export default {
  name: 'TableNoData',
  props: {
    data: {
      type: Array,
      require: true
    }
  }
}
</script>
