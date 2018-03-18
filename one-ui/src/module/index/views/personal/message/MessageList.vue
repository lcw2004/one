<template>
  <section class="content">
    <div class="box">
      <div class="box-header">
        <h3 class="box-title">消息中心</h3>
      </div>
      <div class="box-body">
        <div class="row">
          <div class="col-md-12">
            <div class="pull-right">
              <span class="message_count">
                <span class="message_unread">{{ unReadCount }}条未读/</span>
                <span class="message_read">{{ totalCount }}条消息</span>
              </span>
              <template v-if="messageIdList.length > 0">
                <a @click="markMessageAsRead()">标为已读</a>
                <span class="btn-line"></span>
                <a @click="markMessageAsDelete()">删除</a>
                <span class="btn-line"></span>
              </template>
              <a @click="markAllMessageAsRead()">全部标为已读</a>
              <span class="btn-line"></span>
              <a @click="markAllMessageAsDelete()">全部删除</a>
            </div>
          </div>
          <div class="col-md-12">
            <table class="message_table">
              <tbody>
                <tr v-for="(obj, index) of page.list">
                  <td><input type="checkbox" :value="obj.messageId" :key="obj.messageId" v-model="messageIdList"></td>
                  <td>
                    <div class="message_title" @click="markAsRead(obj.messageId)">
                      <span v-if="obj.status == 0" class="message_unread">{{ obj.title }}</span>
                      <span v-if="obj.status == 1" class="message_read">{{ obj.title }}</span>
                    </div>
                  <td>
                    <i v-if="obj.status == 0" class="fa fa-fw fa-envelope-o" style="color: #3c8dbc"></i>
                    <i v-if="obj.status == 1" class="fa fa-fw fa-envelope-o" style="color: #999999"></i>
                  </td>
                  <td>
                    <div class="message_content" @click="markAsRead(obj.messageId)">
                      <span v-if="obj.status == 0" class="message_unread" v-html="obj.content"></span>
                      <span v-if="obj.status == 1" class="message_read" v-html="obj.content"></span>
                    </div>
                  </td>
                  <td>
                    <div class="message_time">
                      <span v-if="obj.status == 0" class="message_unread">{{ obj.sendTime }}</span>
                      <span v-if="obj.status == 1" class="message_read">{{ obj.sendTime }}</span>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>

            <Pagination :page="page" @page="handlerPage(arguments)"></Pagination>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import PageMixin from '@mixins/PageMixin'

export default {
  mixins: [PageMixin],
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/msg/message'}
      },
      param: {
        loadAfterMounted: true
      },
      messageIdList: []
    }
  },
  computed: {
    unReadCount: function () {
      return this.page && this.page.ext && this.page.ext.unReadCount ? this.page.ext.unReadCount : 0
    },
    totalCount: function () {
      return this.page && this.page.ext && this.page.ext.totalCount ? this.page.ext.totalCount : 0
    }
  },
  methods: {
    loadData () {
      this.query()
      this.$store.dispatch('loadMessage')
    },
    markAsRead (messageId) {
      this.$api.system.markMessageAsRead([messageId]).then((response) => {
        let result = response.data
        if (result.ok) {
          this.loadData()
        }
      })
    },
    markMessageAsRead () {
      if (this.messageIdList.length > 0) {
        this.$api.system.markMessageAsRead(this.messageIdList).then((response) => {
          let result = response.data
          if (result.ok) {
            this.$notify.success('操作成功')
            this.loadData()
          }
        })
      }
    },
    markMessageAsDelete () {
      if (this.messageIdList.length > 0) {
        this.$api.system.markMessageAsDelete(this.messageIdList).then((response) => {
          let result = response.data
          if (result.ok) {
            this.$notify.success('操作成功')
            this.loadData()
          }
        })
      }
    },
    markAllMessageAsRead () {
      this.$api.system.markAllMessageAsRead().then((response) => {
        let result = response.data
        if (result.ok) {
          this.$notify.success('操作成功')
          this.loadData()
        }
      })
    },
    markAllMessageAsDelete () {
      this.$api.system.markAllMessageAsDelete().then((response) => {
        let result = response.data
        if (result.ok) {
          this.$notify.success('操作成功')
          this.loadData()
        }
      })
    }
  }
}
</script>

<style lang="less">
.message_table {
  border-collapse: collapse;
  width: 100%;
  table-layout: fixed;
  text-align: left;

  tr td {
    font-size: 14px;
    color: #2d2d2d;
    padding-bottom: 20px;
    vertical-align: text-top;
  }
  tr:not(:first-of-type) {
    border-bottom: 1px dotted #e5e5e5;
  }
  tr input[type=checkbox] {
    width: 13px;
    height: 13px;
    margin-top: 5px;
  }

  tr td:first-of-type {
    padding-left: 20px;
  }
  tr:first-of-type {
    background: #f4f5f6;
    height: 40px;
  }

  tr:first-of-type {
    height: 60px;
    background-color: #fff;
  }
  tr td:nth-of-type(1) {
    width: 44px;
  }
  tr td:nth-of-type(2) {
    width: 96px;
  }
  tr td:nth-of-type(3) {
    width: 25px;
  }
  tr td:nth-of-type(4) {
    width: 650px;
  }
  tr td:nth-of-type(5) {
    width: 138px;
  }
}
.message_count {
  margin-right: 30px;
}
.message_unread {
  color: #111111;
}
.message_read {
  color: #999999;
}
.message_title {
  font-size: 14px;
}
.message_time {
  font-size: 12px;
}
.message_content {
  overflow: hidden;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  text-overflow: ellipsis;
  padding-right: 50px;
  white-space:normal;
  word-break:break-all;
  word-wrap:break-word;
  font-size: 12px;
}
.btn-line {
  width: 0;
  height: 13px;
  border-left: 1px solid #999;
  display: inline-block;
  margin: 0 10px;
  vertical-align: middle;
}

</style>
