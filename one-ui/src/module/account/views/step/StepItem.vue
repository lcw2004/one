<template>
  <li :class="{active : isActive}" :style="liStyle">
    <a>
      <span class="number">{{ index }}</span>
      <span class="desc">
      <label>{{ name }}</label>
    </span>
    </a>
  </li>
</template>

<script>
  export default {
    props: {
      isActive: {
        type: Boolean,
        default: false
      },
      index: {},
      name: {},
      width: {}
    },
    computed: {
      liStyle: function () {
        return {
          width: this.width + '%'
        }
      }
    }
  }
</script>
