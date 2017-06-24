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
        default: 'rotate'
      },
      mode: {
        default: 'out-in'
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
