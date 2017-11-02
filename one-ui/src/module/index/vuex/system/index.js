import mutations from './mutations'
import actions from './actions'

const state = {
  menuMap: {}, // 所有的菜单Map，键为ID，值为菜单
  topMenu: {}, // 顶级节点
  firstMenu: {}, // 当前的一级菜单
  secondMenu: {}, // 当前的二级菜单
  thirdMenu: {}, // 当前的三级菜单
  menuPath: [], // 三级菜单激活的时候的菜单路径
  dictMap: {},
  userInfo: {},
  sidebarIsExpand: true // 菜单栏的展开和关闭状态，
}

export default {
  state,
  mutations,
  actions
}
