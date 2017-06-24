<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box">
          <div class="box-header">
            <h3 class="box-title">供应商投标类别审核</h3>
          </div>
          <div class="box-body">
            <div class="row">
              <div class="col-md-12">
                <div class="pull-right">
                  <button type="button" class="btn btn-primary" @click="ok">
                    通过
                  </button>
                  <button type="button" class="btn btn-primary" @click="config.show = true">
                    驳回
                  </button>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-12">
                <table class="table table-bordered table-hover">
                  <tbody>
                  <tr>
                    <th style="width: 10px"><input type="checkbox"></th>
                    <th>采购类别</th>
                    <th>申请日期</th>
                    <th>状态</th>
                  </tr>
                  <tr>
                    <td><input type="checkbox"></td>
                    <td>物资类 - 黑色金属 - 型材</td>
                    <td>2017-01-01 11:11:11</td>
                    <td><span class="label label-success">通过</span></td>
                  </tr>
                  <tr>
                    <td><input type="checkbox"></td>
                    <td>物资类 - 黑色金属 - 钢板</td>
                    <td>2017-01-01 11:11:11</td>
                    <td><span class="label label-success">通过</span></td>
                  </tr>
                  <tr>
                    <td><input type="checkbox"></td>
                    <td>物资类 - 黑色金属 - 铸锻件</td>
                    <td>2017-01-01 11:11:11</td>
                    <td><span class="label label-warning">待审核</span></td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <AuditReject :config="config"></AuditReject>
  </section>
</template>

<script>
  import SupplierView from '../SupplierView'
  import AuditReject from '../../../personal/task/AuditReject'

  export default {
    components: {
      SupplierView,
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
