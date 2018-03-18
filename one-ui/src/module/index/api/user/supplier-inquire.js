import axios from 'axios'

// 供应商调查列表
export function listUserSupplierInquire (supplierId) {
  return axios.get(`/api/user/supplier/${supplierId}/inquire`)
}

// 供应商调查详情
export function getUserSupplierInquire (supplierId, inquireId) {
  return axios.get(`/api/user/supplier/${supplierId}/inquire/{inquireId}`)
}

// 保存供应商调查
export function saveUserSupplierInquire (supplierId, inquire) {
  return axios.post(`/api/user/supplier/${supplierId}/inquire`, inquire)
}

// 修改供应商调查
export function updateUserSupplierInquire (supplierId, inquire) {
  return axios.put(`/api/user/supplier/${supplierId}/inquire`, inquire)
}

// 删除供应商调查
export function deleteUserSupplierInquire (supplierId, inquireId) {
  return axios.delete(`/api/user/supplier/${supplierId}/inquire/${inquireId}`)
}
