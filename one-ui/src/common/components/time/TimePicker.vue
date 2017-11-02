<template>
  <el-date-picker
    v-model="time"
    type="datetime"
    size="small"
    placeholder="选择时间"
    :value-format="format"
    :picker-options="pickerOptions">
  </el-date-picker>
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
