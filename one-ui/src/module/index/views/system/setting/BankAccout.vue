<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header with-border">
        <h3 class="box-title">银行账户配置</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="row">
            <div class="col-md-12">
              <h4 class="page-header">标书费账户</h4>
            </div>
          </div>
          <div class="row">
            <div class="col-md-4">
              <FormGroup label="开户银行">
                <input type="text" class="form-control" v-model="configs.bid_bank_name">
              </FormGroup>
            </div>
            <div class="col-md-4">
              <FormGroup label="银行账户">
                <input type="text" class="form-control" v-model="configs.bid_bank_account">
              </FormGroup>
            </div>
            <div class="col-md-4">
              <FormGroup label="开户名">
                <input type="text" class="form-control" v-model="configs.bid_bank_user_name">
              </FormGroup>
            </div>
          </div>
  
          <div class="row">
            <div class="col-md-12">
              <h4 class="page-header">投标保证金账户</h4>
            </div>
          </div>
          <div class="row">
            <div class="col-md-4">
              <FormGroup label="开户银行">
                <input type="text" class="form-control" v-model="configs.bid_promise_bank_name">
              </FormGroup>
            </div>
            <div class="col-md-4">
              <FormGroup label="银行账户">
                <input type="text" class="form-control" v-model="configs.bid_promise_bank_account">
              </FormGroup>
            </div>
            <div class="col-md-4">
              <FormGroup label="开户名">
                <input type="text" class="form-control" v-model="configs.bid_promise_bank_user_name">
              </FormGroup>
            </div>
          </div>
        </form>
      </div>
      <div class="box-footer">
        <div class="row">
          <div class="col-md-2 col-md-offset-2">
            <button type="button" class="btn btn-primary btn-block" @click="saveData()">
              保存
            </button>
          </div>
        </div>
      </div>
    </div>
  
  </section>
</template>

<script>
export default {
  components: {},
  data: function () {
    return {
      configType: '001',
      configs: {}
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.system.getConfigByType(this.configType).then((response) => {
        let result = response.data
        if (result.ok) {
          this.configs = result.data
        }
      })
    },
    saveData () {
      this.$api.system.updateConfigByType(this.configType, this.configs).then((response) => {
        let result = response.data
        if (result.ok) {
          this.$notify.success('保存成功')
        }
      })
    }
  }
}
</script>
