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
                <img :src="processImageUrl" @click="$preview.openImg(processImageUrl)"></img>
              </div>
            </div>

            <div class="col-md-12">
              <hr class="divider">
            </div>

            <div class="col-md-10 col-md-offset-2">
              <form class="form-horizontal">
                <div class="row">
                  <div class="col-md-12">
                    <FormGroupStatic label="流程ID" width="2">{{ obj.processKey }}</FormGroupStatic>
                  </div>
                  <div class="col-md-12">
                    <FormGroupStatic label="流程名称" width="2">{{ obj.processName }}</FormGroupStatic>
                  </div>
                  <div class="col-md-12">
                    <FormGroup label="流程表单" width="2" inputWidth="4">
                      <input type="text" class="form-control" v-model="obj.bindViewForm" maxlength="100" v-validate="'required'" name="流程表单">
                    </FormGroup>
                  </div>
                </div>
              </form>
            </div>

            <template v-for="taskInfo of obj.flowTaskList">
                <div class="col-md-10 col-md-offset-2">
                  <h4>{{ taskInfo.taskName }}</h4>
                </div>
                <div class="col-md-10 col-md-offset-2">
                  <FormGroup label="审核对象" width="2">
                    <div class="row">
                      <div class="col-md-4">
                        <DictElSelect v-model.number="taskInfo.bindType" type="workflow_bind_type"></DictElSelect>
                      </div>
                    </div>
                    <WorkFlowTaskBind :taskInfo="taskInfo" @change="validTaskInfo(taskInfo)"></WorkFlowTaskBind>
                    <div class="row">
                      <div class="col-md-12">
                        <p class="text-red has-error" v-if="errors.has(taskInfo.taskName)">{{ errors.first(taskInfo.taskName) }}</p>
                      </div>
                    </div>
                  </FormGroup>
                  <FormGroup label="操作表单" width="2">
                    <div class="row">
                      <div class="col-md-4">
                        <input type="text" class="form-control" v-model="taskInfo.bindOperationForm" maxlength="100">
                      </div>
                    </div>
                  </FormGroup>
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
  </section>
</template>

<script>
import FormMixin from '@mixins/FormMixin'
import { isEmpty } from '@utils/common'
import WorkFlowTaskBind from './WorkFlowTaskBind.vue'

export default {
  mixins: [FormMixin],
  components: {
    WorkFlowTaskBind
  },
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
        processKey: null,
        bindViewForm: null,
        processDefinitionId: null,
        processName: null,
        flowTaskInfoEOList: []
      }
    }
  },
  computed: {
    processImageUrl: function () {
      return '/api/flow/flowInfo/' + this.id + '/processImage?t=' + Math.random()
    }
  },
  mounted () {
    this.$store.dispatch('initRoleList')
  },
  methods: {
    validTaskInfo (taskInfo) {
      this.errors.remove(taskInfo.taskName)
      if (taskInfo.bindType === 2) {
        if (isEmpty(taskInfo.bindRoleId)) {
          this.errors.add(taskInfo.taskName, `请选择节点[${taskInfo.taskName}]的审核角色`)
        }
      } else if (taskInfo.bindType === 3) {
        if (taskInfo.bindOffice == null) {
          this.errors.add(taskInfo.taskName, `请选择节点[${taskInfo.taskName}]的审核部门`)
        }
      } else if (taskInfo.bindType === 4) {
        if (taskInfo.bindUser == null) {
          this.errors.add(taskInfo.taskName, `请选择节点[${taskInfo.taskName}]的审核人`)
        }
      } else if (taskInfo.bindType === 5) {
        if (taskInfo.bindOffice == null) {
          this.errors.add(taskInfo.taskName, `请选择节点[${taskInfo.taskName}]的审核部门`)
        }
        if (isEmpty(taskInfo.bindRoleId)) {
          this.errors.add(taskInfo.taskName, `请选择节点[${taskInfo.taskName}]的审核角色`)
        }
      } else if (taskInfo.bindType === 8) {
        if (isEmpty(taskInfo.bindRoleId)) {
          this.errors.add(taskInfo.taskName, `请选择节点[${taskInfo.taskName}]的审核角色`)
        }
      } else if (taskInfo.bindType === 9) {
        if (taskInfo.bindService == null) {
          this.errors.add(taskInfo.taskName, `请选择节点[${taskInfo.taskName}]的服务`)
        }
      }
    },
    valid () {
      for (let taskInfo of this.obj.flowTaskList) {
        this.validTaskInfo(taskInfo)
      }
      return this.errors.count() === 0
    },
    validAndSave () {
      this.valid()
      this.$validator.validateAll().then((result) => {
        if (result && this.valid()) {
          if (this.$route.params.id) {
            this.update()
          } else {
            this.save()
          }
        } else {
          this.scrollToError()
        }
      })
    }
  }
}
</script>
