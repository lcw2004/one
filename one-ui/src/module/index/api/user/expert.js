import axios from 'axios'

// 评标专家
export function listExpert (params) {
  return axios.post(`/api/user/expert`, {params})
}
export function getExpert (objId) {
  return axios.get(`/api/user/expert/${objId}`)
}
export function saveExpert (obj) {
  return axios.post(`/api/user/expert`, obj)
}
export function updateExpert (obj) {
  return axios.put(`/api/user/expert`, obj)
}
export function deleteExpert (objId) {
  return axios.delete(`/api/user/expert/${objId}`)
}
