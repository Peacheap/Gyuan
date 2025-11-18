<template>
  <div>
    <header class="fixed top-0 left-0 right-0 bg-dark z-50">
      <div class="w-full px-8 py-6 flex items-center relative">
        <div class="flex items-center gap-3" ref="leftContainer">
          <!-- 折叠时显示汉堡（平时不显示） -->
          <button
            v-if="isCollapsed"
            class="nav-btn text-white"
            title="菜单"
            @click="toggleDrawer"
          >
            <iconify-icon :icon="drawerOpen ? 'ic:round-close' : 'ic:round-menu'" class="text-2xl"></iconify-icon>
          </button>

          <!-- 左侧按钮组：折叠时不参与布局，但保留用于测量 -->
          <div
            ref="leftButtons"
            class="flex items-center gap-3"
            :style="isCollapsed ? offscreenMeasureStyle : null"
          >
            <button class="use-only-btn" @click="$emit('show-test')">
              神奇海螺
            </button>
            <div class="flex gap-2 ml-2">
              <button class="nav-text-btn text-white">关于</button>
              <button class="nav-text-btn text-white">技能</button>
              <button class="nav-text-btn text-white">项目</button>
            </div>
          </div>
        </div>

        <div class="flex-grow"></div>

        <div class="absolute left-1/2 -translate-x-1/2 cursor-pointer" ref="centerLogo" @click="$emit('go-home')">
          <h1 class="site-title">Gyuan</h1>
        </div>

      <div class="flex gap-2 items-center relative" v-click-outside="closeRightMenu">
        <a href="#" class="nav-btn text-white" title="Wechat" ref="wechatBtnRef">
          <iconify-icon icon="mdi:wechat" class="text-xl"></iconify-icon>
        </a>
        <button class="arrow-btn ghost" title="更多" @click.stop="toggleRightMenu">
          <iconify-icon :icon="rightMenuOpen ? 'mdi:chevron-up' : 'mdi:chevron-down'" class="text-base"></iconify-icon>
        </button>
        <div v-if="rightMenuOpen" class="right-dropdown" @click.stop>
          <a href="https://www.linkedin.com/in/guangyuan-lu-7411aa324/" target="_blank" rel="noopener" class="dropdown-item" title="LinkedIn">
            <iconify-icon icon="mdi:linkedin" class="text-lg"></iconify-icon>
            <span>LinkedIn</span>
          </a>
          <a href="https://github.com/Peacheap" target="_blank" rel="noopener" class="dropdown-item" title="GitHub">
            <iconify-icon icon="mdi:github" class="text-lg"></iconify-icon>
            <span>GitHub</span>
          </a>
          <a href="https://www.instagram.com/peacheap_?igsh=YmxpbGR4dnBsNDZ4" target="_blank" rel="noopener" class="dropdown-item" title="Instagram">
            <iconify-icon icon="mdi:instagram" class="text-lg"></iconify-icon>
            <span>Instagram</span>
          </a>
          <a href="#" class="dropdown-item" title="Email" ref="emailBtnRef">
            <iconify-icon icon="mdi:email" class="text-lg"></iconify-icon>
            <span>Email</span>
          </a>
        </div>
      </div>
      </div>
      <div class="header-line w-full"></div>
    </header>

    <!-- 左侧抽屉 -->
    <el-drawer
      v-model="drawerOpen"
      :direction="'ltr'"
      :with-header="false"
      :append-to-body="true"
      :modal-class="'site-drawer-overlay'"
      class="site-drawer"
    >
      <div class="drawer-content p-4 flex flex-col gap-3">
        <button class="use-only-btn text-left" @click="$emit('show-test'); closeDrawer()">
          神奇海螺
        </button>
        <div class="drawer-links flex flex-col gap-2 mt-2">
          <button class="nav-text-btn text-white text-left" @click="closeDrawer()">关于</button>
          <button class="nav-text-btn text-white text-left" @click="closeDrawer()">技能</button>
          <button class="nav-text-btn text-white text-left" @click="closeDrawer()">项目</button>
        </div>
      </div>
    </el-drawer>

    <!-- Wechat Popover -->
    <el-popover
      ref="wechatPopoverRef"
      :virtual-ref="wechatBtnRef"
      trigger="click"
      virtual-triggering
      placement="bottom-end"
    >
      <span>Gyuan1u_549</span>
    </el-popover>

    <!-- Email Popover -->
    <el-popover
      ref="emailPopoverRef"
      :virtual-ref="emailBtnRef"
      trigger="click"
      virtual-triggering
      placement="bottom-end"
    >
      <span>1ugyuan02@gmail.com</span>
    </el-popover>

    <!-- 右侧探针已移除：右侧固定为“微信 + 箭头” -->
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount, nextTick } from 'vue'
import { ClickOutside as vClickOutside } from 'element-plus'

defineEmits(['show-test', 'go-home'])

const leftContainer = ref(null)
const leftButtons = ref(null)
const centerLogo = ref(null)
const isCollapsed = ref(false)
const drawerOpen = ref(false)
const rightMenuOpen = ref(false)

const offscreenMeasureStyle = {
  position: 'fixed',
  left: '-99999px',
  top: '0',
  visibility: 'hidden',
  pointerEvents: 'none',
}

function toggleDrawer() {
  if (!isCollapsed.value) return
  drawerOpen.value = !drawerOpen.value
}

function closeDrawer() {
  drawerOpen.value = false
}

function toggleRightMenu() {
  rightMenuOpen.value = !rightMenuOpen.value
}

function closeRightMenu() {
  rightMenuOpen.value = false
}

// popover refs
const wechatBtnRef = ref()
const emailBtnRef = ref()
const wechatPopoverRef = ref()
const emailPopoverRef = ref()

// 回滞阈值，防止来回抖动
const GAP_ENTER = 8
const GAP_LEAVE = 16

function checkCollision() {
  const container = leftContainer.value
  const leftEl = leftButtons.value
  const logoEl = centerLogo.value
  if (!container || !leftEl || !logoEl) return
  const containerRect = container.getBoundingClientRect()
  const leftRect = leftEl.getBoundingClientRect()
  const logoRect = logoEl.getBoundingClientRect()
  const leftRight = containerRect.left + leftRect.width
  const nextCollapsed = isCollapsed.value
    ? leftRight >= (logoRect.left - GAP_LEAVE)
    : leftRight >= (logoRect.left - GAP_ENTER)
  if (nextCollapsed !== isCollapsed.value) {
    isCollapsed.value = nextCollapsed
    if (!isCollapsed.value) drawerOpen.value = false
  }

  // 右侧不再做碰撞检测
}

let ro
function onResize() {
  checkCollision()
}

onMounted(async () => {
  await nextTick()
  checkCollision()
  window.addEventListener('resize', onResize)
  if ('ResizeObserver' in window) {
    ro = new ResizeObserver(() => onResize())
    if (leftButtons.value) ro.observe(leftButtons.value)
    if (centerLogo.value) ro.observe(centerLogo.value)
  }
})

onBeforeUnmount(() => {
  window.removeEventListener('resize', onResize)
  if (ro && leftButtons.value) ro.unobserve(leftButtons.value)
})
</script>

<style scoped>
.header-line {
  height: 1px;
  background: linear-gradient(90deg, transparent 0%, rgba(0, 0, 0, 0.1) 30%, rgba(0, 0, 0, 0.1) 70%, transparent 100%);
}
.nav-btn {
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
.drawer-content {
  white-space: nowrap;
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
  align-items: flex-start;
}
.drawer-links .nav-text-btn {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.drawer-links {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}
/* 按钮宽度自适应文本 */
.use-only-btn,
.nav-text-btn {
  display: inline-flex;
  width: auto;
  align-items: center;
  justify-content: center;
}

/* 右侧下拉样式 */
.arrow-btn {
  background: transparent;
  padding: 2px 6px;
  border-radius: 4px;
  color: #c7c7c7;
}
.arrow-btn.ghost {
  padding: 0;
  background: transparent;
  border: none;
  line-height: 1;
}
.arrow-btn:hover { background: rgba(255,255,255,0.08); }
.right-dropdown {
  position: absolute;
  top: 100%;
  right: 0;
  margin-top: 8px;
  background: rgba(20,20,20,0.98);
  border: 1px solid rgba(255,255,255,0.08);
  border-radius: 8px;
  padding: 8px;
  min-width: 160px;
  box-shadow: 0 8px 24px rgba(0,0,0,0.35);
  z-index: 60;
}
.dropdown-item {
  display: flex;
  align-items: center;
  gap: 8px;
  color: #e5e5e5;
  padding: 6px 8px;
  border-radius: 6px;
}
.dropdown-item:hover { background: rgba(255,255,255,0.06); }
</style>
