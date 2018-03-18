<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header with-border">
        <div class="col-md-12">
          <div class="puastep_tab">
            <template v-for="(step, index) of steps">
              <a :class="{puastep_tab_now : currentStep == index}">{{ index + 1 }}.{{ step.step }}</a>
              <span v-if="index < steps.length - 1">&gt;</span>
            </template>
          </div>
        </div>
      </div>
      <form class="form-horizontal">
        <component :is="steps[currentStep].component" @last="lastStep" @next="nextStep" v-model="strategy" :strategy="strategy"></component>
      </form>
    </div>
  </section>
</template>

<script>
import CodeGenerateStep1 from './CodeGenerateStep1.vue'
import CodeGenerateStep2 from './CodeGenerateStep2.vue'

export default {
  components: {
    CodeGenerateStep1,
    CodeGenerateStep2
  },
  data: function () {
    return {
      steps: [
        {step: '选择表', component: 'CodeGenerateStep1'},
        {step: '配置策略', component: 'CodeGenerateStep2'}
      ],
      currentStep: 0,
      strategy: {
        moduleType: 1,
        isOverWrite: 0,
        moduleName: '',
        moduleSimpleName: '',
        modulePackage: '',
        entityPrefix: '',
        entitySuffix: 'EO',
        tableList: []
      }
    }
  },
  methods: {
    lastStep: function () {
      if (this.currentStep > 0) {
        this.currentStep = this.currentStep - 1
      }
    },
    nextStep: function () {
      if (this.currentStep < this.steps.length) {
        this.currentStep = this.currentStep + 1
      }
    }
  }
}
</script>
