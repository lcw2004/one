<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">采购负责人</h3>
      </div>
      <div class="box-body">
        <div class="row row-margin-bottom">
          <div class="col-md-12">
            <div class="pull-right">
              <button type="button" class="btn btn-primary" @click="config.show = true">
                添加负责人
              </button>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <table class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th>归属公司</th>
                  <th>登录名</th>
                  <th>姓名</th>
                  <th>电话</th>
                  <th>手机</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="obj of list">
                  <td>{{ obj.sysOffice.name }}</td>
                  <td>{{ obj.userInfo.account }}</td>
                  <td>{{ obj.userInfo.name }}</td>
                  <td>{{ obj.userInfo.userContactInfo.phone }}</td>
                  <td>{{ obj.userInfo.userContactInfo.mobile }}</td>
                  <td>
                    <a @click="deleteData(obj.userId)">删除</a>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
    <SelectSingleManageUserModal :config="config" v-model="selectedUser" @input="saveData(arguments[0])"></SelectSingleManageUserModal>
  </section>
</template>

<script>
import SelectSingleManageUserModal from '../../../user/modal/SelectSingleManageUserModal.vue'

export default {
  components: {
    SelectSingleManageUserModal
  },
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/base/professionType/{purchaseTypeId}/user'}
      },
      config: {
        show: false,
        title: '添加采购负责人'
      },
      purchaseTypeId: '',
      list: [],
      selectedUser: {}
    }
  },
  mounted () {
    this.purchaseTypeId = this.$route.params.id
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.system.listProfessionTypeUser(this.purchaseTypeId).then((response) => {
        let result = response.data
        if (result.ok) {
          this.list = result.data
        }
      })
    },
    saveData (user) {
      let userList = []
      userList.push(user.userId)
      this.$api.system.saveProfessionTypeUser(this.purchaseTypeId, userList).then((response) => {
        let result = response.data
        if (result.ok) {
          this.loadData()
          this.$notify.success('添加成功')
        }
      })
    },
    deleteData (userId) {
      this.$confirm('确认删除吗？', () => {
        this.$api.system.deleteProfessionTypeUser(this.purchaseTypeId, userId).then((response) => {
          let result = response.data
          if (result.ok) {
            this.loadData()
            this.$notify.success('删除数据成功')
          }
        })
      })
    }
  }
}
</script>
