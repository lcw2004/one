<template>
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> {{ setting.appVersion }}
    </div>
    <strong>Copyright &copy; 2014-2016 <a href="http://almsaeedstudio.com">Almsaeed Studio</a>.</strong> All rights
    reserved.
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
