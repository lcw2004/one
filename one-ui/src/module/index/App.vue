<template>
  <router-view v-if="initOk"></router-view>
</template>

<script>
  import setHtmlTitle from '../../common/utils/setHtmlTitle'
  import Home from './views/layout/Home'

  export default {
    components: {
      Home
    },
    data: () => {
      return {
        initOk: false
      }
    },
    mounted () {
      this.loadMenu()
      this.loadDict()
      this.loadUserInfo()
      this.loadSetting()
    },
    methods: {
      loadMenu () {
        this.$http.get('/api/userMenu').then((response) => {
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
        this.$http.get('/api/sys/dict/group').then((response) => {
          let result = response.body
          if (result.ok) {
            this.$store.dispatch('initDict', result.data)
          }
        })
      },
      loadUserInfo () {
        this.$http.get('/api/userInfo').then((response) => {
          let result = response.body
          if (result.ok) {
            this.$store.dispatch('initUserInfo', result.data)
            this.initOk = true
          }
        })
      },
      loadSetting () {
        this.$http.get('/api/sys/setting').then((response) => {
          let result = response.body
          if (result.ok) {
            this.$store.dispatch('initSetting', result.data)
            setHtmlTitle(result.data.appName)
          }
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
