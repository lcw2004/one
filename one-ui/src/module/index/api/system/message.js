import axios from 'axios'
import { stringifyAxios } from '@utils/common'

export function listMessage (params) {
  return axios.get(`/api/msg/message`, {params})
}

export function markMessageAsRead (messageIdList) {
  return axios.put(`/api/msg/message/read`, null, stringifyAxios({messageIdList}))
}

export function markMessageAsDelete (messageIdList) {
  return axios.put(`/api/msg/message/delete`, null, stringifyAxios({messageIdList}))
}

export function markAllMessageAsRead () {
  return axios.put(`/api/msg/message/readAll`)
}

export function markAllMessageAsDelete () {
  return axios.put(`/api/msg/message/deleteAll`)
}

// 消息模板
export function listMsgType () {
  return axios.get(`/api/msg/template/type`)
}

export function listMsgChannel () {
  return axios.get(`/api/msg/template/channel`)
}

export function listMsgTemplate (params) {
  return axios.get(`/api/msg/template`, {params: params})
}

export function getMsgTemplate (objId) {
  return axios.get(`/api/msg/template/${objId}`)
}

export function saveMsgTemplate (obj) {
  return axios.post(`/api/msg/template`, obj)
}

export function updateMsgTemplate (obj) {
  return axios.put(`/api/msg/template`, obj)
}

export function deleteMsgTemplate (objId) {
  return axios.delete(`/api/msg/template/${objId}`)
}
