<template>
  <section class="content">
    <DesktopOfManager></DesktopOfManager>
  </section>
</template>

<script>
import DesktopOfManager from './DesktopOfManager.vue'
import DesktopOfSupplier from './DesktopOfSupplier.vue'

export default {
  components: {
    DesktopOfManager,
    DesktopOfSupplier
  },
  mounted () {
    this.activeHomePage()
  },
  methods: {
    activeHomePage () {
      this.$store.dispatch('activeHomePage')
    }
  },
  computed: {
    userInfo: function () {
      return this.$store.state.system.userInfo
    }
  }
}
</script>
