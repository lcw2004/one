<template>
  <router-view v-if="initOk"></router-view>
</template>

<script>
import setHtmlTitle from '@utils/setHtmlTitle'
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
    this.loadInitData()
    this.interval = setInterval(() => {
      this.checkIsLogin()
    }, 1000 * 60)
    this.initSocket()
  },
  destroyed () {
    clearInterval(this.interval)
    this.unSubscribe()
    this.$sockjs.disConnect()
  },
  methods: {
    /**
     * 加载初始化数据
     */
    loadInitData () {
      this.$overlay.start()
      this.$api.system.initData().then((response) => {
        let result = response.data
        if (result.ok) {
          let data = result.data
          // 菜单
          this.initMenu(data.userMenu)
          // 字典
          this.$store.dispatch('initData', data)
          // 系统网页标题
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
    /**
     * 初始化菜单
     */
    initMenu (topMenu) {
      this.$store.dispatch('initTopMenu', topMenu)
      if (topMenu && topMenu.childList && topMenu.childList.length > 0) {
        this.$store.dispatch('activeFirstMenu', topMenu.childList[0])
      }
    },
    /**
     * 检查是否退出系统，如果已经退出系统，则跳转到登录界面
     */
    checkIsLogin () {
      this.$api.system.checkIsLogin().then((response) => {
        let result = response.data
        if (result.ok) {
          this.$store.dispatch('initSystemTime', result.data.systemTime)
          let isLogin = result.data.isLogin
          if (!isLogin) {
            this.toLoginPage()
          }
        }
      })
    },
    /**
     * 初始化Socket链接
     */
    initSocket () {
      this.$sockjs.connect()
      // 订阅系统公告
      this.$sockjs.subscribe('/broker/notice', (subscription) => {
        this.subscription = subscription
      }, (result) => {
        this.$notify.success(JSON.parse(result.body).data.content)
      })
    },
    /**
     * 取消订阅消息
     * @return {[type]} [description]
     */
    unSubscribe () {
      if (this.subscription) {
        this.subscription.unsubscribe()
      }
    },
    toLoginPage () {
      window.location.href = 'login.html'
    }
  },
  computed: {
    userInfo: function () {
      return this.$store.state.system.userInfo
    }
  }
}
</script>
