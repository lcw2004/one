import axios from 'axios'

// 用户
export function listManagerUser (params) {
  return axios.get(`/api/user/manager`, {params})
}
export function getManagerUser (objId) {
  return axios.get(`/api/user/manager/${objId}`)
}
export function saveManagerUser (obj) {
  return axios.post(`/api/user/manager`, obj)
}
export function updateManagerUser (obj) {
  return axios.put(`/api/user/manager`, obj)
}
export function deleteManagerUser (objId) {
  return axios.delete(`/api/user/manager/${objId}`)
}
