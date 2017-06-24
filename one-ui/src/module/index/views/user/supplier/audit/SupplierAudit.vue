<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box">
          <div class="box-header">
            <form class="form-inline">
              <div class="row">
                <div class="col-md-12">
                  <div class="col-md-3">
                    <input type="text" placeholder="公司名/简称/曾用名称" class="form-control inline-block">
                  </div>
                </div>
              </div>

              <SupplierQueryCondition></SupplierQueryCondition>
            </form>
          </div>

          <div class="box-body">
            <table class="table table-bordered table-hover">
              <tbody>
              <tr>
                <th style="width: 10px">#</th>
                <th>供应商名称</th>
                <th>注册时间</th>
                <th>联系人</th>
                <th>手机号码</th>
                <th>审核项</th>
                <th>状态</th>
                <th>操作</th>
              </tr>
              <tr>
                <td>1</td>
                <td>
                  <router-link to="/supplier/info">某某有限公司</router-link>
                </td>
                <td>2017-01-01</td>
                <td><a>张三</a></td>
                <td>13999999999</td>
                <th>供应商注册</th>
                <td>
                  <span class="label label-warning">待审核</span>
                </td>
                <td>
                  <a @click="ok">通过</a>
                  <a @click="config.show = true">驳回</a>
                </td>
              </tr>
              <tr>
                <td>1</td>
                <td>
                  <router-link to="/supplier/info">某某有限公司</router-link>
                </td>
                <td>2017-01-01</td>
                <td><a>张三</a></td>
                <td>13999999999</td>
                <th>供应商资料变更</th>
                <td>
                  <span class="label label-warning">待审核</span>
                </td>
                <td>
                  <a @click="ok">通过</a>
                  <a @click="config.show = true">驳回</a>
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
  import AuditReject from '../../../personal/task/AuditReject'

  export default {
    components: {
      AuditReject
    },
    data: () => {
      return {
        config: {
          show: false,
          title: '驳回原因'
        }
      }
    },
    methods: {
      ok () {
        this.$confirm('确认审核通过吗？', () => this.$notify.success('提交成功，请等待审核！'))
      }
    }
  }
</script>
