<template>
  <div class="user-panel">
    <div class="pull-left image">
      <img src="static/adminlte/img/user2-160x160.jpg" class="img-circle" alt="User Image">
    </div>
    <div class="pull-left info">
      <p>{{ userInfo.name }}</p>
      <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    userInfo: function () {
      return this.$store.state.system.userInfo
    }
  }
}
</script>
