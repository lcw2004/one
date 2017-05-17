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
                  <th>操作</th>
                </tr>
                <tr>
                  <td>1</td>
                  <td><router-link to="/supplier/info">某某有限公司</router-link></td>
                  <td>2017-01-01</td>
                  <td><a>张三</a></td>
                  <td>13999999999</td>
                  <td>
                    <a @click="config.show = true">上传调查表</a>
                  </td>
                </tr>
                <tr>
                  <td>1</td>
                  <td><a>某某有限公司</a></td>
                  <td>2017-01-01</td>
                  <td><a>张三</a></td>
                  <td>13999999999</td>
                  <td>
                    <a @click="config.show = true">上传调查表</a>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <QuestionnaireUpload :config="config"></QuestionnaireUpload>
  </section>
</template>

<script>
import QuestionnaireUpload from './QuestionnaireUpload'

export default {
  components: {
    QuestionnaireUpload
  },
  data: function () {
    return {
      config: {
        title: '上传调查表',
        show: false
      }
    }
  },
  methods: {
  }
}
</script>
