import mutations from './mutations'
import actions from './actions'

const state = {
  token: '',
  settings: {
    appName: 'One Admin',
    appShortName: 'One',
    appVersion: '1.0',
    maxFileSizeShow: '',
    maxFileSize: 1024 * 1024,
    feeManager: '中心价格审核室张三（0731 - 99999999）',
    basePath: ''
  },
  menuMap: {}, // 所有的菜单Map，键为ID，值为菜单
  topMenu: {}, // 顶级节点
  firstMenu: {}, // 当前的一级菜单
  secondMenu: {}, // 当前的二级菜单
  thirdMenu: {}, // 当前的三级菜单
  menuPath: [], // 三级菜单激活的时候的菜单路径
  dictMap: {}, // 字典信息
  userInfo: {}, // 用户信息
  userOffice: {}, // 用户机构
  supplierId: '', // 供应商ID
  userRoleType: 0,
  messageList: [], // 消息列表
  unreadMessageCount: 0, // 未读消息数量
  systemTime: new Date(), // 系统时间
  sidebarIsExpand: true // 菜单栏的展开和关闭状态
}

export default {
  state,
  mutations,
  actions
}
