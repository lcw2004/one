<template>
  <ul class="sidebar-menu">
    <li class="treeview" v-for="menuLevel1 of activedMenu.childList">
      <a>
        <i :class="menuLevel1.icon"></i>
        <span>{{ menuLevel1.name }}</span>
        <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
        </span>
      </a>
      <ul class="treeview-menu">
        <li :class="{'active': activedThirdMenu.id == menuLevel2.id}" @click="activedThirdMenu = menuLevel2" v-for="menuLevel2 of menuLevel1.childList">
          <router-link :to='menuLevel2.href'><i :class="menuLevel2.icon"></i> {{ menuLevel2.name }}</router-link>
        </li>
      </ul>
    </li>
  </ul>
</template>

<script>
  export default {
    data: () => {
      return {
        activedThirdMenu: {}
      }
    },
    computed: {
      activedMenu: function () {
        return this.$store.state.system.activedMenu
      }
    }
  }
</script>
