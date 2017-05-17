<template>
  <router-view v-if="initOk"></router-view>
</template>

<script>
import Home from './views/layout/Home'

export default {
  components: {
    Home
  },
  data: function () {
    return {
      initOk: false
    }
  },
  mounted () {
    this.loadMenu()
    this.loadDict()
    this.loadUserInfo()
    this.isNeedPerfectSupplierInfo()
  },
  methods: {
    loadMenu () {
      this.$http.get('/api/userMenu').then(function (response) {
        let result = response.body
        if (result.ok) {
          let topMenu = result.data
          this.$store.dispatch('initMenu', topMenu)
          if (topMenu && topMenu.childList && topMenu.childList.length > 0) {
            this.$store.dispatch('activeMenu', topMenu.childList[0])
          }
        }
      })
    },
    loadDict () {
      this.$http.get('/api/sys/dict/group').then(function (response) {
        let result = response.body
        if (result.ok) {
          this.$store.dispatch('initDict', result.data)
        }
      })
    },
    loadUserInfo () {
      this.$http.get('/api/userInfo').then(function (response) {
        let result = response.body
        if (result.ok) {
          this.$store.dispatch('initUserInfo', result.data)
        }
      })
    },
    isNeedPerfectSupplierInfo () {
      // TODO 需要将这个判断放到全局，未完善信息的不允许访问到其他功能
      this.$http.get('/api/isNeedPerfectSupplierInfo').then(function (response) {
        let result = response.body
        if (result.ok) {
          let isNeed = result.data
          if (isNeed) {
            this.$router.push('/prefect-info')
          }
        }
        this.initOk = true
      })
    }
  },
  computed: {
    userInfo: function () {
      return this.$store.state.system.userInfo
    }
  }
}
</script>
