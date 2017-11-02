<template>
  <el-date-picker
    v-model="time"
    type="date"
    placeholder="选择日期"
    size="small"
    :value-format="format"
    :picker-options="pickerOptions">
  </el-date-picker>
</template>

<script>
  import TimeMixin from './TimeMixin'

  export default {
    mixins: [TimeMixin],
    name: 'DatePicker',
    props: {
      value: {},
      format: {
        type: String,
        default: 'yyyy-MM-dd'
      }
    }
  }
</script>
