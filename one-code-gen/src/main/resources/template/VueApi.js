import axios from 'axios'

// @{table.tableName}列表
export function list@{table.ClassNameNoEO} (params) {
  return axios.get(`/@{restPath}/@{strategy.moduleSimpleName}/@{table.restPath}`, {params: params})
}

// @{table.tableName}详情
export function get@{table.ClassNameNoEO} (objId) {
  return axios.get(`/@{restPath}/@{strategy.moduleSimpleName}/@{table.restPath}/${objId}`)
}

// 保存@{table.tableName}
export function save@{table.ClassNameNoEO} (obj) {
  return axios.post(`/@{restPath}/@{strategy.moduleSimpleName}/@{table.restPath}`, obj)
}

// 修改@{table.tableName}
export function update@{table.ClassNameNoEO} (obj) {
  return axios.put(`/@{restPath}/@{strategy.moduleSimpleName}/@{table.restPath}`, obj)
}

// 删除@{table.tableName}
export function delete@{table.ClassNameNoEO} (objId) {
  return axios.delete(`/@{restPath}/@{strategy.moduleSimpleName}/@{table.restPath}/${objId}`)
}
