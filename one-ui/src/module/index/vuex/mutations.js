import * as types from './mutation-types'

export default {
  // 新增计划
  [types.SAVE_PLAN] (state, plan) {
    // 设置默认值，未来我们可以做登入直接读取昵称和头像
    const image = 'https://sfault-avatar.b0.upaiyun.com/147/223/147223148-573297d0913c5_huge256'

    state.list.push(
      Object.assign({ name: '二哲', image: image }, plan)
    )
  },
  // 删除某计划
  [types.DELETE_PLAN] (state, idx) {
    state.list.splice(idx, 1)
  }
}
