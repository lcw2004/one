import { stringify } from './common'

export function openOffice (basePath, businessType, businessId, authCode) {
  openOfficeParams(basePath, {
    businessType,
    businessId,
    authCode
  })
}

export function openOfficeParams (basePath, params) {
  let wordPath = basePath + '?' + stringify(params)
  console.log(wordPath)
  window.POBrowser.openWindow(wordPath, 'width=1200px;height=800px;')
}
