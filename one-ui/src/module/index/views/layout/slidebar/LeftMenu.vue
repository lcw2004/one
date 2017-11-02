<template>
  <ul class="sidebar-menu">
    <li class="treeview" v-for="secondMenu of firstMenu.childList">
      <a @click="activeSecondMenu(secondMenu)">
        <i :class="secondMenu.icon" v-if="secondMenu.icon"></i>
        <i v-if="isShowFirstChar(secondMenu)" style="font-style: normal;">{{ firstChar(secondMenu) }}</i> <!-- 如果未设置LOGO，则显示菜单的第一个字 -->
        <span>{{ secondMenu.name }}</span>
        <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
        </span>
      </a>
      <ul class="treeview-menu">
        <li :class="{'active': thirdMenuInStore.id == thirdMenu.id}" @click="activeThirdMenu(thirdMenu)" v-for="thirdMenu of secondMenu.childList">
          <router-link :to='thirdMenu.href'><i :class="thirdMenu.icon"></i> {{ thirdMenu.name }}</router-link>
        </li>
      </ul>
    </li>
  </ul>
</template>

<script>
  import setHtmlTitle from '../../../../../common/utils/setHtmlTitle.js'
  export default {
    computed: {
      firstMenu: function () {
        return this.$store.state.system.firstMenu
      },
      thirdMenuInStore: function () {
        return this.$store.state.system.thirdMenu
      },
      sidebarIsExpand: function () {
        return this.$store.state.system.sidebarIsExpand
      },
      appName: function () {
        return this.$store.state.setting.appName
      }
    },
    methods: {
      activeSecondMenu (secondMenu) {
        this.$store.dispatch('activeSecondMenu', secondMenu)
      },
      activeThirdMenu (thirdMenu) {
        setHtmlTitle(this.appName + ' - ' + thirdMenu.name)
        this.$store.dispatch('activeThirdMenu', thirdMenu)
      },
      isShowFirstChar (menu) {
        if (menu.icon) {
          return false
        } else if (this.sidebarIsExpand) {
          return false
        }
        return true
      },
      firstChar (menu) {
        let firstChar = ''
        if (menu.name) {
          firstChar = menu.name.charAt(0)
        }
        return firstChar
      }
    }
  }
</script>
