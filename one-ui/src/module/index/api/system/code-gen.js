import axios from 'axios'
import { stringifyAxios } from '@utils/common'

export function listTable (tableName) {
  return axios.get(`/api/codeGen/table`, {params: {tableName}})
}

export function getTable (tableName) {
  return axios.get(`/api/codeGen/table/${tableName}`)
}

export function previewCode (tableName, codeType, strategy) {
  return axios.post(`/api/codeGen/table/${tableName}/${codeType}`, strategy)
}

export function listModule () {
  return axios.get(`api/codeGen/table/module`)
}

export function generate (codeGenStrategy) {
  return axios.post(`api/codeGen/table/generate`, codeGenStrategy)
}

export function listCodeGenTable (tableList) {
  return axios.get(`api/codeGen/tableEO`, stringifyAxios({tableList}))
}

export function saveCodeGenTable (table) {
  return axios.put(`api/codeGen/tableEO`, table)
}

export function listTableField (tableId) {
  return axios.get(`api/codeGen/tableEO/${tableId}/field`)
}

export function updateTableFieldList (tableId, fieldEOList) {
  return axios.put(`api/codeGen/tableEO/${tableId}/field`, fieldEOList)
}
