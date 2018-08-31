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
  return axios.get(`/api/user/supplier/registry/validAccount`, {params: {account}})
}

export function validSupplierName (name) {
  return axios.get(`/api/user/supplier/registry/validName`, {params: {name}})
}

export function registrySupplier (supplier) {
  return axios.post(`/api/user/supplier/registry/registrySupplierInfo`, supplier)
}

export function sendValidCode (email) {
  return axios.get(`/api/user/supplier/registry/sendValidCode`, {params: {email: email}})
}

export function getSupplierInfo (supplierId) {
  return axios.get(`/api/user/supplier/${supplierId}`)
}

export function prefectSupplierInfo (supplierInfo) {
  return axios.post(`/api/user/supplier/current/prefect`, supplierInfo)
}

export function changeSupplierInfo (supplierInfo) {
  return axios.post(`/api/user/supplier/current/change`, supplierInfo)
}
