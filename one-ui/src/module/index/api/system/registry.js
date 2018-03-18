import axios from 'axios'

export function validAccount (params) {
  return axios.get(`/api/account/validAccount`, {params: params})
}

export function validVerifyCode (params) {
  return axios.get(`/api/account/validVerifyCode`, {params})
}

export function resetPassword (params) {
  return axios.get(`/api/account/resetPassword`, {params})
}

export function validRegistryAccount (account) {
  return axios.get(`/api/user/supplierRegistry/validAccount`, {params: {account}})
}

export function validSupplierName (name) {
  return axios.get(`/api/user/supplierRegistry/validName`, {params: {name}})
}

export function registrySupplier (supplier) {
  return axios.post(`/api/user/supplierRegistry/registryUserInfo`, supplier)
}

export function sendValidCode (email) {
  return axios.get(`/api/user/supplierRegistry/sendValidCode`, {params: {email: email}})
}

export function getSupplierInfo (supplierId) {
  return axios.get(`/api/user/supplier/${supplierId}`)
}

export function updateUserSupplier (supplierInfo) {
  return axios.post(`/api/user/supplierRegistry/updateUserSupplierEO`, supplierInfo)
}
