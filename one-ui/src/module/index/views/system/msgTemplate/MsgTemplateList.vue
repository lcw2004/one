<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-body">
        <!--Query Start-->
        <form class="form-horizontal">
          <div class="row margin-bottom-10">
            <div class="col-md-4">
              <FormGroup label="查询条件">
                <input class="form-control" type="text" placeholder="编号/名称/内容" v-model="param.likeName">
              </FormGroup>
            </div>
            <div class="col-md-4">
            </div>
            <div class="col-md-4">
              <div class="pull-right">
                <router-link to="/msg/template/add" class="btn btn-primary">添加</router-link>
              </div>
            </div>
          </div>
        </form>
        <!--Query End-->

        <div class="row">
          <div class="col-md-12">
            <table class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th style="width: 10px">序号</th>
                  <th>模板编号</th>
                  <th>模板类型</th>
                  <th>模板标题</th>
                  <th>消息内容</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(obj, index) of page.list">
                  <td>{{ index + 1 }}</td>
                  <td>{{ obj.templateId }}</td>
                  <td>{{ obj.msgType ? obj.msgType.msgTypeName : '' }}</td>
                  <td>{{ obj.title }}</td>
                  <td class="word_warp">{{ obj.content }}</td>
                  <td>
                    <router-link :to='"/msg/template/" + obj.templateId + "/form"'>修改</router-link>
                    <a @click="deleteData(obj.templateId)">删除</a>
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
        list: {method: 'get', url: '/api/msg/template'}
      },
      param: {
        likeName: ''
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
    },
    deleteData (id) {
      this.$confirm('确认删除吗？', () => {
        this.$api.system.deleteMsgTemplate(id).then((response) => {
          let result = response.data
          if (result.ok) {
            this.query()
            this.$notify.success('删除成功！')
          }
        })
      })
    }
  }
}
</script>

<style lang="less" type="text/less">
.word_warp {
  white-space: normal;
  word-break: break-all;
  word-wrap: break-word;
}
</style>
