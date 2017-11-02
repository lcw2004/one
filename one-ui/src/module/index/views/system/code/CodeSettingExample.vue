<template>
  <div class="row">
    <div class="col-md-12">
      <p class="form-control-static">
        单据编号规则 =
        <template v-for="(section, index) of codeSetting.sectionEOList">
          <span class="label label-primary">{{ getTypeLable(section.type) }}</span>
          <template v-if="index < codeSetting.sectionEOList.length - 1">+ </template>
        </template>
      </p>
    </div>
    <div class="col-md-12">
      <p class="form-control-static">
        单据编号示例 = <span class="label label-primary">{{ exampleValue }}</span>
      </p>
    </div>
  </div>
</template>

<script>
import * as utils from './code-setting.js'
export default {
  props: {
    codeSetting: {
      type: Object,
      require: true
    }
  },
  computed: {
    exampleValue: function () {
      let value = ''
      if (this.codeSetting.sectionEOList) {
        for (let section of this.codeSetting.sectionEOList) {
          value += this.getSectionValue(section)
        }
      }
      return value
    }
  },
  methods: {
    getSectionValue (section) {
      if (!section) {
        return ''
      }
      if (section.type === 2 || section.type === 3 || section.type === 4) {
        return utils.timeNow(section.type)
      } else if (section.type === 5) {
        return section.content
      } else if (section.type === 6) {
        return section.content
      }
    },
    fillContent () {
      if (this.section.type === 6) {
        this.section.content = utils.fillZero(this.section.length)
      }
    },
    getTypeLable (type) {
      if (type === 2) {
        return '年'
      } else if (type === 3) {
        return '年月'
      } else if (type === 4) {
        return '年月日'
      } else if (type === 5) {
        return '固定内容'
      } else if (type === 6) {
        return '流水号'
      }
    }
  }
}
</script>
