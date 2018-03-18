<template>
  <div class="row">
    <div class="col-md-12">
      <div class="pull-right" style="margin-bottom: 10px;">
        <button type="button" class="btn btn-primary" @click="showAddQualificationModal">
          添加资质文件
        </button>
      </div>
    </div>

    <div class="col-md-12">
      <table class="table table-bordered table-hover">
        <thead>
          <tr>
            <th style="width: 10px">#</th>
            <th>资质类型</th>
            <th>颁发机构</th>
            <th>颁发日期</th>
            <th>有效日期</th>
            <th>影印件</th>
            <th>备注</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(q, index) of qualificationList">
            <td>{{ index + 1}}</td>
            <td>{{ q.type.qualificationName }}</td>
            <td>{{ q.issueAgency }}</td>
            <td>{{ q.issueDate }}</td>
            <td>
              {{ q.invalidDate }}
              <span class="label label-danger" v-if="q.isInvalid">已过期</span>
              <span class="label label-warning" v-if="!q.isInvalid && q.lastTime < 90">{{ q.lastTime }}天</span>
            </td>
            <td>
              <ImageView v-if="q.fileId" :src="'/api/sys/file/' + q.fileId + '/download'"></ImageView>
            </td>
            <td>
              <LongText :text="q.remark"></LongText>
            </td>
            <td>
              <a v-if="editable == 'true'" @click="showEditQualificationModal(q)">编辑</a>
              <a v-if="deletable == 'true'" @click="deleteQualification(index)">删除</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <AddQualificationModal @ok="handlerOk(arguments[0])" v-if="qualificationTypeConfig.show" :value="qualification"
                           :config="qualificationTypeConfig" :qualificationTypeList="qualificationTypeList"></AddQualificationModal>
  </div>
</template>

<script>
import AddQualificationModal from './AddQualificationModal'

export default {
  components: {
    AddQualificationModal
  },
  props: {
    type: {
      type: String
    },
    value: {
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
      qualificationTypeList: [],
      qualificationTypeConfig: {
        title: '上传资质文件',
        show: false
      },
      qualification: {},
      qualificationList1: []
    }
  },
  computed: {
    qualificationList: function () {
      return this.value
    },
    showDelete: function () {
      return this.deletable || (this.deletable === 'true')
    },
    showEdit: function () {
      return this.editable || (this.editable === 'true')
    }
  },
  mounted: function () {
    this.loadQualificationType()
    this.qualificationList = this.value
  },
  methods: {
    loadQualificationType () {
      this.$api.system.listQualificationType(this.type).then((response) => {
        let result = response.data
        if (result.ok && result.data) {
          this.qualificationTypeList = result.data
        }
      })
    },
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
      if (this.qualificationList == null) {
        this.qualificationList = []
      }
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
        this.$emit('input', this.qualificationList)
      }
    }
  }
}
</script>
