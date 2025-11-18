import { httpGet, httpPost } from './http'

export function getRandomResponse() {
  return httpGet('/response/getResponse')
}

// Comments APIs
export function createComment(payload) {
  return httpPost('/comments', payload)
}

export function listRootComments(page = 1, size = 10) {
  return httpGet(`/comments?page=${page}&size=${size}`)
}

export function listReplies(parentId) {
  return httpGet(`/comments/${parentId}/replies`)
}

export function likeComment(id) {
  return httpPost(`/comments/${id}/like`)
}


