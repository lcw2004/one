<template>
  <el-date-picker
    v-model="time"
    type="date"
    placeholder="选择日期"
    :picker-options="pickerOptions">
  </el-date-picker>
</template>

<script>
  import moment from 'moment'
  import TimeMixin from './TimeMixin'

  export default {
    mixins: [TimeMixin],
    name: 'DatePicker',
    props: {
      value: {
        type: String
      }
    },
    computed: {
      time: {
        get: function () {
          let time = null
          if (this.value) {
            time = moment(this.value, 'YYYY-MM-DD').toDate()
          }
          return time
        },
        set: function (newDate) {
          let newValue = ''
          if (newDate) {
            newValue = moment(newDate).format('YYYY-MM-DD')
          }
          if (newDate !== undefined) {
            this.$emit('input', newValue)
          }
        }
      }
    }
  }
</script>
