<template>
  <div class="direct-chat-msg" :class="{right : slide == 'right'}">
    <div class="direct-chat-info clearfix">
      <span class="direct-chat-name" :class="[ slide == 'right' ? 'pull-right' : 'pull-left' ]">Sarah Bullock</span>
      <span class="direct-chat-timestamp" :class="[ slide == 'right' ? 'pull-left' : 'pull-right' ]">23 Jan 2:05 pm</span>
    </div>
    <img class="direct-chat-img" src="static/adminlte/img/user3-128x128.jpg" alt="message user image">
    <div class="direct-chat-text">
      {{ message }}
    </div>
  </div>
</template>

<script>
export default {
  props: {
    slide: {
      default: 'left'
    },
    message: {
      required: true,
      type: String
    }
  },
  data: function () {
    return {}
  }
}
</script>
