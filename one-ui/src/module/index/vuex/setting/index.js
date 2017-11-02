import mutations from './mutations'
import actions from './actions'

const state = {
  appName: 'One Admin',
  appShortName: 'One',
  appVersion: '1.0',
  maxFileSizeShow: '',
  maxFileSize: 1024 * 1024,
  feeManager: '中心价格审核室张三（0731 - 99999999）',
  basePath: ''
}

export default {
  state,
  mutations,
  actions
}
