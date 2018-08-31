<template>
  <div class="row margin-top-10">
    <template v-if="taskInfo.bindType == 2">
      <div class="col-md-4">
        <SelectRoleId v-model="taskInfo.bindRoleId" :isShowEmpty="false" @change="onChange()"></SelectRoleId>
      </div>
    </template>
    <template v-if="taskInfo.bindType == 3">
      <div class="col-md-4">
        <div class="input-group">
          <input type="text" class="form-control" v-model="auditOfficeName" v-validate="'required'" name="审核部门" readonly>
          <span class="input-group-btn">
            <button type="button" class="btn btn-primary btn-flat" @click="auditOfficeConfig.show = true">选择</button>
          </span>
        </div>
      </div>
    </template>
    <template v-if="taskInfo.bindType == 4">
      <div class="col-md-4">
        <div class="input-group">
          <input type="text" class="form-control" v-model="auditUserName" v-validate="'required'" name="审核人" readonly>
          <span class="input-group-btn">
            <button type="button" class="btn btn-primary btn-flat" @click="auditUserConfig.show = true">选择</button>
          </span>
        </div>
      </div>
    </template>
    <template v-if="taskInfo.bindType == 5">
      <div class="col-md-4">
        <div class="input-group">
          <input type="text" class="form-control" v-model="auditOfficeName" v-validate="'required'" name="审核部门" readonly>
          <span class="input-group-btn">
            <button type="button" class="btn btn-primary btn-flat" @click="auditOfficeConfig.show = true">选择</button>
          </span>
        </div>
      </div>
      <div class="col-md-4">
        <SelectRoleId v-model="taskInfo.bindRoleId" :isShowEmpty="false"></SelectRoleId>
      </div>
    </template>
    <template v-if="taskInfo.bindType == 8">
      <div class="col-md-4">
        <SelectRoleId v-model="taskInfo.bindRoleId" :isShowEmpty="false" @change="onChange()"></SelectRoleId>
      </div>
    </template>
    <template v-if="taskInfo.bindType == 9">
      <div class="col-md-4">
        <input type="text" class="form-control" v-model="taskInfo.bindService" maxlength="100" @change="onChange()">
      </div>
    </template>

    <SelectOfficeModal :config="auditOfficeConfig" v-model="taskInfo.bindOffice"></SelectOfficeModal>
    <SelectSingleManageUserModal :config="auditUserConfig" v-model="taskInfo.bindUser"></SelectSingleManageUserModal>
  </div>
</template>

<script>
import SelectOfficeModal from '../modal/SelectOfficeModal.vue'
import SelectSingleManageUserModal from '../../user/modal/SelectSingleManageUserModal.vue'
import SelectRoleId from '../modal/SelectRoleId.vue'

export default {
  components: {
    SelectOfficeModal,
    SelectSingleManageUserModal,
    SelectRoleId
  },
  props: {
    taskInfo: {
      type: Object
    }
  },
  data: function () {
    return {
      auditOfficeConfig: {
        title: '选择审核部门',
        show: false
      },
      auditUserConfig: {
        title: '选择审核人',
        show: false
      }
    }
  },
  computed: {
    auditOfficeName: function () {
      return this.taskInfo.bindOffice ? this.taskInfo.bindOffice.name : ''
    },
    auditUserName: function () {
      return this.taskInfo.bindUser ? this.taskInfo.bindUser.name : ''
    },
    auditRoleName: function () {
      return this.taskInfo.bindRole ? this.taskInfo.bindRole.name : ''
    }
  },
  methods: {
    onChange () {
      this.$emit('change')
    }
  },
  watch: {
    'taskInfo.bindRoleId': {
      handler: function () {
        this.onChange()
      }
    },
    'auditOfficeName': function () {
      this.onChange()
    },
    'auditUserName': function () {
      this.onChange()
    }
  }
}
</script>
