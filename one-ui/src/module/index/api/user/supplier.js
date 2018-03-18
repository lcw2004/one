import axios from 'axios'

export * from './supplier-inquire.js'

// 供应商
export function deleteSupplier (supplierId) {
  return axios.delete(`/api/user/supplier/${supplierId}`)
}
export function getSupplier (supplierId) {
  return axios.get(`/api/user/supplier/${supplierId}`)
}
export function auditSupplierPurchaseType (supplierId, purchaseTypeId, auditResult, remark) {
  let params = {
    auditResult,
    remark
  }
  return axios.post(`/api/user/supplier/audit/${supplierId}/${purchaseTypeId}`, null, {params: params})
}

export function listSupplierPurchaseType (supplierId) {
  return axios.get(`/api/user/supplier/audit/${supplierId}/purchaseType`)
}

// 停用供应商
export function stopSupplier (supplierId, remark) {
  return axios.put(`/api/user/supplier/${supplierId}/stop`, null, {params: {remark}})
}

// 启用供应商
export function startSupplier (supplierId, remark) {
  return axios.put(`/api/user/supplier/${supplierId}/start`, null, {params: {remark}})
}

// 移入黑名单
export function moveInBlackList (supplierId, remark) {
  return axios.put(`/api/user/supplier/blackList/${supplierId}/in`, null, {params: {remark}})
}

// 从黑名单移除
export function moveOutFromBlackList (supplierId, remark) {
  return axios.put(`/api/user/supplier/blackList/${supplierId}/out`, null, {params: {remark}})
}

// 调整供应商等级
export function updateSupplierLevel (supplierId, level, remark) {
  return axios.put(`/api/user/supplier/${supplierId}/level`, null, {params: {level, remark}})
}

export function listSupplierLog (supplierId) {
  return axios.get(`/api/user/supplier/${supplierId}/log`)
}

export function listSupplierProjectOfSupplier (supplierId) {
  return axios.get(`/api/bid/supplier/score/${supplierId}/project`)
}

export function listProjectForScoreOfSupplier (supplierId) {
  return axios.get(`/api/bid/supplier/score/${supplierId}/projectForScore`)
}

export function listScoreOfSupplier (supplierId, projectId) {
  return axios.get(`/api/bid/supplier/score/${supplierId}/${projectId}/score`)
}

export function startScore (supplierId, projectId, userIdList, scoreTemplateId) {
  return axios.put(`/api/bid/supplier/score/${supplierId}/${projectId}/start`, userIdList, {params: {scoreTemplateId}})
}

export function endScore (supplierId, projectId) {
  return axios.put(`/api/bid/supplier/score/${supplierId}/${projectId}/end`)
}

export function saveScore (supplierId, projectId, template) {
  return axios.put(`/api/bid/supplier/score/${supplierId}/${projectId}/score`, template)
}
