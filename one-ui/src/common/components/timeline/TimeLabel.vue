<template>
<li class="time-label">
  <span :class="background">{{ time }}</span>
  <template v-if="remark">{{ remark }}</template>
</li>
</template>

<script>
export default {
  name: 'TimeLabel',
  props: {
    time: {},
    remark: {},
    background: {
      required: false,
      default: 'bg-red'
    }
  },
  components: {
  },
  data: function () {
    return {
    }
  },
  methods: {
  }
}
</script>
