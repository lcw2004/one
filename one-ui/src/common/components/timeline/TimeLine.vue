<template>
  <ul class="timeline">
    <slot></slot>
  </ul>
</template>

<script>
export default {
  name: 'TimeLine',
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

<style>
/*覆盖AdminLte的字体*/
.timeline h3 {
  font-family: "Microsoft Yahei","Hiragino Sans GB","Helvetica Neue",Helvetica,tahoma,arial,Verdana,sans-serif;
  font-size: 14px;
}
</style>
