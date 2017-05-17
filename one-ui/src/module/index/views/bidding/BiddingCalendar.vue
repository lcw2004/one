<template>
<section class="content">
  <div class="box">
    <div class="box-header">
      <h3 class="box-title">
        项目日历
      </h3>
    </div>
    <div class="box-body">
      <div class="row">
        <div class="col-md-12">
          <Calendar :events="events" :eventClick="eventClick"></Calendar>
        </div>
      </div>
    </div>
    <div class="box-footer">
    </div>
  </div>

  <ProjectPreview :config="config"></ProjectPreview>
</section>
</template>

<script>
import ProjectPreview from './calendar/ProjectPreview'

export default {
  components: {
    ProjectPreview
  },
  props: {},
  data: function () {
    return {
      events: [],
      config: {
        show: false,
        title: '项目预览'
      }
    }
  },
  mounted () {
    this.loadEvents()
  },
  methods: {
    eventClick (event) {
      this.config.title = event.title
      this.config.show = true
    },
    loadEvents () {
      let date = new Date()
      let d = date.getDate()
      let m = date.getMonth()
      let y = date.getFullYear()

      let events = [
        {
          id: 1,
          title: '项目一（售标）',
          start: new Date(y, m, d - 15),
          end: new Date(y, m, d - 12),
          backgroundColor: '#f56954', // red
          borderColor: '#f56954' // red
        },
        {
          id: 1,
          title: '项目一（投标）',
          start: new Date(y, m, d - 12),
          backgroundColor: '#f56954', // red
          borderColor: '#f56954' // red
        },
        {
          id: 1,
          title: '项目一（开标）',
          start: new Date(y, m, d - 11),
          backgroundColor: '#f56954', // red
          borderColor: '#f56954' // red
        },
        {
          id: 2,
          title: '项目二（售标）',
          start: new Date(y, m, d - 7),
          end: new Date(y, m, d - 2),
          backgroundColor: '#f39c12', // yellow
          borderColor: '#f39c12' // yellow
        },
        {
          id: 2,
          title: '项目二（投标）',
          start: new Date(y, m, d - 2),
          end: new Date(y, m, d),
          backgroundColor: '#f39c12', // yellow
          borderColor: '#f39c12' // yellow
        },
        {
          id: 2,
          title: '项目二（开标）',
          start: new Date(y, m, d),
          end: new Date(y, m, d + 2),
          backgroundColor: '#f39c12', // yellow
          borderColor: '#f39c12' // yellow
        },
        {
          id: 3,
          title: '项目三（售标）',
          start: new Date(y, m, d - 1),
          end: new Date(y, m, d + 3),
          allDay: false,
          backgroundColor: '#0073b7', // Blue
          borderColor: '#0073b7' // Blue
        },
        {
          id: 4,
          title: '项目四（售标）',
          start: new Date(y, m, d),
          end: new Date(y, m, d + 4),
          allDay: false,
          backgroundColor: '#00c0ef', // Info (aqua)
          borderColor: '#00c0ef' // Info (aqua)
        }
      ]

      this.events = events
    },
    addEvent () {
      let length = this.events.length
      let date = new Date()
      let d = date.getDate()
      let m = date.getMonth()
      let y = date.getFullYear()
      let randomEvent = {
        id: length + 1,
        title: 'Birthday Party',
        start: new Date(y, m, d + length, 19, 0),
        end: new Date(y, m, d + length, 22, 30),
        allDay: false,
        backgroundColor: '#00a65a',
        borderColor: '#00a65a'
      }
      this.events.push(randomEvent)
    }
  }
}
</script>
