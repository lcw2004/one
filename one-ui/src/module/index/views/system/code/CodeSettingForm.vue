<template>
  <div class="box box-primary">
    <div class="box-header">
      <h3 class="box-title">编码规则配置</h3>
    </div>
    <div class="box-body">
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
  }
}
</script>
