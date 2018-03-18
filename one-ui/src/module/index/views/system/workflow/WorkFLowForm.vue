<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h4>{{ obj.processName }}</h4>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-12">
              <div class="process-img">
                <img :src="processImageUrl"></img>
              </div>
            </div>

            <div class="col-md-12">
              <hr class="divider">
            </div>

            <template v-for="taskInfo of obj.flowTaskInfoEOList">
              <div class="row">
                <div class="col-md-3">
                  <div class="pull-right">
                    <h4>{{ taskInfo.taskName }}</h4>
                  </div>
                </div>
                <div class="col-md-6">
                  <FormGroup label="审核角色">
                    <select class="form-control" v-model="taskInfo.bindRole">
                      <option value="">请选择该节点审核角色</option>
                      <option v-for="role of roleList" :value="role.id">{{ role.name }}</option>
                    </select>
                  </FormGroup>
                  <FormGroup label="审核表单链接">
                    <input type="text" class="form-control" v-model="taskInfo.bindForm" maxlength="100">
                  </FormGroup>
                </div>
              </div>
            </template>
          </div>
        </form>
      </div>
      <div class="box-footer">
        <div class="row">
          <div class="col-md-2 col-md-offset-2">
            <a class="btn btn-block btn-primary" @click="validAndSave()">保存</a>
          </div>
          <div class="col-md-2">
            <a class="btn btn-block btn-default" @click="$router.go(-1)">返回</a>
          </div>
        </div>
      </div>
    </div>
    </div>
  </section>
</template>

<script>
import FormMixin from '@mixins/FormMixin'

export default {
  mixins: [FormMixin],
  data: function () {
    return {
      form: {
        actions: {
          get: this.$api.system.getWorkflow,
          save: this.$api.system.saveWorkflow,
          update: this.$api.system.updateWorkflow
        },
        continue: false
      },
      obj: {
        'processKey': '',
        'processState': 0,
        'lastUpdateTime': '',
        'processDefinitionId': '',
        'processName': '',
        'flowTaskInfoEOList': []
      },

      roleList: []
    }
  },
  computed: {
    processImageUrl: function () {
      return '/api/flow/flowInfo/' + this.id + '/processImage?t=' + Math.random()
    }
  },
  mounted () {
    this.listRole()
  },
  methods: {
    listRole () {
      this.$api.system.listRole({pageNo: 1, pageSize: 1000}).then((response) => {
        let result = response.data
        if (result.ok) {
          this.roleList = result.data.list
        }
      })
    },
    validate () {
      for (let taskInfo of this.obj.flowTaskInfoEOList) {
        if (!taskInfo.bindRole || taskInfo.bindRole === '') {
          this.$notify.warn('请选择节点【' + taskInfo.taskName + '】的审核角色')
          return false
        }

        if (!taskInfo.bindForm || taskInfo.bindForm === '') {
          this.$notify.warn('请选择节点【' + taskInfo.taskName + '】的审核表单链接')
          return false
        }
      }
      return true
    }
  }
}
</script>

<style>
.process-img {
  padding-top: 50px;
  padding-bottom: 50px;
  background-color: #ffffff;
  text-align: center;
  border: 1px dashed #3c8dbc;
}
</style>
