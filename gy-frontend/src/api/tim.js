import { httpGet } from './http'

export function findTimById(id) {
  if (id == null || id === '') {
    return Promise.reject(new Error('id 不能为空'))
  }
  const numericId = Number(id)
  if (!Number.isInteger(numericId) || numericId < 0) {
    return Promise.reject(new Error('id 必须为非负整数'))
  }
  return httpGet(`/tim/findById/${numericId}`)
}

