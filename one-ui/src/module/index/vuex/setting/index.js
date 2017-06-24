import mutations from './mutations'
import actions from './actions'

const state = {
  appName: 'AdminLTE',
  appShortName: 'ALT',
  appVersion: '1.0',
  maxFileSizeShow: '',
  maxFileSize: 1024 * 1024
}

export default {
  state,
  mutations,
  actions
}
