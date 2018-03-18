import UploadMixin from './UploadMixin.js'
export default {
  mixins: [UploadMixin],

  props: {
    value: {
      type: Array,
      require: true
    }
  },

  data: function () {
    return {
      fileList: []
    }
  },

  computed: {
    fileIdList: function () {
      return this.value
    }
  },

  methods: {
    onStartUpload (event, file) {
      if (file && this.validFile(file)) {
        this.startUploadMultFile(event, file)
      }
    },

    /**
     * 上传文件的实际动作
     */
    startUploadMultFile (event, file) {
      let picture = {
        fileId: '',
        fileName: file.name,
        progressWidth: 0
      }

      // form data
      const formData = new FormData()
      formData.append('file', file)
      formData.append('userId', this.userId)

      // upload file
      this.$axios.post(this.url, formData, {
        onUploadProgress: function (event) {
          picture.progressWidth = parseInt(event.loaded / event.total * 100)
        }
      }).then(response => {
        let result = response.data
        if (result.ok) {
          picture.progressWidth = 100
          picture.fileId = result.data.fileId
          this.fileIdList.push(picture.fileId)
        }
        event.target.value = null
      }, response => {
        this.$notify.warn('上传文件失败，请重试！')
        picture.progressWidth = 0
        event.target.value = null
      })

      this.fileList.push(picture)
    },

    remove (index) {
      this.fileList.splice(index, 1)
      this.fileIdList.splice(index, 1)
    }
  },
  watch: {
    'fileIdList': {
      handler: function () {
        this.$emit('input', this.fileIdList)
      },
      deep: true
    }
  }
}
