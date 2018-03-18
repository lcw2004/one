import AddQualificationModal from './AddQualificationModal'

export default {
  components: {
    AddQualificationModal
  },

  props: {
    qualificationList: {
      type: Array
    },
    deletable: {
      type: String,
      default: true
    },
    editable: {
      type: String,
      default: true
    }
  },

  data: function () {
    return {
      qualificationTypeConfig: {
        title: '上传投标资质文件',
        show: false
      },
      qualification: {}
    }
  },

  computed: {
    showDelete: function () {
      return this.deletable || (this.deletable === 'true')
    },
    showEdit: function () {
      return this.editable || (this.editable === 'true')
    }
  },

  methods: {
    showAddQualificationModal () {
      let qualification = {
        fileId: '',
        invalidDate: '',
        issueAgency: '',
        issueDate: '',
        remark: '',
        type: null
      }
      this.qualification = qualification
      this.qualificationTypeConfig.show = true
    },
    showEditQualificationModal (obj) {
      this.qualification = JSON.parse(JSON.stringify(obj))
      this.qualificationTypeConfig.show = true
    },
    deleteQualification (index) {
      this.qualificationList.splice(index, 1)
    },
    addOrUpdateQualification (qualification) {
      let index = this.indexOf(qualification)
      if (index === -1) {
        this.qualificationList.push(qualification)
      } else {
        this.qualificationList[index] = qualification
      }
    },
    indexOf (qualification) {
      let index = -1
      for (let i = 0; i < this.qualificationList.length; i++) {
        let type = this.qualificationList[i]
        if (type.qualificationCode === qualification.qualificationCode) {
          index = i
          break
        }
      }
      return index
    },
    handlerOk (qualification) {
      qualification.qualificationCode = qualification.type.qualificationCode
      if (qualification.qualificationCode) {
        this.addOrUpdateQualification(qualification)
      }
    }
  }
}
