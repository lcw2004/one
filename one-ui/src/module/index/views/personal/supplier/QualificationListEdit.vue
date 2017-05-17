<template>
  <div class="row">
    <div class="col-md-12">
      <div class="pull-right" style="margin-bottom: 10px;">
        <button type="button" class="btn btn-primary" @click="qualificationTypeConfig.show = true">
          添加资质文件
        </button>
      </div>
    </div>

    <div class="col-md-12">
      <table class="table table-bordered table-hover">
        <tbody>
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
          <tr v-for="(q, index) of qualificationList">
            <td>{{ index + 1}}</td>
            <td>{{ q.type.qualificationName }}</td>
            <td>{{ q.issueAgency }}</td>
            <td>{{ q.issueDate }}</td>
            <td>{{ q.invalidDate }}</td>
            <td>
              <a>查看</a>
            </td>
            <td>{{ q.remark }}</td>
            <td>
              <a @click="q.editable = true">编辑</a>
              <a @click="deleteQualification(index)">删除</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <AddQualificationModal :config="qualificationTypeConfig" v-model="qualification"/>
  </div>
</template>

<script>
import AddQualificationModal from '../../user/supplier/info/AddQualificationModal'

export default {
  components: {
    AddQualificationModal
  },
  props: {
    qualificationList: {
      type: Array
    }
  },
  data: function () {
    return {
      qualificationTypeConfig: {
        title: '上传投标资质文件',
        show: true
      },
      qualification: {}
    }
  },
  methods: {
    deleteQualification (index) {
      this.qualificationList.splice(index, 1)
    },
    addQualification (qualification) {
      if (this.indexOf(qualification) === -1) {
        this.qualificationList.push(qualification)
      }
    },
    indexOf (qualification) {
      let index = -1
      for (var i = 0; i < this.qualificationList.length; i++) {
        let type = this.qualificationList[i]
        if (type.qualificationCode === qualification.qualificationCode) {
          index = i
          break
        }
      }
      return index
    }
  },
  watch: {
    'qualification': {
      handler: function () {
        this.qualification.qualificationCode = this.qualification.type.qualificationCode
        if (this.qualification.qualificationCode) {
          this.addQualification(this.purchaseType)
          this.qualification = {}
        }
      },
      deep: true
    }
  }
}
</script>
