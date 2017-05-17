<template>
<div class="box box-primary box-solid direct-chat direct-chat-primary " :class="{'direct-chat-contacts-open' : showContacts}" >
  <div class="box-header">
    <h3 class="box-title">消息</h3>
    <div class="box-tools pull-right">
      <span data-toggle="tooltip" title="3 New Messages" class="badge bg-light-blue">3</span>
      <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
      <button class="btn btn-box-tool" title="Contacts" data-widget="chat-pane-toggle" @click="toggoleContacts()"><i class="fa fa-comments"></i></button>
      <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
    </div>
  </div>
  <div class="box-body">
    <ChatMessages></ChatMessages>
    <Contacts></Contacts>
  </div>
  <div class="box-footer">
    <form action="#" method="post">
      <div class="input-group">
        <input type="text" name="message" placeholder="输入 ..." class="form-control">
        <span class="input-group-btn">
          <button type="button" class="btn btn-primary btn-flat">发送</button>
        </span>
      </div>
    </form>
  </div>
</div>
</template>

<script>
import ChatMessages from './ChatMessages'
import Contacts from './Contacts'

export default {
  components: {
    ChatMessages,
    Contacts
  },
  data: function () {
    return {
      showContacts: false
    }
  },
  methods: {
    toggoleContacts: function () {
      this.showContacts = !this.showContacts
    }
  }
}
</script>
