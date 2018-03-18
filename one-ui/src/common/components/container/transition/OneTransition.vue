<template>
  <transition :name="name" :mode="mode" @before-enter="beforeEnter" @after-enter="afterEnter">
    <slot></slot>
  </transition>
</template>

<script>
export default {
  name: 'OneTransition',
  props: {
    name: {
      default: 'zoom'
    },
    mode: {
      default: 'out-in'
    }
  },
  data: function () {
    return {
      isContainer: true
    }
  },
  methods: {
    beforeEnter: function (el) {
      console.log('beforeEnter')
    },
    afterEnter: function (el) {
      console.log('afterEnter')
    }
  }
}
</script>
