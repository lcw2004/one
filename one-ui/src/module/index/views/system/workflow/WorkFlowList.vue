<template>
  <section class="content">
    <div class="box">
      <div class="box-body">
        <!--Query Start-->
        <form class="form-horizontal">
          <div class="row row-margin-bottom">
            <div class="col-md-4">
              <FormGroup label="查询条件">
                <input class="form-control inline-block" type="text" placeholder="流程ID/流程名称" v-model="param.likeName">
              </FormGroup>
            </div>
            <div class="col-md-4">
            </div>
            <div class="col-md-4">
              <div class="pull-right">
                <FileUpload type="bpmn,zip,bar" btnText="部署流程" url="/api/flow/flowState" :showProgress="false" :callback="uploadSuccess"></FileUpload>
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
                  <th style="width: 10px">#</th>
                  <th>流程ID</th>
                  <th>流程名称</th>
                  <th>最后修改时间</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(obj, index) of page.list">
                  <td>{{ index + 1 }}</td>
                  <td>{{ obj.processKey }}</td>
                  <td>{{ obj.processName }}</td>
                  <td>{{ obj.lastUpdateTime }}</td>
                  <td>
                    <router-link :to='"/system/workflow/" + obj.processKey + "/form"'>修改</router-link>
                    <a @click="deleteData(obj.processKey)">删除</a>
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
  import PageMixin from 'mixins/PageMixin.js'

  export default {
    mixins: [PageMixin],
    data: () => {
      return {
        actions: {
          list: {method: 'get', url: '/api/flow/flowState'},
          deleteData: {method: 'delete', url: '/api/flow/flowState{/id}'},
          uploadWorkflow: {method: 'post', url: '/api/flow/flowState'}
        },
        param: {
          likeName: ''
        }
      }
    },
    methods: {
      deleteData (id) {
        this.$confirm('确认删除吗？', () => {
          this.resource.deleteData({id: id}).then((response) => {
            let result = response.body
            if (result.ok) {
              this.query()
              this.$notify.success('删除成功！')
            }
          })
        })
      },
      showSelectFile () {
        document.getElementById('file').click()
      },
      uploadSuccess () {
        this.$notify.success('部署成功')
        this.queryForPage()
      }
    },
    watch: {
      'param': {
        handler: function () {
          this.query()
        },
        deep: true
      }
    }
  }
</script>
