<template>
  <li class="dropdown user user-menu" :class="{ open: isOpen }">
    <a class="dropdown-toggle" data-toggle="dropdown" @click="toggole()">
      <img src="static/images/head_man.png" class="user-image" alt="User Image">
      <span class="hidden-xs">{{ userInfo.name | limitLength(4) }}</span>
    </a>
    <ul class="dropdown-menu">
      <!-- User image -->
      <li class="user-header">
        <img src="static/images/head_man.png" class="img-circle" alt="User Image">
        <p>
          {{ userInfo.name }}
          <small style="margin-top: 10px">当前时间：{{ systemTimeStr }}</small>
        </p>
      </li>
      <!-- Menu Body -->
      <li class="user-body">
        <div class="row">
          <div class="col-xs-4 text-center">
            <a href="#">Followers</a>
          </div>
          <div class="col-xs-4 text-center">
            <a href="#">Sales</a>
          </div>
          <div class="col-xs-4 text-center">
            <a href="#">Friends</a>
          </div>
        </div>
        <!-- /.row -->
      </li>
      <!-- Menu Footer-->
      <li class="user-footer">
        <div class="pull-left">
          <a class="btn btn-default btn-flat" @click="userInfoPage()">个人信息</a>
        </div>
        <div class="pull-right">
          <a class="btn btn-default btn-flat" @click="logout">退出登录</a>
        </div>
      </li>
    </ul>
  </li>
</template>

<script>
export default {
  data: function () {
    return {
      isOpen: false
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
  },
  computed: {
    userInfo: function () {
      return this.$store.state.system.userInfo
    }
  }
}
</script>
