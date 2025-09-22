<template>
  <div>
    <main class="flex items-center justify-center" style="min-height: calc(100vh - 88px - 4rem);">
      <button @click="onClick"
              class="px-6 py-3 bg-brand text-white rounded-lg hover:bg-opacity-90 transition-all font-medium shadow">
        Click Me
      </button>
    </main>
  </div>
</template>

<script setup>
function onClick() {
  const ok = confirm('如果你点击了这个按钮，那么你是Gay。\n\n点击“确定”认清你自己。')
  if (ok) {
    const link = document.createElement('a')
    link.href = '/GAY.png'
    link.download = 'Hi.png'
    document.body.appendChild(link)
    link.click()
    document.body.removeChild(link)
  }
}
</script>

<style scoped>
/* 当前页面无需额外样式 */
</style>

