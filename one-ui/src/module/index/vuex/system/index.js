import mutations from './mutations'
import actions from './actions'

/**
 * 系统信息
 */
const systemInfo = {
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
  systemTime: new Date(), // 系统时间
  sidebarIsExpand: true, // 菜单栏的展开和关闭状态
  isShowRouterView: true
}

/**
 * 菜单信息
 */
const menuInfo = {
  menuMap: {}, // 所有的菜单Map，键为ID，值为菜单
  permisionMap: {},
  topMenu: {}, // 顶级节点
  firstMenu: {}, // 当前的一级菜单
  secondMenu: {}, // 当前的二级菜单
  thirdMenu: {}, // 当前的三级菜单
  menuPath: [] // 三级菜单激活的时候的菜单路径
}

/**
 * 用户信息
 */
const userInfo = {
  userInfo: {}, // 用户信息
  userOffice: {}, // 用户机构
  supplierId: '', // 供应商ID
  supplierInfo: {}, // 供应商信息
  userRoleType: 0,
  messageList: [], // 消息列表
  unreadMessageCount: 0 // 未读消息数量
}

/**
 * 系统数据
 */
const systemData = {
  dictMap: {}, // 字典信息
  roleList: [], // 角色列表
  topArea: {}, // 区域信息
  topPurchaseType: {} // 采购类别信息
}

const state = Object.assign({}, systemInfo, menuInfo, userInfo, systemData)

export default {
  state,
  mutations,
  actions
}
