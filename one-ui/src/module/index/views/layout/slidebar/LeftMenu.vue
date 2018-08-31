<template>
  <ul class="sidebar-menu">
    <template v-for="secondMenu of firstMenu.childList">
      <li v-if="secondMenu.isShow" class="treeview" :class="getTreeViewClass(secondMenu)">
        <a @click="activeSecondMenu(secondMenu)">
          <i v-if="secondMenu.icon" :class="secondMenu.icon"></i>
          <i v-if="isShowFirstChar(secondMenu)" style="font-style: normal;">{{ firstChar(secondMenu) }}</i>
          <span>{{ secondMenu.name }}</span>
          <span class="pull-right-container">
            <i class="fa fa-angle-left pull-right"></i>
          </span>
        </a>
        <ul v-show="secondMenu.isShowInMenu === 1" class="treeview-menu" :class="getTreeMenuClass(secondMenu)">
          <template v-for="thirdMenu of secondMenu.childList">
            <li v-if="thirdMenu.isShow" :class="{'active': thirdMenuInStore.id == thirdMenu.id}" @click="activeThirdMenu(thirdMenu)">
              <a @click="goToPage(thirdMenu)" class="treeview-menu-a"><i :class="thirdMenu.icon"></i> {{ thirdMenu.name }}</a>
            </li>
          </template>
        </ul>
      </li>
    </template>
  </ul>
</template>

<script>
import { setHtmlTitle } from '@utils/common'

export default {
  data: function () {
    return {
      show: false
    }
  },
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
      return this.$store.state.system.settings.appName
    }
  },
  methods: {
    activeSecondMenu (secondMenu) {
      this.toggle(secondMenu)
      this.$store.dispatch('activeSecondMenu', secondMenu)
    },
    activeThirdMenu (thirdMenu) {
      setHtmlTitle(this.appName + ' - ' + thirdMenu.name)
      this.$store.dispatch('activeThirdMenu', thirdMenu)
    },
    /**
     * 如果未设置LOGO，则显示菜单的第一个字
     */
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
    },
    toggle (menu) {
      if (menu.isShowInMenu === 1) {
        this.$set(menu, 'isShowInMenu', 0)
      } else {
        this.$set(menu, 'isShowInMenu', 1)
      }
    },
    getTreeViewClass (menu) {
      return {
        'active': menu.isShowInMenu === 1
      }
    },
    getTreeMenuClass (menu) {
      return {
        'menu-open': menu.isShowInMenu === 1
      }
    },
    goToPage (menu) {
      // 如下代码用于点击菜单的时候强制刷新页面
      this.$store.dispatch('isShowRouterView', false)
      this.$nextTick(() => {
        this.$router.push(menu.href)
        this.$store.dispatch('isShowRouterView', true)
      })
    }
  }
}
</script>

<style lang="less" type="text/less">
.treeview-menu-a {
  padding-left: 25px !important;
}
</style>
