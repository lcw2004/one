<template>
  <section class="content">
    <div class="box">
      <div class="box-body">
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="编码类别">
              <select class="form-control" v-model="selectSettingId">
                <option v-for="(obj, index) of list" :value="obj.settingId">{{ obj.settingName }}</option>
              </select>
            </FormGroup>
          </div>
        </div>

        <div class="row" v-if="selectSettingId">
          <div class="col-md-12">
            <section class="content">
              <CodeSettingForm :codeSetting="codeSetting"></CodeSettingForm>
            </section>
          </div>
        </div>
      </div>

      <div class="box-footer">
        <div class="row">
          <div class="col-md-2 col-md-offset-2">
            <button type="button" class="btn btn-block btn-primary" @click="update()">
              保存
            </button>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
  import CodeSettingForm from './CodeSettingForm.vue'
  export default {
    components: {
      CodeSettingForm
    },
    data: () => {
      return {
        actions: {
          list: {method: 'get', url: '/api/base/codeSetting'},
          update: {method: 'put', url: '/api/base/codeSetting'},
          get: {method: 'get', url: '/api/base/codeSetting/{id}'}
        },
        list: [],
        selectSettingId: '',
        codeSetting: {},
        sectionList: [
          {
            orderIndex: 1,
            type: 5,
            content: '',
            length: 1
          },
          {
            orderIndex: 2,
            type: 4,
            content: '',
            length: 1
          },
          {
            orderIndex: 3,
            type: 6,
            content: '00001',
            length: 5
          }
        ]
      }
    },
    mounted () {
      this.resource = this.$resource(null, {}, this.actions)
      this.loadData()
    },
    methods: {
      loadData () {
        this.resource.list().then(function (response) {
          let result = response.body
          if (result.ok) {
            this.list = result.data
            this.selectSettingId = this.list[0].settingId
          }
        })
      },
      loadCodeSetting (codeSettingId) {
        this.resource.get({id: codeSettingId}).then(function (response) {
          let result = response.body
          if (result.ok) {
            let codeSetting = result.data
            if (codeSetting.sectionEOList.length === 0) {
              codeSetting.sectionEOList = JSON.parse(JSON.stringify(this.sectionList))
            }
            this.codeSetting = codeSetting
          }
        })
      },
      update () {
        this.resource.update(this.codeSetting).then(function (response) {
          let result = response.body
          if (result.ok) {
            this.codeSetting = result.data
            this.$notify.success('保存成功')
          }
        })
      }
    },
    watch: {
      'selectSettingId': function () {
        this.loadCodeSetting(this.selectSettingId)
      }
    }
  }
</script>
