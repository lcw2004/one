<template>
  <aside class="main-sidebar">
    <section class="sidebar">
      <UserPanel/>
      <!-- <SearchForm/> -->
      <LeftMenu/>
    </section>
  </aside>
</template>

<script>
import UserPanel from './slidebar/UserPanel'
import SearchForm from './slidebar/SearchForm'
import LeftMenu from './slidebar/LeftMenu'

export default {
  components: {
    UserPanel,
    SearchForm,
    LeftMenu
  }
}
</script>
