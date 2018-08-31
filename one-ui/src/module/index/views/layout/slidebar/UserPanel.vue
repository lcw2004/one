<template>
  <div class="user-panel">
    <div class="pull-left image" @click="userInfoPage()">
      <img src="static/images/head_man.png" class="img-circle" alt="User Image">
    </div>
    <div class="pull-left info">
      <p>{{ userInfo.name | limitLength(4) }}</p>
      <a><i class="fa fa-circle text-success"></i> 在线</a>
      <a @click="logout"><i class="fa fa-sign-out text-danger"></i> 注销</a>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    userInfo: function () {
      return this.$store.state.system.userInfo
    }
  },
  methods: {
    toggole () {
      this.isOpen = !this.isOpen
    },
    logout () {
      this.$store.dispatch('logout')
    },
    userInfoPage () {
      this.$store.dispatch('activePageTitle', '个人信息')
      this.$router.push('/personal/user-info')
      this.isOpen = false
    }
  }
}
</script>
