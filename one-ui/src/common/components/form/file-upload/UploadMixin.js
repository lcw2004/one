import { uuid } from '@utils/common.js'
export default {
  props: {
    url: {
      type: String,
      default: '/api/sys/file/upload'
    },
    type: {
      type: String
    },
    size: {
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

  data: function () {
    return {
      id: uuid(),
      progressWidth: 0,
      isSuccess: false,
      fileName: ''
    }
  },

  computed: {
    maxFileSize: function () {
      return this.$store.state.system.settings.maxFileSize
    },
    maxFileSizeShow: function () {
      if (this.size) {
        return this.size
      } else {
        return this.$store.state.system.settings.maxFileSizeShow
      }
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
      this.$axios.post(this.url, formData, {
        onUploadProgress: function (event) {
          self.progressWidth = parseInt(event.loaded / event.total * 100)
        }
      }).then(response => {
        let result = response.data
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
    },

    clear () {
      this.fileName = ''
      this.callback({fileId: ''})
    }
  }
}
