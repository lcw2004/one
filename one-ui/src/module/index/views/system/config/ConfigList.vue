<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-body">
        <!--Query Start-->
        <form class="form-horizontal">
          <div class="row margin-bottom-10">
            <div class="col-md-4">
              <FormGroup label="参数名称">
                <input class="form-control" type="text" v-model="param.configName" maxlength="100">
              </FormGroup>
            </div>
            <div class="col-md-4">
              <FormGroup label="参数键名">
                <input class="form-control" type="text" v-model="param.configKey" maxlength="100">
              </FormGroup>
            </div>
          </div>
        </form>
        <!--Query End-->

        <div class="row">
          <div class="col-md-12">
            <table class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th>参数名称</th>
                  <th>参数键名</th>
                  <th>参数值</th>
                  <th>参数描述</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="obj of page.list">
                  <td><a>{{ obj.configName }}</a></td>
                  <td>{{ obj.configKey }}</td>
                  <td>{{ obj.configValue }}</td>
                  <td>{{ obj.remark | limitLength }}</td>
                  <td>
                    <router-link :to='"/system/config/" + obj.configId + "/form"'>修改</router-link>
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
        list: {method: 'get', url: '/api/sys/config'}
      },
      param: {
        type: '',
        description: ''
      }
    }
  }
}
</script>
