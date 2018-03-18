<template>
</template>

<script>
let $ = require('jquery')
require('fullcalendar')

export default {
  name: 'Calendar',
  props: {
    events: {
      type: Array
    },
    eventClick: {
      type: Function
    },
    editable: {
      type: Boolean
    },
    droppable: {
      type: Boolean
    }
  },
  mounted () {
    this.init()
  },
  data: function () {
    return {
      id: '123',
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay'
      },
      buttonText: {
        today: '今天',
        month: '月',
        week: '周',
        day: '日'
      }
    }
  },
  watch: {
    events: {
      handler: function () {
        this.cal.fullCalendar('refetchEvents')
      },
      deep: true
    }
  },
  methods: {
    init () {
      this.cal = $(this.$el).fullCalendar(this.calendarOption)
    },
    refresh () {
      this.cal.fullCalendar('refetchEvents')
    }
  },
  computed: {
    calendarOption: function () {
      let self = this
      return {
        locale: 'zh-cn',
        header: this.header,
        buttonText: this.buttonText,
        events: function (start, end, timezone, callback) {
          callback(self.events)
        },
        eventClick: function (calEvent, jsEvent, view) {
          self.eventClick(calEvent)
        },
        editable: false,
        droppable: false
      }
    }
  }
}
</script>

<style>
  a {
    cursor: pointer;
  }

  .fc-event-container :hover {
    opacity: 0.8
  }
</style>
