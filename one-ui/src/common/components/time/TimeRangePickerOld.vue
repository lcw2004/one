<template>
  <div id="reportrange" class="input-group">
    <input type="text" class="form-control" :id="id">
    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
  </div>
</template>

<script>
import 'bootstrap-daterangepicker'
import moment from 'moment'
let $ = require('jquery')

export default {
  name: 'TimeRangePicker',
  data: function () {
    return {
      id: new Date().getTime()
    }
  },
  mounted: function () {
    this.picker = $('#' + this.id)

    var start = moment().subtract(29, 'days')
    var end = moment()

    this.picker.daterangepicker({
      startDate: start,
      endDate: end,
      ranges: {
        '今天': [moment(), moment()],
        '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
        '一周前': [moment().subtract(6, 'days'), moment()],
        '这个月': [moment().startOf('month'), moment().endOf('month')],
        '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
      }
    })

    console.log(moment().format())
  }
}
</script>
