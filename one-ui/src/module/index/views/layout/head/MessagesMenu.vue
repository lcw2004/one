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
            <a class="message" @click="viewMore()">
              <h4>
                {{ obj.title }}
                <small><i class="fa fa-clock-o"></i> {{ obj.timeDiff }}</small>
              </h4>
              <p>{{ obj.content }}</p>
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
      this.$router.push('/personal/message')
      this.toggole()
    },
    listMessage () {
      this.$store.dispatch('loadMessage')
    }
  }
}
</script>

<style lang="less" scoped>
.message {
  h4 {
    margin-left: 0 !important;
    margin-bottom: 3px;
  }
  p {
    margin-left: 0 !important;
    white-space:normal;
    word-break:break-all;
    word-wrap:break-word;
  }
}
</style>
