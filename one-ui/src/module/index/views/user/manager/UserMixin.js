import FormMixin from '@mixins/FormMixin'
import SelectOfficeModal from '../../system/modal/SelectOfficeModal'
import PasswordStrength from './PasswordStrength.vue'

export default {
  mixins: [FormMixin],
  components: {
    SelectOfficeModal,
    PasswordStrength
  },
  data: function () {
    return {
      form: {
        actions: {
          get: this.$api.user.getManagerUser,
          save: this.$api.user.saveManagerUser,
          update: this.$api.user.updateManagerUser
        },
        continue: false
      },

      obj: {
        userId: '1',
        remark: '',
        workNumber: '',
        password: '',
        roleIdList: [],
        userInfo: {
          account: '',
          name: '',
          type: 1,
          status: 1,
          userContactInfo: {
            areaCode: '',
            mobile: '',
            email: '',
            phone: '',
            wechatId: '',
            address: '',
            zipCode: ''
          }
        },
        sysOffice: {}
      },
      rolelist: [],
      companyTreeModalConfig: {
        show: false,
        title: '选择所属机构'
      },
      officeTreeModalConfig: {
        show: false,
        title: '选择所属部门'
      }
    }
  },
  mounted () {
    this.loadRole()
  },
  methods: {
    loadRole: function () {
      this.$api.system.listRole({pageSize: 100}).then((response) => {
        let result = response.data
        if (result.ok) {
          let roleList = []
          for (let role of result.data.list) {
            // 忽略系统内置角色
            if (role.id !== '20' && role.id !== '30') {
              roleList.push(role)
            }
          }
          this.rolelist = roleList
        }
      })
    }
  }
}
