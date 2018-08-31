<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">单据编号设置</h3>
      </div>
      <div class="box-body">
        <div class="row">
          <div class="col-md-3">
            <div class="nav-tabs-custom">
              <ul class="nav nav-pills nav-stacked">
                <template v-for="(obj, index) of list">
                  <li :class="{'active': selectSettingId == obj.settingId}" @click="selectSettingId = obj.settingId"><a>{{ obj.settingName }}</a></li>
                </template>
              </ul>
            </div>
          </div>
          <div class="col-md-9">
            <CodeSettingForm :codeSetting="codeSetting"></CodeSettingForm>
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
    }
  },
  watch: {
    'selectSettingId': function () {
      this.loadCodeSetting(this.selectSettingId)
    }
  }
}
</script>
