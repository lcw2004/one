import axios from 'axios'

// 供应商操作日志列表
export function listUserSupplierOperationLog (params) {
  return axios.get(`/api/user/user/operationLog`, {params: params})
}

// 供应商操作日志详情
export function getUserSupplierOperationLog (objId) {
  return axios.get(`/api/user/user/operationLog/${objId}`)
}

// 保存供应商操作日志
export function saveUserSupplierOperationLog (obj) {
  return axios.post(`/api/user/user/operationLog`, obj)
}

// 修改供应商操作日志
export function updateUserSupplierOperationLog (obj) {
  return axios.put(`/api/user/user/operationLog`, obj)
}

// 删除供应商操作日志
export function deleteUserSupplierOperationLog (objId) {
  return axios.delete(`/api/user/user/operationLog/${objId}`)
}
