import * as types from './mutation-types'

export default {
  [types.INIT_SETTING] (state, settings) {
    state.appName = settings.appName
    state.appShortName = settings.appShortName
    state.appVersion = settings.appVersion
    state.maxFileSize = settings.maxFileSize
    state.maxFileSizeShow = settings.maxFileSizeShow
  }
}
