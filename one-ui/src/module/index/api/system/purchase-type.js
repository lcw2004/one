import axios from 'axios'

// 采购类别
export function getPurchaseTypeTree () {
  return axios.get('/api/base/purchaseType/tree')
}
export function getPurchaseType (objId) {
  return axios.get(`/api/base/purchaseType/${objId}`)
}
export function savePurchaseType (obj) {
  return axios.post(`/api/base/purchaseType`, obj)
}
export function updatePurchaseType (obj) {
  return axios.put(`/api/base/purchaseType`, obj)
}
export function deletePurchaseType (objId) {
  return axios.delete(`/api/base/purchaseType/${objId}`)
}

// 采购物料
export function getMateriel (objId) {
  return axios.get(`/api/base/materiel/${objId}`)
}
export function saveMateriel (obj) {
  return axios.post(`/api/base/materiel`, obj)
}
export function updateMateriel (obj) {
  return axios.put(`/api/base/materiel`, obj)
}
export function deleteMateriel (objId) {
  return axios.delete(`/api/base/materiel/${objId}`)
}

// 采购用户
export function listProfessionTypeUser (purchaseTypeId) {
  return axios.get(`/api/base/professionType/${purchaseTypeId}/user`)
}
export function saveProfessionTypeUser (purchaseTypeId, userIdList) {
  return axios.post(`/api/base/professionType/${purchaseTypeId}/user`, userIdList)
}
export function deleteProfessionTypeUser (purchaseTypeId, userId) {
  return axios.delete(`/api/base/professionType/${purchaseTypeId}/user/${userId}`)
}
export function listQualificationType (userType) {
  return axios.get(`/api/qualificationType?userType=${userType}`)
}
