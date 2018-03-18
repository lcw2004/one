<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">消息信息</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="消息ID" >
                <input type="text" class="form-control" v-model="obj.messageId" v-validate="'required'" name="消息ID" />
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="消息模板ID" >
                <input type="text" class="form-control" v-model="obj.templateId" v-validate="'required'" name="消息模板ID" />
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="消息标题" >
                <input type="text" class="form-control" v-model="obj.title"/>
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="消息内容" >
                <input type="text" class="form-control" v-model="obj.content"/>
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="发送时间" >
                <TimePicker v-model="obj.sendTime" ></TimePicker>
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="目标用户类型" >
                <input type="text" class="form-control" v-model="obj.targetType"/>
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

export default {
  mixins: [FormMixin],
  data: function () {
    return {
      form: {
        actions: {
          get: this.$api.msg.getMsgMessage,
          save: this.$api.msg.saveMsgMessage,
          update: this.$api.msg.updateMsgMessage
        }
      },
      obj: {
        messageId: '',
        templateId: '',
        title: '',
        content: '',
        sendTime: '',
        targetType: 0
      }
    }
  },
  mounted () {
  },
  methods: {
  }
}
</script>
