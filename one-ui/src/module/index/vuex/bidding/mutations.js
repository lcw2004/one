import * as types from './mutation-types'

export default {
  [types.INIT_SCENE_PROJECT] (state, project) {
    state.project = project
  },
  [types.INIT_SCENE_PROJECT_ID] (state, projectId) {
    state.projectId = projectId
  }
}
