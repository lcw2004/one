<template>
  <aside class="main-sidebar">
    <section class="sidebar">
      <UserPanel/>
      <SearchForm/>
      <LeftMenu v-if="topMenu.id != null"/>
      <LeftMenuStatic v-if="topMenu.id == null"/>
    </section>
  </aside>
</template>

<script>
  import UserPanel from './slidebar/UserPanel'
  import SearchForm from './slidebar/SearchForm'
  import LeftMenu from './slidebar/LeftMenu'
  import LeftMenuStatic from './slidebar/LeftMenuStatic'

  export default {
    components: {
      UserPanel,
      SearchForm,
      LeftMenu,
      LeftMenuStatic
    },
    computed: {
      topMenu: function () {
        return this.$store.state.system.topMenu
      }
    }
  }
</script>
