<template>
<div class="modal" v-show="config.show" style="display: block">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="config.show = false">
          <span aria-hidden="true">×</span>
        </button>
        <h4 class="modal-title" v-text="config.title"></h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-6">
              <div class="form-group">
                <label class="control-label col-md-4">资质文件类型</label>
                <div class="col-md-8">
                    <select class="form-control" v-model="qualification.type">
                      <option v-for="type of qualificationTypeList" :value="type">{{ type.qualificationName }}</option>
                    </select>
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <label class="control-label col-md-4">颁发机构</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" v-model="qualification.issueAgency">
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <label class="control-label col-md-4">颁发日期</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" v-model="qualification.issueDate">
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <label class="control-label col-md-4">失效日期</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" v-model="qualification.invalidDate">
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <label class="control-label col-md-4">影印件</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" v-model="qualification.fileId">
                </div>
              </div>
            </div>
            <div class="col-md-12">
              <div class="form-group">
                <label class="control-label col-md-2">备注</label>
                <div class="col-md-10">
                  <textarea class="form-control" rows="3" v-model="qualification.remark"></textarea>
                </div>
              </div>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default " data-dismiss="modal" @click="config.show = false">取消</button>
        <button type="button" class="btn btn-primary" @click="ok()">确认</button>
      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  props: {
    config: {
      type: Object,
      required: true
    },
    value: {
      type: Object,
      required: true
    }
  },
  computed: {
    qualification: function () {
      return this.value
    }
  },
  data: function () {
    return {
      actions: {
        getQualificationType: { method: 'get', url: '/api/qualificationType?userType=3' }
      },
      qualificationTypeList: []
    }
  },
  mounted: function () {
    this.resource = this.$resource(null, {}, this.actions)
    this.loadQualificationType()
  },
  methods: {
    loadQualificationType () {
      this.resource.getQualificationType().then(function (response) {
        var result = response.body
        if (result.ok && result.data) {
          this.qualificationTypeList = result.data
        }
      })
    },
    ok () {
      this.config.show = false
      this.$emit('input', this.qualification)
    }
  }
}
</script>
