<!--
文件上传控件，上传到文件服务器，并返回在文件服务器中的ID
注：文件上传需要传入用户ID（后续需要改成token），验证不通过则不允许上传
TODO 将用户ID改成token

输入：
  url: 上传的URL地址，默认为文件服务器的上传地址
  type: 可接受文件类型，多个类型以逗号隔开，如果不限制则不传该值
  size: 可接受文件长度，如果未传入，则默认以系统配置的长度为限制（未实现）
  callback: 成功之后的回调事件，参数是fileObj对象
  errorCallback: 失败之后的回调事件，参数是失败原因
  btnText: 按钮上的文字
  btnClass: 按钮的样式
  showProgress: 是否显示进度条

输出：
  input: 接受文件ID作为参数
  success: 接受文件对象作为参数

示例：
  <FileUpload :callback="uploadSuccess" :errorCallback="uploadError"></FileUpload>
  uploadSuccess (fileObj) {
    this.fileObj.fileId = fileObj.fileId
    this.$validator.validateAll({'文件': fileObj.fileId})
  },
  uploadError (error) {
    this.errors.remove('文件') // 移除前一次的错误防止错误覆盖看不到
    this.errors.add('文件', error, 'maxFileSize')
  }
-->

<template>
  <div style="display: inline-block">
    <input type="file" style="display: none" :id="id" @change="uploadFile($event)">

    <!--上传按钮-->
    <template v-if="!isSlot">
      <button type="button" :class="btnClass" @click="selectFile">
        <i class="fa fa-upload"></i> {{ btnText }}
      </button>
      <QuestionTooltip>
        <div style="text-align: left">
          <template v-if="type">文件类型：{{ type }}<br></template>
          文件大小：不超过{{ maxFileSizeShow }}
        </div>
      </QuestionTooltip>
    </template>

    <!--上传Slot-->
    <span v-if="isSlot" @click="selectFile">
      <slot></slot>
    </span>

    <!-- 上传进度 -->
    <template v-if="fileName && showProgress">
      <div class="row file-detail">
        <div class="col-md-12">
          <span class="file-detail-name"><i class="fa fa-file-o"></i> {{ fileName }}</span>
          <span class="pull-right" v-if="!isSuccess">{{ progressWidth }}%</span>

          <span class="pull-right" v-if="isSuccess">
            <span class="file-detail-success">
              <i class="fa fa-fw fa-check-circle"></i> 上传成功
            </span>
          	<i class="file-detail-name fa fa-fw fa-close" title="删除" @click="clear()"></i>
          </span>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <div class="progress progress-xxs">
            <div class="progress-bar progress-bar-primary" :style="progressStyle">
              <span class="sr-only">60% Complete (warning)</span>
            </div>
          </div>
        </div>
      </div>
    </template>
  </div>
</template>

<script>
import UploadMixin from './UploadMixin.js'

export default {
  name: 'FileUpload',
  mixins: [UploadMixin],
  methods: {
    /**
     * 文件路径变更
     * @param event
     */
    uploadFile (event) {
      let file = event.target.files[0]
      if (file && this.validFile(file)) {
        this.startUploadFile(event, file)
      }
    }
  }
}
</script>

<style lang="less" type="text/less">
 .file-detail {
   margin-top: 10px;

   .file-detail-name {
     font-size: 13px;
     color: #777;
     cursor: pointer;

     &:hover {
       color: #72afd2;
     }
   }

   .file-detail-success {
     color: #00a65a;
   }
 }
</style>
