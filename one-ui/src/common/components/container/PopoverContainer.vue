<template>
<span style="position: relative">
  <div @click="isShow = !isShow" style="cursor:pointer">
    <slot name="outer"></slot>
    <i :class="iconClass"></i>
  </div>
  <Popover v-if="isShow" :title="title" :width="width">
    <slot></slot>
  </Popover>
</span>
</template>

<script>
  import Popover from './Popover'
  export default {
    name: 'PopoverContainer',
    components: {
      Popover
    },
    props: {
      title: {
        type: String
      },
      width: {
        type: Number,
        default: 500
      }
    },
    data: () => {
      return {
        isShow: false
      }
    },
    methods: {
      getIconWidth: function () {
        return 9
      }
    },
    computed: {
      iconClass: function () {
        return ['fa', 'fa-fw', this.isShow ? 'fa-angle-down' : 'fa-angle-right']
      }
    }
  }
</script>

<style>
  i {
    font-size: 15px;
  }

  i:hover {
    font-weight: bold;
  }
</style>
