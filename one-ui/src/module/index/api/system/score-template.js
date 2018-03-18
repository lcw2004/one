import axios from 'axios'

// 评分模板列表
export function listBaseScoreTemplate (params) {
  return axios.get(`/api/base/scoreTemplate`, {params: params})
}

// 评分模板详情
export function getBaseScoreTemplate (objId) {
  return axios.get(`/api/base/scoreTemplate/${objId}`)
}

// 保存评分模板
export function saveBaseScoreTemplate (obj) {
  return axios.post(`/api/base/scoreTemplate`, obj)
}

// 修改评分模板
export function updateBaseScoreTemplate (obj) {
  return axios.put(`/api/base/scoreTemplate`, obj)
}

// 删除评分模板
export function deleteBaseScoreTemplate (objId) {
  return axios.delete(`/api/base/scoreTemplate/${objId}`)
}

// 查看评分
export function getAnswerByScoreId (scoreId) {
  return axios.get(`/api/base/userScore/${scoreId}`)
}
