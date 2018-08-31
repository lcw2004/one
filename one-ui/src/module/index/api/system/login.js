import axios from 'axios'

export function login (params) {
  return axios.get('/api/login', {params: params})
}

export function systemInfo () {
  return axios.get('/api/systemInfo')
}

export function logout () {
  return axios.get('/api/logout')
}

export function initData () {
  return axios.get('/api/initData')
}

export function homeStatistic () {
  return axios.get('/api/statistic/home')
}

export function checkIsLogin () {
  return axios.get('/api/isLogin')
}

export function updateUserPassword (oldPassword, newPassword) {
  return axios.put(`/api/updatePassword`, null, {params: {oldPassword: oldPassword, newPassword: newPassword}})
}

export function updateUserInfo (userInfo) {
  return axios.put(`/api/updateUserInfo`, userInfo)
}

export function getCurrentSupplierInfo () {
  return axios.get(`/api/user/supplier/current`)
}
