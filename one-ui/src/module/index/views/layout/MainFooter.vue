<template>
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> {{ setting.appVersion }}
    </div>
    <strong>Copyright &copy; 2014-2017 <a href="https://github.com/lcw2004/one">lcw2004</a>.</strong> All rights reserved.
  </footer>
</template>

<script>
  export default {
    computed: {
      setting: function () {
        return this.$store.state.setting
      }
    }
  }
</script>
