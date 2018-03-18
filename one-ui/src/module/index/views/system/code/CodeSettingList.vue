<template>
  <section class="content">
    <div class="box box-primary">
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
  data: function () {
    return {
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
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.system.listCodeSetting().then((response) => {
        let result = response.data
        if (result.ok) {
          this.list = result.data
          this.selectSettingId = this.list[0].settingId
        }
      })
    },
    loadCodeSetting (codeSettingId) {
      this.$api.system.getCodeSetting(codeSettingId).then((response) => {
        let result = response.data
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
      this.$api.system.updateCodeSetting(this.codeSetting).then((response) => {
        let result = response.data
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
