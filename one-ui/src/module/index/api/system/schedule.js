import axios from 'axios'

// 任务调度列表
export function listSysSchedule (params) {
  return axios.get(`/api/sys/schedule`, {params: params})
}

// 任务调度详情
export function getSysSchedule (objId) {
  return axios.get(`/api/sys/schedule/${objId}`)
}

// 修改任务调度
export function updateSysSchedule (obj) {
  return axios.put(`/api/sys/schedule`, obj)
}

// 任务调度日志列表
export function listSysScheduleLog (params) {
  return axios.get(`/api/sys/scheduleLog`, {params: params})
}

