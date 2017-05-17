# Calendar组件

## props：
- events(Array, 必须) 事件列表
- eventClick(Function)  event点击回调事件
- editable(Boolean) 是否可编辑
- droppable(Boolean) 是否可拖拽

## 示例：

    <Calendar :events="events" :eventClick="eventClick"></Calendar>

    // 事件点击回调
    eventClick (event) {
      this.$notify.success('Event: ' + event.title)
      this.$notify.success('Event Id: ' + event.id)
    }

    // 加载事件
    loadEvents () {
      let date = new Date()
      let d = date.getDate()
      let m = date.getMonth()
      let y = date.getFullYear()

      let events = [
        {
          id: 1,
          title: 'All Day Event 1',
          start: new Date(y, m, 1),
          backgroundColor: '#f56954', // red
          borderColor: '#f56954' // red
        },
        {
          id: 2,
          title: 'Long Event',
          start: new Date(y, m, d - 5),
          end: new Date(y, m, d - 2),
          backgroundColor: '#f39c12', // yellow
          borderColor: '#f39c12' // yellow
        },
        {
          id: 3,
          title: 'Meeting',
          start: new Date(y, m, d, 10, 30),
          allDay: false,
          backgroundColor: '#0073b7', // Blue
          borderColor: '#0073b7' // Blue
        },
        {
          id: 4,
          title: 'Lunch',
          start: new Date(y, m, d, 12, 0),
          end: new Date(y, m, d, 14, 0),
          allDay: false,
          backgroundColor: '#00c0ef', // Info (aqua)
          borderColor: '#00c0ef' // Info (aqua)
        },
        {
          id: 5,
          title: 'Birthday Party',
          start: new Date(y, m, d + 1, 19, 0),
          end: new Date(y, m, d + 1, 22, 30),
          allDay: false,
          backgroundColor: '#00a65a', // Success (green)
          borderColor: '#00a65a' // Success (green)
        },
        {
          id: 6,
          title: 'Click for Google',
          start: new Date(y, m, 28),
          end: new Date(y, m, 29),
          url: 'http://google.com/',
          backgroundColor: '#3c8dbc', // Primary (light-blue)
          borderColor: '#3c8dbc' // Primary (light-blue)
        }
      ]

      this.events = events
    },

    // 添加事件
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
