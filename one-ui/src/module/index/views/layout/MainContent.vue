<template>
  <div class="content-wrapper">
    <section class="content-header">
      <h1 style="font-weight: 300">
        <template v-if="thirdMenu.name">{{ thirdMenu.name }}</template>
        <template v-if="!thirdMenu.name">&nbsp;</template>
      </h1>
      <ol class="breadcrumb">
        <template v-for="(menu, index) of menuPath" v-if="menuPath && menuPath.length > 0">
          <li v-if="menu && menu.name" :class="{'active': index != 2}">
            <template v-if="index < menuPath.length - 1">
              <i class="fa fa-dashboard" v-if="index == 0"></i> {{ menu.name }}
            </template>
            <template v-if="index === menuPath.length - 1 && menu.href">
              <router-link :to='menu.href'>{{ menu.name }}</router-link>
            </template>
          </li>
        </template>
      </ol>
    </section>

    <!-- <keep-alive> -->
      <router-view v-if="isShowRouterView"></router-view>
    <!-- </keep-alive> -->
  </div>
</template>

<script>
export default {
  computed: {
    thirdMenu: function () {
      return this.$store.state.system.thirdMenu
    },
    menuPath: function () {
      return this.$store.state.system.menuPath
    },
    isShowRouterView: function () {
      return this.$store.state.system.isShowRouterView
    }
  }
}
</script>
