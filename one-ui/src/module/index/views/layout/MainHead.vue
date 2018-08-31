<template>
  <header class="main-header">
    <!-- Logo -->
    <router-link to='/' class="logo">
      <span class="logo-mini">{{ setting.appShortName }}</span>
      <span class="logo-lg">{{ setting.appName }}</span>
    </router-link>

    <nav class="navbar navbar-static-top">
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button" @click="toggleSidebar">
        <span class="sr-only">Toggle navigation</span>
      </a>

      <ul class="nav navbar-nav navbar-left">
        <li :class="{active : firstMenu.id == secondMenu.id}"
            v-for="secondMenu of topMenu.childList"
            @click="activeFirstMenu(secondMenu)">
          <a>{{ secondMenu.name }}</a>
        </li>
      </ul>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- 首页 -->
          <li><a @click="home()"><i class="fa fa-home" style="font-size: 18px"></i></a></li>

          <!-- 消息 -->
          <MessagesMenu/>

          <!-- 退出 -->
          <li><a @click="logout()"><i class="fa fa-power-off" style="font-size: 16px"></i></a></li>

          <!-- 通知 -->
          <!-- <NotificationsMenu/> -->

          <!-- 任务 -->
          <!-- <TasksMenu/> -->

          <!-- 用户信息 -->
          <UserInfoMenu/>
        </ul>
      </div>
    </nav>
  </header>
</template>

<script>
import MessagesMenu from './head/MessagesMenu'
import NotificationsMenu from './head/NotificationsMenu'
import TasksMenu from './head/TasksMenu'
import UserInfoMenu from './head/UserInfoMenu'

export default {
  components: {
    MessagesMenu,
    NotificationsMenu,
    TasksMenu,
    UserInfoMenu
  },
  computed: {
    setting: function () {
      return this.$store.state.system.settings
    },
    topMenu: function () {
      return this.$store.state.system.topMenu
    },
    firstMenu: function () {
      return this.$store.state.system.firstMenu
    }
  },
  methods: {
    activeFirstMenu (secondMenu) {
      this.$store.dispatch('activeFirstMenu', secondMenu)
      if (secondMenu.href) {
        window.open(secondMenu.href)
      }
    },
    toggleSidebar () {
      this.$store.dispatch('toggleSidebarState')
    },
    logout () {
      this.$store.dispatch('logout')
    },
    home () {
      this.$router.push('/')
    }
  }
}
</script>
