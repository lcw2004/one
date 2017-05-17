<template>
  <div id="reportrange" class="input-group">
    <input type="text" class="form-control" :id="id">
    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
  </div>
</template>

<script>
let $ = require('jquery')
import datetimepicker from 'eonasdan-bootstrap-datetimepicker'
$.fn.datetimepicker = datetimepicker

export default {
  name: 'TimePicker',
  data: function () {
    return {
      id: new Date().getTime()
    }
  },
  mounted: function () {
    $(this.$el).datetimepicker({
      locale: 'zh-CN',
      format: 'YYYY-MM-DD HH:mm:00',
      viewMode: 'days'
    })
  }
}
</script>
