<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">公告信息</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="标题" >
                <input type="text" class="form-control" v-model="obj.title" maxlength="50" v-validate="'required'" name="标题" />
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <FormGroup label="读者范围" :width="2">
                <UserGroupType :type="obj.targetType" :value="targetValue" @type-change="handlerTypeChange(arguments[0])" @value-change="handlerValueChage(arguments[0])"></UserGroupType>
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <FormGroup label="公告内容" width="2" inputWidth="9">
                <InlineEditor v-model="obj.content" v-validate="'required'" data-vv-value-path="innerValue" data-vv-name="公告内容"></InlineEditor>
              </FormGroup>
            </div>
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
import UserGroupType from './UserGroupType.vue'

export default {
  mixins: [FormMixin],
  components: {
    UserGroupType
  },
  data: function () {
    return {
      form: {
        actions: {
          get: this.$api.system.getMsgAnnounce,
          save: this.$api.system.saveMsgAnnounce,
          update: this.$api.system.updateMsgAnnounce
        }
      },
      obj: {
        title: '',
        content: '',
        targetType: 1,
        target: ''
      }
    }
  },
  computed: {
    targetValue: function () {
      let value = []
      if (this.obj.target) {
        value = JSON.parse(this.obj.target)
      }
      return value
    }
  },
  methods: {
    valid () {
      this.errors.remove('读者范围')
      if (this.obj.targetType === 2 && this.targetValue.length === 0) {
        this.errors.add('读者范围', '部门不能为空')
        return false
      } else if (this.obj.targetType === 3 && this.targetValue.length === 0) {
        this.errors.add('读者范围', '角色不能为空')
        return false
      }
      return true
    },
    publish () {
      this.valid()
      this.$validator.validateAll().then((result) => {
        if (result && this.valid()) {
          this.$api.system.publishMsgAnnounce().then((response) => {
            let result = response.data
            if (result.ok) {
              this.$notify.success('发布成功')
            }
          })
        } else {
          this.scrollToError()
        }
      })
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
    },
    handlerTypeChange (type) {
      this.obj.targetType = type
    },
    handlerValueChage (value) {
      this.obj.target = JSON.stringify(value)
    }
  },
  watch: {
    'obj.target': {
      handler: function () {
        this.valid()
      },
      deep: true
    }
  }
}
</script>
