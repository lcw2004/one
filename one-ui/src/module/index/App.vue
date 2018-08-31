<template>
  <router-view v-if="initOk"></router-view>
</template>

<script>
import { setHtmlTitle } from '@utils/common'
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

          // 设置系统页面标题
          setHtmlTitle(data.sysSetting.appName)

          // 初始化数据
          this.$store.dispatch('initData', data)

          // 是否完善供应商信息
          let isNeed = data.isNeedPerfectSupplierInfo
          if (isNeed) {
            this.$router.push('/supplier/prefect')
          }

          this.doAfterInitSuccess()
        } else {
          this.toLoginPage()
        }
      })
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

    doAfterInitSuccess () {
      // 初始化Socket
      this.initSocket()

      // 设置定时器检查登录状态
      this.interval = setInterval(this.checkIsLogin, 1000 * 60)

      // 50ms后关闭加载组件，并显示页面
      setTimeout(() => {
        this.$overlay.done()
        this.initOk = true
      }, 10)
    },

    /**
     * 初始化Socket链接
     */
    initSocket () {
//      this.$sockjs.connect()
//      // 订阅系统公告
//      this.$sockjs.subscribe('/broker/notice', (subscription) => {
//        this.subscription = subscription
//      }, (result) => {
//        this.$notify.success(JSON.parse(result.body).data.content)
//      })
    },

    /**
     * 取消订阅消息
     */
    unSubscribe () {
//      if (this.subscription) {
//        this.subscription.unsubscribe()
//      }
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
