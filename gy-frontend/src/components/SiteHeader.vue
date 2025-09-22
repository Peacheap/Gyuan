<template>
  <header class="fixed top-0 left-0 right-0 bg-dark z-50">
    <div class="w-full px-8 py-6 flex items-center relative">
      <div class="flex items-center gap-3">
        <button class="nav-btn text-white" title="菜单">
          <iconify-icon icon="ic:round-menu" class="text-2xl"></iconify-icon>
        </button>
        <button class="use-only-btn" @click="$emit('show-test')">
          唯一能用的按钮
        </button>
        <div class="flex gap-2 ml-2">
          <button class="nav-text-btn text-white">关于</button>
          <button class="nav-text-btn text-white">技能</button>
          <button class="nav-text-btn text-white">项目</button>
        </div>
      </div>

      <div class="flex-grow"></div>

      <div class="absolute left-1/2 -translate-x-1/2 cursor-pointer" @click="$emit('go-home')">
        <h1 class="site-title">Gyuan</h1>
      </div>

      <div class="flex gap-2">
        <a href="#" class="nav-btn text-white" title="微信">
          <iconify-icon icon="mdi:wechat" class="text-xl"></iconify-icon>
        </a>
        <a href="#" class="nav-btn text-white" title="LinkedIn">
          <iconify-icon icon="mdi:linkedin" class="text-xl"></iconify-icon>
        </a>
        <a href="#" class="nav-btn text-white" title="GitHub">
          <iconify-icon icon="mdi:github" class="text-xl"></iconify-icon>
        </a>
        <a href="#" class="nav-btn text-white" title="Instagram">
          <iconify-icon icon="mdi:instagram" class="text-xl"></iconify-icon>
        </a>
        <a href="#" class="nav-btn text-white" title="邮箱">
          <iconify-icon icon="mdi:email" class="text-xl"></iconify-icon>
        </a>
      </div>
    </div>
    <div class="header-line w-full"></div>
  </header>
</template>

<script setup>
defineEmits(['show-test', 'go-home'])
</script>

<style scoped>
.header-line {
  height: 1px;
  background: linear-gradient(90deg, transparent 0%, rgba(0, 0, 0, 0.1) 30%, rgba(0, 0, 0, 0.1) 70%, transparent 100%);
}
.nav-btn {
  padding: 0.375rem 1rem;
  border-radius: 9999px;
  transition: all .2s ease;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  line-height: 1;
}
.nav-text-btn {
  padding: 0.375rem 1rem;
  border-radius: 0.5rem;
  transition: all .2s ease;
}
.use-only-btn {
  padding: 0.375rem 1rem;
  border-radius: 0.5rem;
  background: #3b82f6;
  color: #fff;
  transition: all .2s ease;
}
.use-only-btn:hover {
  opacity: .9;
}
.site-title {
  font-family: 'Great Vibes', cursive;
  color: #c7c7c7;
  font-size: 37px;
  line-height: 44px;
  letter-spacing: 1px;
  font-weight: normal;
}
</style>

