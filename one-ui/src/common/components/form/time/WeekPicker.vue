<template>
  <div class="date-picker">
    <el-date-picker
      v-model="time"
      type="week"
      size="small"
      firstDayOfWeek="1"
      format="yyyy 第 WW 周"
      :value-format="format"
      placeholder="选择周">
    </el-date-picker>
  </div>
</template>

<script>
import TimeMixin from './TimeMixin'

export default {
  mixins: [TimeMixin],
  name: 'WeekPicker',
  props: {
    value: {},
    format: {
      type: String,
      default: 'yyyy-MM-dd'
    }
  }
}
</script>
