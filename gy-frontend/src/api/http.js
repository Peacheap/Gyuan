import { reactive } from 'vue'

const config = reactive({
  baseURL: import.meta.env.VITE_API_BASE || 'http://localhost:18080',
})

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
  try {
    return JSON.parse(trimmed)
  } catch (e) {
    throw new Error('响应不是有效的 JSON')
  }
}

export function setBaseURL(baseURL) {
  config.baseURL = baseURL
}

