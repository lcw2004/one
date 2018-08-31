import FormMixin from '@mixins/FormMixin'
import SelectOfficeModal from '../../../system/modal/SelectOfficeModal.vue'
import PasswordStrength from '../../common/PasswordStrength.vue'
import UserElSelect from '../../modal/UserElSelect.vue'

export default {
  mixins: [FormMixin],
  components: {
    SelectOfficeModal,
    PasswordStrength,
    UserElSelect
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
        leader: null,
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
  computed: {
    roleList: function () {
      // 忽略系统内置角色
      const roleList = this.$store.state.system.roleList
      let roleListWithoutDefault = []
      for (let role of roleList) {
        if (role.id !== '20' && role.id !== '30') {
          roleListWithoutDefault.push(role)
        }
      }
      return roleListWithoutDefault
    }
  },
  mounted () {
    this.$store.dispatch('initRoleList')
  },
  watch: {
    'obj.sysOffice': {
      handler: function () {
        this.$validator.validate('归属公司', this.obj.sysOffice.name)
      },
      deep: true
    }
  }
}
