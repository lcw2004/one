<template>
  <router-view v-if="initOk"></router-view>
</template>

<script>
  import Vue from 'vue'
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
      this.loadData()
    },
    methods: {
      loadData () {
        this.$overlay.start()
        Vue.http.get('/api/initData').then((response) => {
          // 检查是否是JSON串
          // 转为小写解决浏览器兼容性问题
          let headersMap = JSON.parse(JSON.stringify(response.headers.map).toLowerCase())
          let contenType = headersMap['content-type'] + ''
          if (contenType.indexOf('application/json') < 0) {
            window.location.href = 'login.html'
            this.$overlay.done()
            return
          }

          let result = response.body
          if (result.ok) {
            let data = result.data

            // 菜单
            this.initMenu(data.userMenu)

            // 字典
            this.$store.dispatch('initDict', data.sysDict)

            // 用户信息
            this.$store.dispatch('initUserInfo', data.userInfo)

            // 系统配置
            this.$store.dispatch('initSetting', data.sysSetting)
            setHtmlTitle(data.sysSetting.appName)

            // 是否完善供应商信息
            let isNeed = data.isNeedPerfectSupplierInfo
            if (isNeed) {
              this.$router.push('/prefect-info')
            }
          }

          // 50ms后关闭加载组件，并显示页面
          setTimeout(() => {
            this.$overlay.done()
            this.initOk = true
          }, 10)
        })
      },
      initMenu (data) {
        let topMenu = data
        this.$store.dispatch('initTopMenu', topMenu)
        if (topMenu && topMenu.childList && topMenu.childList.length > 0) {
          this.$store.dispatch('activeFirstMenu', topMenu.childList[0])
        }
      }
    },
    computed: {
      userInfo: function () {
        return this.$store.state.system.userInfo
      }
    }
  }
</script>
