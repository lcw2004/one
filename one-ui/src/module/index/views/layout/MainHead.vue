<template>
  <header class="main-header">
    <!-- Logo -->
    <a href="#" class="logo">
      <span class="logo-mini">{{ setting.appShortName }}</span>
      <span class="logo-lg">{{ setting.appName }}</span>
    </a>

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
          <li>
            <router-link to='/'><i class="fa fa-home" style="font-size: 18px"></i></router-link>
          </li>

          <MessagesMenu/>
          <!-- <NotificationsMenu/> -->
          <!-- <TasksMenu/> -->
          <UserInfoMenu/>

          <!-- ControlSidebar 暂时隐藏
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>-->
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
    }
  }
}
</script>
