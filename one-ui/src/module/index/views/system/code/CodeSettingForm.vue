<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <template v-for="section of codeSetting.sectionEOList">
          <CodeSettingSection :section="section"></CodeSettingSection>
        </template>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <CodeSettingExample :codeSetting="codeSetting"></CodeSettingExample>
      </div>
    </div>

    <div class="row">
      <div class="col-md-2 margin-20">
        <button type="button" class="btn btn-block btn-primary" @click="update()">
          保存
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import CodeSettingSection from './CodeSettingSection.vue'
import CodeSettingExample from './CodeSettingExample.vue'

export default {
  components: {
    CodeSettingSection,
    CodeSettingExample
  },
  props: {
    codeSetting: {
      type: Object,
      require: true
    }
  },
  methods: {
    update () {
      this.$api.system.updateCodeSetting(this.codeSetting).then((response) => {
        let result = response.data
        if (result.ok) {
          this.codeSetting = result.data
          this.$notify.success('保存成功')
        }
      })
    }
  }
}
</script>
