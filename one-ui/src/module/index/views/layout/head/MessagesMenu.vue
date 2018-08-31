<template>
  <li class="dropdown messages-menu" :class="{ open: isOpen }">
    <a class="dropdown-toggle" data-toggle="dropdown" @click="toggole()">
      <i class="fa fa-envelope-o"></i>
      <span class="label label-success" v-if="unreadMessageCount > 0">{{ unreadMessageCount }}</span>
    </a>
    <ul class="dropdown-menu">
      <li class="header">您有 {{ unreadMessageCount }} 条未读消息</li>
      <li>
        <ul class="menu">
          <li v-for="obj of messageList">
            <a class="one-message" @click="viewMore()">
              <h4>
                {{ obj.title }}
                <small><i class="fa fa-clock-o"></i> {{ obj.timeDiff }}</small>
              </h4>
              <p v-html="obj.content"></p>
            </a>
          </li>
        </ul>
      </li>
      <li class="footer">
        <a @click="viewMore()">查看更多消息</a>
      </li>
    </ul>
  </li>
</template>

<script>
export default{
  data: function () {
    return {
      isOpen: false,
      page: {}
    }
  },
  computed: {
    messageList: function () {
      return this.$store.state.system.messageList
    },
    unreadMessageCount: function () {
      return this.$store.state.system.unreadMessageCount
    }
  },
  mounted () {
    this.listMessage()
    this.interval = setInterval(() => {
      this.listMessage()
    }, 1000 * 60)
  },
  destroyed () {
    clearInterval(this.interval)
  },
  methods: {
    toggole () {
      this.isOpen = !this.isOpen
      if (this.isOpen) {
        this.listMessage()
      }
    },
    viewMore () {
      this.$store.dispatch('activePageTitle', '消息中心')
      this.goToPage('/personal/message')
      this.toggole()
    },
    listMessage () {
      this.$store.dispatch('loadMessage')
    },
    goToPage (url) {
      // 如下代码用于点击菜单的时候强制刷新页面
      this.$store.dispatch('isShowRouterView', false)
      this.$nextTick(() => {
        this.$router.push(url)
        this.$store.dispatch('isShowRouterView', true)
      })
    }
  }
}
</script>

<style lang="less" type="text/less">
.one-message {
  h4 {
    margin-left: 0 !important;
    margin-bottom: 3px;
  }
  p {
    margin-left: 0 !important;
    margin-top: 10px !important;
    white-space: normal !important;
    word-break: normalbreak-all;
    word-wrap: break-word;
  }
  a {
    margin: 0 5px;
  }
}
</style>
