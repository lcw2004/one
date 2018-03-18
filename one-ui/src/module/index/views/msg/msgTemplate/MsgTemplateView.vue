<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">系统消息模板详情</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="消息模板ID">{{ obj.templateId }}</FormGroupStatic>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="消息模板标题">{{ obj.title }}</FormGroupStatic>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="消息内容">{{ obj.content }}</FormGroupStatic>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="状态">{{ obj.status }}</FormGroupStatic>
            </div>
          </div>
        </form>
      </div>
      <div class="box-footer">
        <div class="row">
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
          get: this.$api.msg.getMsgTemplate,
          save: this.$api.msg.saveMsgTemplate,
          update: this.$api.msg.updateMsgTemplate
        }
      },
      obj: {
        templateId: '',
        title: '',
        content: '',
        status: 0
      }
    }
  }
}
</script>
