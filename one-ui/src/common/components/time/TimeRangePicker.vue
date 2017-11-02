<!--

使用示例：<TimeRangePicker :from="param.registTimeFrom" :to="param.registTimeTo"
          @from="param.registTimeFrom = arguments[0]" @to="param.registTimeTo = arguments[0]"/>

 -->

<template>
  <el-date-picker
    v-model="timeRange"
    type="daterange"
    align="right"
    size="small"
    placeholder="选择日期范围"
    :value-format="format"
    :picker-options="pickerOptions">
  </el-date-picker>
</template>

<script>
  import moment from 'moment'

  export default {
    name: 'TimeRangePicker',
    props: {
      from: {},
      to: {},
      format: {
        type: String,
        default: 'yyyy-MM-dd'
      }
    },
    data: () => {
      return {
        time: '',
        pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick (picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
              picker.$emit('pick', [start, end])
            }
          }, {
            text: '最近一个月',
            onClick (picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
              picker.$emit('pick', [start, end])
            }
          }, {
            text: '最近三个月',
            onClick (picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
              picker.$emit('pick', [start, end])
            }
          }]
        }
      }
    },
    computed: {
      timeRange: {
        get: function () {
          let timeRange = []
          if (this.from && this.to) {
            timeRange.push(moment(this.from).toDate())
            timeRange.push(moment(this.to).toDate())
          } else {
            timeRange = ''
          }
          return timeRange
        },
        set: function (newValue) {
          if (newValue && newValue.length === 2 && newValue[0] && newValue[1]) {
            let from = moment(newValue[0]).format('YYYY-MM-DD')
            let to = moment(newValue[1]).format('YYYY-MM-DD')
            this.$emit('from', from)
            this.$emit('to', to)
          } else {
            this.$emit('from', '')
            this.$emit('to', '')
          }
        }
      }
    }
  }
</script>
