import * as types from './mutation-types'

export default {
  initSceneProject ({commit}, project) {
    commit(types.INIT_SCENE_PROJECT, project)
  },
  initSceneProjectId ({commit}, projectId) {
    commit(types.INIT_SCENE_PROJECT_ID, projectId)
  }
}
