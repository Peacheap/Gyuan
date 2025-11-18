import { reactive } from 'vue'

const config = reactive({
  baseURL: import.meta.env.VITE_API_BASE || 'http://localhost:8080',
})

// Expose for quick runtime inspection in browser console
if (typeof window !== 'undefined') {
  // eslint-disable-next-line no-undef
  window.__HTTP_BASE__ = config.baseURL
  // eslint-disable-next-line no-console
  console.log('HTTP base =', window.__HTTP_BASE__)
}

export async function httpGet(path) {
  const url = `${config.baseURL}${path}`
  const response = await fetch(url, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    },
  })
  const text = await response.text()
  if (!response.ok) {
    throw new Error(text || `HTTP ${response.status}`)
  }
  const trimmed = text.trim()
  if (trimmed === '') {
    return null
  }
  const contentType = response.headers.get('content-type') || ''
  if (contentType.includes('application/json')) {
    try {
      return JSON.parse(trimmed)
    } catch {
      throw new Error('响应不是有效的 JSON')
    }
  }
  // 非 JSON 响应，直接返回文本
  return trimmed
}

export async function httpPost(path, body) {
  const url = `${config.baseURL}${path}`
  const response = await fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: body ? JSON.stringify(body) : undefined,
  })
  const text = await response.text()
  if (!response.ok) {
    throw new Error(text || `HTTP ${response.status}`)
  }
  const trimmed = text.trim()
  if (trimmed === '') return null
  const contentType = response.headers.get('content-type') || ''
  if (contentType.includes('application/json')) {
    try {
      return JSON.parse(trimmed)
    } catch {
      throw new Error('响应不是有效的 JSON')
    }
  }
  return trimmed
}

export function setBaseURL(baseURL) {
  config.baseURL = baseURL
}

