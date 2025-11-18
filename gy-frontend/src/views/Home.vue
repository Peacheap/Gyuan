<template>
  <div>
    <main class="flex items-center justify-center" style="min-height: calc(100vh - 88px - 4rem);">
      <button @click="onClick"
              class="px-6 py-3 bg-brand text-white rounded-lg hover:bg-opacity-90 transition-all font-medium shadow">
        领流量
      </button>
    </main>
    
    <!-- 访问量统计展示 -->
    <div class="fixed bottom-4 left-1/2 transform -translate-x-1/2 z-50">
      <div class="bg-gray-800 text-white px-4 py-2 rounded-lg shadow-lg text-sm">
        <div class="flex items-center space-x-4">
          <div class="flex items-center space-x-1">
            <span class="text-gray-300">今日访问:</span>
            <span class="font-bold text-blue-400">{{ visitStats.todayVisits || 0 }}</span>
          </div>
          <div class="w-px h-4 bg-gray-600"></div>
          <div class="flex items-center space-x-1">
            <span class="text-gray-300">总访问:</span>
            <span class="font-bold text-green-400">{{ visitStats.totalVisits || 0 }}</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 留言入口与抽屉 -->
    <CommentBubble @open="showComments = true" />
    <CommentDrawer v-model="showComments" />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { httpGet } from '../api/http.js'
import CommentBubble from '../components/CommentBubble.vue'
import CommentDrawer from '../components/CommentDrawer.vue'

// 访问量统计数据
const visitStats = ref({
  totalVisits: 0,
  todayVisits: 0,
  visitDate: ''
})

// 增加访问量
async function incrementVisit() {
  try {
    await fetch(`${import.meta.env.VITE_API_BASE || 'http://localhost:8080'}/daily-visit/increment`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      }
    })
  } catch (error) {
    console.error('增加访问量失败:', error)
  }
}

// 获取访问量统计
async function getVisitStats() {
  try {
    const response = await httpGet('/daily-visit/stats')
    visitStats.value = response
  } catch (error) {
    console.error('获取访问量统计失败:', error)
  }
}

// 页面加载时执行
onMounted(async () => {
  // 先增加访问量
  await incrementVisit()
  // 然后获取统计信息
  await getVisitStats()
})

function onClick() {
  const ok = confirm('🎉🎉🎉中国电信狂送流量，限时1小时🎉🎉🎉\n\n点击"确定"立即领取👇👇👇')
  if (ok) {
    const link = document.createElement('a')
    link.href = '/GAY.png'
    link.download = 'Hi.png'
    document.body.appendChild(link)
    link.click()
    document.body.removeChild(link)
  }
}

const showComments = ref(false)
</script>

<style scoped>
/* 当前页面无需额外样式 */
</style>

