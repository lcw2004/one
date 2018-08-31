<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <ManagerUserInfoView v-show="!editMode" :user="managerUser" @edit-start="startEdit()"></ManagerUserInfoView>
        <ManagerUserInfoEdit v-show="editMode" :user="managerUser" @edit-end="endEdit()"></ManagerUserInfoEdit>
      </div>
    </div>
  </section>
</template>

<script>
import ManagerUserInfoEdit from './manager/ManagerUserInfoEdit.vue'
import ManagerUserInfoView from './manager/ManagerUserInfoView.vue'

export default {
  components: {
    ManagerUserInfoView,
    ManagerUserInfoEdit
  },
  data: function () {
    return {
      editMode: false,
      managerUser: {}
    }
  },
  computed: {
    user: function () {
      return this.$store.state.system.userInfo
    }
  },
  mounted () {
    this.loadManagerUser()
  },
  methods: {
    loadManagerUser () {
      if (this.user.type === 1) {
        this.$api.user.getCurrentManagerUser().then((response) => {
          let result = response.data
          if (result.ok) {
            this.managerUser = result.data
          }
        })
      }
    },
    startEdit () {
      this.editMode = true
    },
    endEdit () {
      this.editMode = false
      this.loadManagerUser()
    }
  }
}
</script>
