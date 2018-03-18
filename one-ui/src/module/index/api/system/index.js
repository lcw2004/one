import axios from 'axios'

export * from './login.js'
export * from './purchase-type.js'
export * from './registry.js'
export * from './code-gen.js'
export * from './score-template.js'
export * from './message.js'

export function getToken () {
  return axios.get(`/api/token`)
}
export function audit (params) {
  return axios.get(`/api/flow/audit`, {params: params})
}
export function getTaskInfo (taskId) {
  return axios.get(`/api/flow/task/todo/${taskId}`)
}

// 字典
export function listDictType () {
  return axios.get(`/api/sys/dict/type`)
}
export function listDict (params) {
  return axios.get(`/api/sys/dict`, {params: params})
}
export function getDict (objId) {
  return axios.get(`/api/sys/dict/${objId}`)
}
export function saveDict (obj) {
  return axios.post(`/api/sys/dict`, obj)
}
export function updateDict (obj) {
  return axios.put(`/api/sys/dict`, obj)
}
export function deleteDict (objId) {
  return axios.delete(`/api/sys/dict/${objId}`)
}

// 角色
export function listRole (params) {
  return axios.get(`/api/sys/role`, {params: params})
}
export function getRole (objId) {
  return axios.get(`/api/sys/role/${objId}`)
}
export function saveRole (obj) {
  return axios.post(`/api/sys/role`, obj)
}
export function updateRole (obj) {
  return axios.put(`/api/sys/role`, obj)
}
export function deleteRole (objId) {
  return axios.delete(`/api/sys/role/${objId}`)
}
export function listHomeComponent (objId) {
  return axios.get(`/api/sys/homeComponent`)
}

// 菜单
export function getMenuTree () {
  return axios.get('/api/sys/menu/tree')
}
export function getMenu (objId) {
  return axios.get(`/api/sys/menu/${objId}`)
}
export function saveMenu (obj) {
  return axios.post(`/api/sys/menu`, obj)
}
export function updateMenu (obj) {
  return axios.put(`/api/sys/menu`, obj)
}
export function deleteMenu (objId) {
  return axios.delete(`/api/sys/menu/${objId}`)
}
export function updateMenuSort (objId, sort) {
  return axios.post(`/api/sys/menu/${objId}/sort`, null, {params: {sort: sort}})
}

// 模版
export function listTemplateType () {
  return axios.get(`/api/base/templateType`)
}
export function listTemplateByCode (code) {
  return axios.get('/api/base/template', {params: {'templateTypeCode': code}})
}
export function listTemplate (params) {
  return axios.get(`/api/base/template`, {params: params})
}
export function getTemplate (objId) {
  return axios.get(`/api/base/template/${objId}`)
}
export function saveTemplate (obj) {
  return axios.post(`/api/base/template`, obj)
}
export function updateTemplate (obj) {
  return axios.put(`/api/base/template`, obj)
}
export function deleteTemplate (objId) {
  return axios.delete(`/api/base/template/${objId}`)
}

// 机构
export function getOfficeTree () {
  return axios.get('/api/sys/office/tree')
}
export function getOffice (objId) {
  return axios.get(`/api/sys/office/${objId}`)
}
export function saveOffice (obj) {
  return axios.post(`/api/sys/office`, obj)
}
export function updateOffice (obj) {
  return axios.put(`/api/sys/office`, obj)
}
export function deleteOffice (objId) {
  return axios.delete(`/api/sys/office/${objId}`)
}

// 区域
export function getAreaTree () {
  return axios.get('/api/sys/area/tree')
}
export function getArea (objId) {
  return axios.get(`/api/sys/area/${objId}`)
}
export function saveArea (obj) {
  return axios.post(`/api/sys/area`, obj)
}
export function updateArea (obj) {
  return axios.put(`/api/sys/area`, obj)
}
export function deleteArea (objId) {
  return axios.delete(`/api/sys/area/${objId}`)
}

// 工作流
export function getWorkflow (workflowId) {
  return axios.get(`/api/flow/flowInfo/${workflowId}`)
}
export function saveWorkflow (workflow) {
  return axios.post(`/api/flow/flowInfo`, workflow)
}
export function updateWorkflow (workflow) {
  return axios.put(`/api/flow/flowInfo`, workflow)
}
export function deleteWorkflow (workflowId) {
  return axios.delete(`/api/flow/flowInfo/${workflowId}`)
}
export function listAuditLog (params) {
  return axios.get(`/api/flow/auditLog`, {params: params})
}

// 编码
export function generateCode (settingId) {
  return axios.get(`/api/base/codeSetting/${settingId}/genCode`)
}
export function listCodeSetting () {
  return axios.get(`/api/base/codeSetting`)
}
export function getCodeSetting (settingId) {
  return axios.get(`/api/base/codeSetting/${settingId}`)
}
export function updateCodeSetting (setting) {
  return axios.put(`/api/base/codeSetting`, setting)
}
export function unlockCode (code) {
  return axios.get(`/api/base/codeSetting/${code}/unlock`)
}

// 配置
export function getConfigByType (configType) {
  return axios.get(`/api/sys/config/${configType}`)
}
export function updateConfigByType (configType, configs) {
  return axios.post(`/api/sys/config/${configType}`, configs)
}

// 归档目录
export function getArchiveMenuTree () {
  return axios.get('/api/base/archiveMenu/tree')
}
export function getArchiveMenu (menuId) {
  return axios.get(`/api/base/archiveMenu/${menuId}`)
}
export function saveArchiveMenu (archiveMenu) {
  return axios.post(`/api/base/archiveMenu/`, archiveMenu)
}
export function updateArchiveMenu (archiveMenu) {
  return axios.put(`/api/base/archiveMenu/`, archiveMenu)
}
export function deleteArchiveMenu (menuId) {
  return axios.delete(`/api/base/archiveMenu/${menuId}`)
}
