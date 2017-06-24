<template>
  <section class="content">
    <div class="box">
      <div class="box-body">
        <!-- 标题 -->
        <div class="row row-margin-bottom">
          <div class="col-md-12">
            <form class="form-inline">
              <div class="col-md-6">
                <label class="control-label">类型</label>
                <select class="form-control inline-block" v-model="param.type">
                  <option value="">全部</option>
                  <option v-for="dictType of dictTypeList" :value="dictType">{{ dictType }}</option>
                </select>

                <label class="control-label">描述</label>
                <input class="form-control inline-block" type="text" v-model="param.description">
              </div>
              <div class="col-md-6">
                <div class="pull-right">
                  <router-link to="/system/dict/add" class="btn btn-primary">添加</router-link>
                </div>
              </div>
            </form>
          </div>
        </div>
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
              <tr v-for="(obj, index) of page.list">
                <td>{{ index + 1 }}</td>
                <td>{{ obj.taskId }}</td>
                <td>{{ obj.itemsName }} </td>
                <td><span class="label label-info">{{ obj.variables.businessName }}</span></td>
                <td>{{ obj.variables.applyUserName }}({{ obj.variables.applyUserId }})</td>
                <td>{{ obj.taskCreateTime }}</td>
                <td>
                  <a @click="pass(obj)">通过</a>
                  <a @click="reject(obj)">驳回</a>
                </td>
              </tr>
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
  import PageMixin from '../../../../../common/mixins/PageMixin.js'
  export default {
    mixins: [PageMixin],
    components: {
      AuditReject
    },
    data: () => {
      return {
        actions: {
          list: {method: 'get', url: '/api/flow/task/todo'},
          audit: {method: 'get', url: '/api/flow/audit'}
        },
        param: {
          type: '',
          description: ''
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
      pass (obj) {
        this.$confirm('确认审核通过吗？', () => {
          let data = {
            taskId: obj.taskId,
            auditUserId: this.userId,
            auditResult: true
          }
          this.resource.audit(data).then((response) => {
            let result = response.body
            if (result.ok) {
              this.$notify.success('审核成功')
              this.query()
            }
          })
        })
      },
      reject (obj) {
        this.rejectConfig.show = true
        this.rejectConfig.title = obj.itemsName
        this.rejectCallback = (remark) => {
          let data = {
            taskId: obj.taskId,
            auditUserId: this.userId,
            auditResult: false,
            remark: remark
          }
          this.resource.audit(data).then((response) => {
            let result = response.body
            if (result.ok) {
              this.$notify.success('驳回成功')
              this.query()
            }
          })
        }
      }
    },
    computed: {
      userInfo: function () {
        return this.$store.state.system.userInfo
      },
      userId: function () {
        // TODO 将userId缓存token
        let userId = ''
        if (this.userInfo) {
          userId = this.userInfo.userId
        }
        return userId
      }
    }
  }
</script>
