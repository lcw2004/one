<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">审核</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-11">
              <FormGroup label="是否审核通过" width="3" inputWidth="8">
                <div>
                  <div class="radio-inline">
                    <label><input type="radio" :value="true" v-model="auditResult">通过</label>
                  </div>
                  <div class="radio-inline">
                    <label><input type="radio" :value="false" v-model="auditResult">驳回</label>
                  </div>
                </div>
              </FormGroup>
            </div>
            <div class="col-md-11">
              <FormGroup label="审核意见" width="3" inputWidth="8">
                <textarea class="form-control" rows="5" v-model="auditRemark" maxlength="500"></textarea>
              </FormGroup>
            </div>
          </div>
        </form>
      </div>
      <div class="box-footer">
        <div class="pull-right">
          <a class="btn btn-primary" @click="audit()" v-if="auditResult">通过</a>
          <a class="btn btn-warning" @click="audit()" v-if="!auditResult">驳回</a>
          <a class="btn btn-default" @click="$router.go(-1)">返回</a>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import AuditMixin from '../AuditMixin'
import AuditDetail from '../AuditDetail.vue'

export default {
  mixins: [AuditMixin],
  components: {
    AuditDetail
  },
  data: function () {
    return {
      auditResult: true,
      auditRemark: ''
    }
  }
}
</script>
