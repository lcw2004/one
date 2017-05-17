<template>
  <li class="dropdown user user-menu" :class="{ open: isOpen }">
    <a class="dropdown-toggle" data-toggle="dropdown" @click="toggole()">
      <img src="static/adminlte/img/user2-160x160.jpg" class="user-image" alt="User Image">
      <span class="hidden-xs">{{ userInfo.name }}</span>
    </a>
    <ul class="dropdown-menu">
      <!-- User image -->
      <li class="user-header">
        <img src="static/adminlte/img/user2-160x160.jpg" class="img-circle" alt="User Image">

        <p>
          {{ userInfo.name }}
          <small>登录时间：{{ userInfo.lastLoginTime }}</small>
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
          <router-link to='/personal/user-info' class="btn btn-default btn-flat">个人信息</router-link>
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
      toggole: function () {
        this.isOpen = !this.isOpen
      },
      logout: function () {
        this.$http.get('/api/logout').then(function (response) {
          this.$store.dispatch('removeUserInfo')
          window.location.href = 'login.html'
        })
      }
    },
    computed: {
      userInfo: function () {
        return this.$store.state.system.userInfo
      }
    }
  }
</script>
