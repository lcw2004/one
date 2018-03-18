<template>
  <div class="col-md-12 text-center" v-if="!data || data.length == 0" style="margin: 20px 0">
    <slot></slot>
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
