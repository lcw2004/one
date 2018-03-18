<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-body">
        <div class="row">
          <div class="col-md-12">
            <table class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th>序号</th>
                  <th>任务ID</th>
                  <th>任务名称</th>
                  <th>审核事项</th>
                  <th>任务发起人</th>
                  <th>任务发起时间</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <template v-for="(obj, index) of page.list">
                  <tr>
                    <td>{{ index + 1 }}</td>
                    <td>{{ obj.taskId }}</td>
                    <td>{{ obj.itemsName }}</td>
                    <td><span class="label label-info">{{ obj.variables.businessName }}</span></td>
                    <td>{{ obj.variables.applyUserName }}({{ obj.variables.applyUserId }})</td>
                    <td>{{ obj.taskCreateTime }}</td>
                    <td>
                      <router-link :to='"/task/todo/" + obj.taskId'>审核</router-link>
                      <a @click="shouData(obj)" v-if="!obj.show">展开参数</a>
                      <a @click="obj.show = false" v-if="obj.show">收起参数</a>
                    </td>
                  </tr>
                  <template v-if="obj.show">
                    <tr>
                      <td colspan="8">
                        <table class="table table-bordered table-hover" style="word-break:break-all; word-wrap:break-all;">
                          <tbody>
                            <tr>
                              <th style="width: 10px">#</th>
                              <th>键</th>
                              <th>值</th>
                            </tr>
                            <template v-for="(item, key, index) in obj.variables">
                              <tr>
                                <td>{{ index + 1 }}</td>
                                <td style="width: 200px;">{{ key }}</td>
                                <td style="max-width: 100px;">{{ item }}</td>
                              </tr>
                            </template>
                          </tbody>
                        </table>
                      </td>
                    </tr>
                  </template>
                </template>
              </tbody>
            </table>
            <Pagination :page="page" @page="handlerPage(arguments)"></Pagination>
          </div>
        </div>
      </div>
    </div>

    <AuditReject :config="rejectConfig" :callback="rejectCallback"></AuditReject>
  </section>
</template>

<script>
import AuditReject from './AuditReject'
import PageMixin from '@mixins/PageMixin'

export default {
  mixins: [PageMixin],
  components: {
    AuditReject
  },
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/flow/task/todo'}
      },
      param: {
        type: '',
        description: '',
        processDefinitionKeys: ''
      },
      rejectConfig: {
        show: false
      },
      rejectCallback: Function
    }
  },
  watch: {
    'param': {
      handler: function () {
        this.query()
      },
      deep: true
    }
  },
  methods: {
    shouData (obj) {
      this.$set(obj, 'show', true)
    }
  }
}
</script>
