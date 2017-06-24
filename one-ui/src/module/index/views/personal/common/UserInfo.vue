<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <UserInfoView v-show="!editMode" @edit-start="editMode = true"></UserInfoView>
        <UserInfoEdit v-show="editMode" @edit-end="editMode = false"></UserInfoEdit>
      </div>
    </div>
  </section>
</template>

<script>
  import UserInfoEdit from './UserInfoEdit'
  import UserInfoView from './UserInfoView'

  export default {
    components: {
      UserInfoEdit,
      UserInfoView
    },
    data: () => {
      return {
        editMode: false
      }
    }
  }
</script>
