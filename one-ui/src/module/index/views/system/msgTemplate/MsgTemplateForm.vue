<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">修改消息模板</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="模板编号" :required="true">
                <input type="text" class="form-control" v-model="obj.templateId" v-validate="'required'" name="模板编号" readonly/>
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="消息类型" :required="true">
                <select class="form-control" v-model="obj.msgTypeCode" v-validate="'required'" name="消息类型">
                  <option v-for="msgType of msgTypeList" :value="msgType.msgTypeCode" :key="msgType.msgTypeCode">{{ msgType.msgTypeName }}</option>
                </select>
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="模板标题" :required="true">
                <input type="text" class="form-control" v-model="obj.title" v-validate="'required'" name="模板标题" maxlength="50"/>
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <FormGroup label="消息内容" :required="true">
                <textarea v-model="obj.content" class="form-control" rows="4" maxlength="200"  v-validate="'required'" name="消息内容" ></textarea>
              </FormGroup>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <FormGroup label="发送渠道" :width="2" :required="true">
                <div>
                  <div class="checkbox-inline" v-for="channel of msgChannelList">
                    <label>
                      <input type="checkbox" :value="channel.channelId" v-model="obj.channelIdList" v-validate="'required'" name="发送渠道"> {{ channel.channelName }}
                    </label>
                  </div>
                </div>
                <div>
                  <p class="text-yellow">请控制邮件的发送数量，以防邮件服务器退信！</p>
                </div>
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
import MsgTemplateFormMixin from './MsgTemplateFormMixin.js'

export default {
  mixins: [FormMixin, MsgTemplateFormMixin]
}
</script>
