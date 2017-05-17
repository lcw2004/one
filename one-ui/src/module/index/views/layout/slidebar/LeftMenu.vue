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
        <!-- active -->
        <li class="active" @click="activedThirdMenu = this"v-for="menuLevel2 of menuLevel1.childList">
          <a>
            <i :class="menuLevel2.icon"></i>
            <router-link :to='menuLevel2.href'>{{ menuLevel2.name }}</router-link>
          </a>
        </li>
      </ul>
    </li>
  </ul>
</template>

<script>
export default {
  data: function () {
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
