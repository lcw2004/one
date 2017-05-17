<template>
  <div class="direct-chat-messages">
    <ChatMessage message="你好，你叫什么!"></ChatMessage>
    <ChatMessage message="你好，我是张三！" slide="right"></ChatMessage>
  </div>
</template>

<script>
import ChatMessage from './ChatMessage'

export default {
  components: {
    ChatMessage
  },
  data: function () {
    return {}
  }
}
</script>
