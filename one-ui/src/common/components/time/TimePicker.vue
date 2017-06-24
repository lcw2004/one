<template>
  <el-date-picker
    v-model="time"
    type="datetime"
    placeholder="选择日期时间"
    :picker-options="pickerOptions">
  </el-date-picker>
</template>

<script>
  import moment from 'moment'
  import TimeMixin from './TimeMixin'

  export default {
    mixins: [TimeMixin],
    name: 'TimePicker',
    props: {
      value: {}
    },
    computed: {
      time: {
        get: function () {
          let time = null
          if (this.value) {
            time = moment(this.value, 'YYYY-MM-DD HH:mm:ss').toDate()
          }
          return time
        },
        set: function (newDate) {
          let newValue = ''
          if (newDate) {
            newValue = moment(newDate).format('YYYY-MM-DD HH:mm:ss')
          }
          if (newDate !== undefined) {
            this.$emit('input', newValue)
          }
        }
      }
    }
  }
</script>
