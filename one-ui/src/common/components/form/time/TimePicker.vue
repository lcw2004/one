<template>
  <div class="date-picker">
    <el-date-picker
      v-model="time"
      type="datetime"
      size="small"
      placeholder="选择时间"
      :value-format="format"
      :picker-options="pickerOptions"
      :default-value="currentTime">
    </el-date-picker>
  </div>
</template>

<script>
import TimeMixin from './TimeMixin'

export default {
  mixins: [TimeMixin],
  name: 'TimePicker',
  props: {
    value: {},
    format: {
      type: String,
      default: 'yyyy-MM-dd HH:mm:ss'
    }
  }
}
</script>
