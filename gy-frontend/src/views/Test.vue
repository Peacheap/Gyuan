<template>
  <div style="padding:16px;">
    <h2 style="margin-bottom:12px;">Home</h2>
    <div style="display:flex; gap:8px; align-items:center; margin-bottom:12px;">
      <input
        :value="idInput"
        @input="onInputNumberOnly"
        placeholder="输入ID（数字）"
        style="padding:6px 8px; border:1px solid #ccc; border-radius:4px; width:200px;"
      />
      <button @click="onSearch" :disabled="loading || !idInput" style="padding:6px 12px;">
        {{ loading ? '查询中...' : '查询' }}
      </button>
    </div>

    <div v-if="errorMsg" style="color:#c00; margin-bottom:12px;">{{ errorMsg }}</div>
    

    <table v-if="data" style="border-collapse:collapse; width:100%;">
      <thead>
        <tr>
          <th style="border:1px solid #ddd; padding:8px; text-align:left;">id</th>
          <th style="border:1px solid #ddd; padding:8px; text-align:left;">ip</th>
          <th style="border:1px solid #ddd; padding:8px; text-align:left;">province</th>
          <th style="border:1px solid #ddd; padding:8px; text-align:left;">time</th>
          <th style="border:1px solid #ddd; padding:8px; text-align:left;">str</th>
          <th style="border:1px solid #ddd; padding:8px; text-align:left;">likes</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td style="border:1px solid #ddd; padding:8px;">{{ data.id }}</td>
          <td style="border:1px solid #ddd; padding:8px;">{{ data.ip }}</td>
          <td style="border:1px solid #ddd; padding:8px;">{{ data.province }}</td>
          <td style="border:1px solid #ddd; padding:8px;">{{ data.time }}</td>
          <td style="border:1px solid #ddd; padding:8px;">{{ data.str }}</td>
          <td style="border:1px solid #ddd; padding:8px;">{{ data.likes }}</td>
        </tr>
      </tbody>
    </table>
  </div>
  
</template>

<script setup>
import { ref } from 'vue'
import { findTimById } from '../api/tim'

const idInput = ref('')
const loading = ref(false)
const errorMsg = ref('')
const data = ref(null)

function onInputNumberOnly(event) {
  const value = event.target.value
  const numeric = value.replace(/\D+/g, '')
  idInput.value = numeric
}

async function onSearch() {
  errorMsg.value = ''
  data.value = null
  loading.value = true
  try {
    const res = await findTimById(idInput.value)
    data.value = res
  } catch (err) {
    errorMsg.value = err?.message || '请求失败'
  } finally {
    loading.value = false
  }
}
</script>


