<template>
<span style="position: relative">
  <span @click="isShow = !isShow">
    <slot name="outer"></slot>
    <i :class="iconClass" style="cursor:pointer">
      <Popover v-if="isShow" :title="title" :width="width" :left="left">
        <slot></slot>
      </Popover>
    </i>
  </span>
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
      getIconWidth () {
        return 9
      },
      getWidth () {
        console.log('-----------------')
        console.log(this.$el)
        console.log(this.$el.style.width)
        console.log('-----------------')
      }
    },
    mounted () {
      this.$nextTick(() => {
        this.getWidth()
      })
    },
    computed: {
      iconClass: function () {
        return ['fa', 'fa-fw', this.isShow ? 'fa-angle-down' : 'fa-angle-right']
      },
      left: function () {
        let left = 9 - this.width / 2
        return left
      }
    },
    watch: {
      'isShow': {
        handler: function () {
          this.getWidth()
        },
        deep: true
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
