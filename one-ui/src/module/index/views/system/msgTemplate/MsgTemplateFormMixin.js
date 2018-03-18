export default {
  data: function () {
    return {
      form: {
        actions: {
          get: this.$api.system.getMsgTemplate,
          save: this.$api.system.saveMsgTemplate,
          update: this.$api.system.updateMsgTemplate
        },
        continue: false
      },

      obj: {
        templateId: '',
        title: '',
        content: '',
        msgTypeCode: '',
        channelIdList: []
      },

      msgTypeList: [],
      msgChannelList: []
    }
  },
  mounted () {
    this.initData()
  },
  methods: {
    initData () {
      this.$api.system.listMsgType().then((response) => {
        let result = response.data
        if (result.ok) {
          this.msgTypeList = result.data
        }
      })
      this.$api.system.listMsgChannel().then((response) => {
        let result = response.data
        if (result.ok) {
          this.msgChannelList = result.data
        }
      })
    }
  }
}
