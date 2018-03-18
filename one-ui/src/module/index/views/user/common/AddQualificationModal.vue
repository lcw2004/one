<template>
  <OneTransition>
    <div :class="fullScreenClass" v-show="config.show" style="display: block">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="close()">
              <span aria-hidden="true">×</span>
            </button>
            <h4 class="modal-title">{{ config.title }}</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="row">
                <div class="col-md-6">
                  <FormGroup label="资质文件类型">
                    <select class="form-control" v-model="qualification.type" v-validate="'required'" data-vv-name="资质文件类型">
                      <option v-for="type of qualificationTypeList" :value="type">{{ type.qualificationName }}</option>
                    </select>
                  </FormGroup>
                </div>
                <div class="col-md-6">
                  <FormGroup label="颁发机构">
                    <input type="text" class="form-control" v-model="qualification.issueAgency" v-validate="'required'" name="颁发机构">
                  </FormGroup>
                </div>
                <div class="col-md-6">
                  <FormGroup label="颁发日期">
                    <DatePicker v-model="qualification.issueDate" :disabledDate="afterToday" v-validate="'required'"
                                data-vv-value-path="qualification.issueDate" data-vv-name="颁发日期"></DatePicker>
                  </FormGroup>
                </div>
                <div class="col-md-6">
                  <FormGroup label="失效日期">
                    <DatePicker v-model="qualification.invalidDate" :disabledDate="beforeToday" v-validate="'required'"
                                data-vv-value-path="qualification.invalidDate" data-vv-name="失效日期"></DatePicker>
                  </FormGroup>
                </div>
                <div class="col-md-6">
                  <FormGroup label="影印件">
                    <FileUpload type="bmp,jpg,jpeg,png" :callback="uploadSuccess" :errorCallback="uploadError"></FileUpload>
                  </FormGroup>
                </div>
                <div class="col-md-12">
                  <FormGroup label="备注" width="2">
                    <textarea class="form-control" rows="3" v-model="qualification.remark" maxlength="200"></textarea>
                  </FormGroup>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default " data-dismiss="modal" @click="close()">取消</button>
            <button type="button" class="btn btn-primary" @click="ok()">确认</button>
          </div>
        </div>
      </div>
    </div>
  </OneTransition>
</template>


<script>
import * as dateUtil from '@utils/date-util'
import moment from 'moment'
import ModalMixin from '@mixins/ModalMixin'

export default {
  mixins: [ModalMixin],
  props: {
    value: {
      type: Object,
      required: true
    },
    qualificationTypeList: {
      type: Array,
      required: true
    }
  },
  computed: {
    qualification: function () {
      return this.value
    }
  },
  data: function () {
    return {}
  },
  mounted: function () {
    this.$validator.attach('影印件', 'required')
  },
  methods: {
    ok () {
      this.$validator.validateAll({
        '资质文件类型': this.qualification.type,
        '颁发机构': this.qualification.issueAgency,
        '颁发日期': this.qualification.issueDate,
        '失效日期': this.qualification.invalidDate,
        '影印件': this.qualification.fileId
      }).then(() => {
        this.$emit('ok', this.qualification)
        this.close()
      })
    },
    uploadSuccess (fileObj) {
      this.qualification.fileId = fileObj.fileId
      this.errors.remove('影印件')
      this.$validator.validateAll({'影印件': this.qualification.fileId})
    },
    uploadError (error) {
      this.errors.remove('影印件')
      this.errors.add('影印件', error, 'maxFileSize')
    },
    beforeToday (time) {
      return dateUtil.isBefore(moment(time), this.systemTime)
    },
    afterToday (time) {
      return dateUtil.isAfter(moment(time), this.systemTime)
    }
  }
}
</script>
