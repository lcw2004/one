<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box">
          <div class="box-header">
            <form class="form-inline">
              <div class="col-md-3"><label class="control-label">项目</label> <input type="text" placeholder="项目编号/项目名称" class="form-control inline-block"></div>
            </form>
          </div>

          <div class="box-body">
            <table class="table table-bordered table-hover">
              <tbody>
                <tr>
                  <th style="width: 10px">#</th>
                  <th>项目编号</th>
                  <th>项目名称</th>
                  <th>项目类型</th>
                  <th>项目经理</th>
                  <th>业主单位</th>
                  <th>发布日期</th>
                  <th>开标时间</th>
                  <th>状态</th>
                  <th>操作</th>
                </tr>
                <tr>
                  <td>3</td>
                  <td><a>ZB201701160009</a></td>
                  <td>测试3</td>
                  <td>邀请招标</td>
                  <td>张三</td>
                  <td>某某业主单位</td>
                  <td>2017-01-16</td>
                  <td>2017-03-16 12:00:00</td>
                  <td>已提交审核
                    <span class="label label-default">待审核</span>
                  </td>
                  <td>
                    <a @click="success">success</a>
                    <a @click="info">info</a>
                    <a @click="warn">warn</a>
                    <a @click="danger">danger</a>
                    <a @click="auditConfirm()">通过</a>
                    <a @click="config.show = true">驳回</a>
                  </td>
                </tr>
                <tr>
                  <td>3</td>
                  <td><a>ZB201701160009</a></td>
                  <td>测试3</td>
                  <td>邀请招标</td>
                  <td>张三</td>
                  <td>某某业主单位</td>
                  <td>2017-01-16</td>
                  <td>2017-03-16 12:00:00</td>
                  <td>已提交审核
                    <span class="label label-default">待审核</span>
                  </td>
                  <td>
                    <a @click="start">start</a>
                    <a @click="done">done</a>
                    <button type="button" class="btn btn-primary btn-sm" @click="rest">
                      Rest
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <AuditReject :config="config"></AuditReject>
  </section>
</template>

<script>
import AuditReject from '../modal/AuditReject'

export default {
  components: {
    AuditReject
  },
  data: function () {
    return {
      config: {
        show: false,
        title: '驳回原因'
      }
    }
  },
  methods: {
    start: function () {
      this.$progress.start()
    },
    done: function () {
      this.$progress.done()
    },
    success: function () {
      this.$notify.success('审核成功')
    },
    info: function () {
      this.$notify.info('审核成功')
    },
    warn: function () {
      this.$notify.warn('审核成功')
    },
    danger: function () {
      this.$notify.danger('审核成功')
    },
    auditConfirm: function () {
      this.$confirm('确认审核通过吗？', () => {
        this.$notify.success('审核成功')
      })
    },
    rest: function () {
      this.$notify.success('rest')
      let actions = {
        loadMenu: {method: 'get', url: '/api/sys/menu/user'},
        loadEvent: {method: 'get', url: '/redis-demo/event/user'}
      }
      let resource = this.$resource(null, {}, actions)
      resource.loadMenu().then(function (response) {
        console.log(response.body)
        this.$store.dispatch('initMenu', response.body)
      })
    }
  }
}
</script>
