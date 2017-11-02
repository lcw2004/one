import FormMixin from 'mixins/FormMixin.js'
import SelectOfficeModal from '../modal/SelectOfficeModal'
import PasswordStrength from './PasswordStrength.vue'

export default {
  mixins: [FormMixin],
  components: {
    SelectOfficeModal,
    PasswordStrength
  },
  data: () => {
    return {
      actions: {
        get: {method: 'get', url: '/api/user/manager{/id}'},
        getRole: {method: 'get', url: '/api/sys/role'},
        save: {method: 'post', url: '/api/user/manager'},
        update: {method: 'put', url: '/api/user/manager'}
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
      this.resource.getRole().then(function (response) {
        let result = response.body
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
