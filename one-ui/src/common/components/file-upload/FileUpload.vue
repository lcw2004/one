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
  <div>
    <input type="file" style="display: none" :id="id" @change="uploadFile($event)">

    <div class="row">
      <div class="col-md-12">
        <button type="button" :class="btnClass" @click="selectFile">
          <i class="fa fa-upload"></i> {{ btnText }}
        </button>
        <QuestionTooltip>
          <div style="text-align: left">
            <template v-if="type">文件类型：{{ type }}</template><br>
            文件大小：不超过{{ maxFileSizeShow }}
          </div>
        </QuestionTooltip>
      </div>
    </div>

    <template v-if="fileName && showProgress">
      <div class="row file-detail">
        <div class="col-md-12">
          <span class="file-detail-name"><i class="fa fa-file-o"></i> {{ fileName }}</span>
          <span class="pull-right" v-if="!isSuccess">{{ progressWidth }}%</span>
          <span class="pull-right file-detail-success" v-if="isSuccess"><i class="fa fa-fw fa-check-circle"></i> 上传成功</span>
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
  export default {
    name: 'FileUpload',
    components: {},
    props: {
      url: {
        type: String,
        default: '/api/sys/file/upload'
      },
      type: {
        type: String
      },
      showProgress: {
        type: Boolean,
        default: true
      },
      btnText: {
        type: String,
        default: '上传文件'
      },
      btnClass: {
        type: String,
        default: 'btn btn-primary'
      },
      callback: {
        type: Function
      },
      errorCallback: {
        type: Function
      }
    },
    data: () => {
      return {
        id: '' + Math.random(),
        progressWidth: 0,
        isSuccess: false,
        fileName: ''
      }
    },
    computed: {
      maxFileSize: function () {
        return this.$store.state.setting.maxFileSize
      },
      maxFileSizeShow: function () {
        return this.$store.state.setting.maxFileSizeShow
      },
      userInfo: function () {
        return this.$store.state.system.userInfo
      },
      userId: function () {
        let userId = ''
        if (this.userInfo) {
          userId = this.userInfo.userId
        }
        return userId
      },
      progressStyle: function () {
        return {
          width: this.progressWidth + '%'
        }
      },
      typeList: function () {

      }
    },
    methods: {
      /**
       * 选择文件
       */
      selectFile () {
        document.getElementById(this.id).click()
        this.progressWidth = 0
      },

      /**
       * 文件路径变更
       * @param event
       */
      uploadFile (event) {
        let file = event.target.files[0]
        if (file && this.validFile(file)) {
          this.startUploadFile(event, file)
        }
      },

      /**
       * 上传文件的实际动作
       */
      startUploadFile (event, file) {
        this.fileName = file.name

        // form data
        const formData = new FormData()
        formData.append('file', file)
        formData.append('userId', this.userId)

        // upload file
        let self = this
        this.$http.post(this.url, formData, {
          progress (event) {
            self.progressWidth = parseInt(event.loaded / event.total * 100)
          }
        }).then(response => {
          let result = response.body
          if (result.ok) {
            this.isSuccess = true
            if (this.callback) {
              this.callback(result.data)
            }
          }
          event.target.value = null
        }, response => {
          this.$notify.warn('上传文件失败，请重试！')
          self.progressWidth = 0
          event.target.value = null
        })
      },

      /**
       * 验证文件
       * 1. 验证文件是否是允许的类型
       * 2. 验证文件长度是否超过限制
       * @param file 文件
       * @returns {boolean} true - 验证通过 false - 验证不通过
       */
      validFile (file) {
        // 验证文件类型
        if (!this.isTargetType(file.name)) {
          this.handlerError('请选择类型为[' + this.type + ']的文件')
          return false
        }

        // 验证长度
        if (file.size >= this.maxFileSize) {
          this.handlerError('超过允许上传的文件长度限制[' + this.maxFileSizeShow + ']')
          return false
        }

        return true
      },

      /**
       * 判断文件名是否是指定的类型
       * @param fileName 文件名称
       * @returns {boolean} true - 是允许的类型，false - 不是允许的类型
       */
      isTargetType (fileName) {
        if (!this.type) {
          return true
        }

        let fileType = fileName.substr(fileName.lastIndexOf('.') + 1).toLowerCase()
        let typeList = this.type.toLowerCase().split(',')
        return typeList.indexOf(fileType) >= 0
      },

      /**
       * 处理验证失败
       * @param msg
       */
      handlerError (msg) {
        this.$notify.warn(msg)
        if (this.errorCallback) {
          this.errorCallback(msg)
        }
      }
    }
  }
</script>

<style>
  .file-detail {
    margin-top: 10px;
  }

  .file-detail .file-detail-name {
    font-size: 13px;
    color: #777;
  }

  .file-detail .file-detail-success {
    color: #00a65a;
  }

</style>
