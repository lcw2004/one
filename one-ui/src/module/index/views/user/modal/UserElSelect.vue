<template>
  <el-select v-model="innerValue" filterable :placeholder="placeholder" size="small" value-key="userId">
    <el-option
      v-for="user of userList"
      :key="user.userId"
      :label="user.userInfo.name"
      :value="user.userInfo">
    </el-option>
  </el-select>
</template>

<script>
export default {
  props: {
    value: {
      type: Number
    },
    placeholder: {
      type: String,
      default: '请选择'
    },
    officeId: {
      type: String
    },
    roleIdList: {
      type: String
    }
  },
  data () {
    return {
      userList: []
    }
  },
  created () {
    this.$emit('input', this.defaultValue)
  },
  computed: {
    innerValue: {
      get: function () {
        return this.value
      },
      set: function (newValue) {
        this.$emit('input', newValue)
      }
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.user.listManagerUser({pageSize: 1000, officeId: this.officeId, roleIdList: this.roleIdList}).then((response) => {
        let result = response.data
        if (result.ok) {
          this.userList = result.data.list
        }
      })
    },
    onChange () {
      this.$emit('change')
    }
  }
}
</script>
