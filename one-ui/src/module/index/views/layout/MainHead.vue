<template>
<header class="main-header">
  <!-- Logo -->
  <a href="index2.html" class="logo">
    <span class="logo-mini">{{ setting.productShortName }}</span>
    <span class="logo-lg">{{ setting.productName }}</span>
  </a>

  <nav class="navbar navbar-static-top">
    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
      <span class="sr-only">Toggle navigation</span>
    </a>

    <ul class="nav navbar-nav navbar-left">
      <li :class="{active : activedMenu.id == secondMenu.id}"
        v-for="secondMenu of topMenu.childList"
        @click="activeMenu(secondMenu)">
        <a>{{ secondMenu.name }}</a>
      </li>
    </ul>

    <div class="navbar-custom-menu">
      <ul class="nav navbar-nav">
        <MessagesMenu/>
        <NotificationsMenu/>
        <TasksMenu/>
        <UserInfoMenu/>

        <!-- ControlSidebar 暂时隐藏 -->
        <!-- <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li> -->
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
        return this.$store.state.setting
      },
      topMenu: function () {
        return this.$store.state.system.topMenu
      },
      activedMenu: function () {
        return this.$store.state.system.activedMenu
      }
    },
    methods: {
      activeMenu (secondMenu) {
        this.$store.dispatch('activeMenu', secondMenu)
        if (secondMenu.href) {
          window.open(secondMenu.href)
        }
      }
    }
  }
</script>
