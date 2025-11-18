<template>
  <el-drawer v-model="visible" direction="rtl" :with-header="false" :size="drawerSize">
    <div class="p-4 space-y-4">
      <div>
        <el-form :model="form" :rules="rules" ref="formRef" label-width="64px">
          <el-form-item label="昵称" prop="nickname">
            <el-input v-model="form.nickname" maxlength="50" show-word-limit />
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="form.email" placeholder="可选" />
          </el-form-item>
          <el-form-item label="内容" prop="content">
            <el-input v-model="form.content" type="textarea" :rows="3" maxlength="1000" show-word-limit />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" :loading="submitting" @click="onSubmit">发布</el-button>
            <el-button @click="reset">清空</el-button>
          </el-form-item>
        </el-form>
      </div>

      <el-divider> 最新留言 </el-divider>

      <el-scrollbar height="60vh">
        <div v-if="list.length === 0" class="text-gray-400 text-center py-8">暂无留言</div>
        <div v-for="item in list" :key="item.id" class="py-3 border-b border-gray-200">
          <div class="text-sm text-gray-500">{{ item.nickname }} · {{ formatTime(item.createdAt) }}</div>
          <div class="mt-1 whitespace-pre-wrap">{{ item.content }}</div>
          <div class="mt-2 flex items-center gap-2">
            <el-button size="small" @click="onLike(item)">👍 {{ item.likes || 0 }}</el-button>
            <el-button size="small" text type="primary" @click="toggleReplies(item)">回复</el-button>
          </div>

          <div v-if="item.showReply" class="mt-2 p-2 bg-gray-50 rounded">
            <el-input v-model="item.reply" type="textarea" :rows="2" placeholder="回复内容..." />
            <div class="mt-2">
              <el-button size="small" type="primary" :loading="item.replying" @click="submitReply(item)">回复</el-button>
            </div>
            <div class="mt-2" v-if="item.replies && item.replies.length">
              <div v-for="rep in item.replies" :key="rep.id" class="mt-2 text-sm">
                <div class="text-gray-500">{{ rep.nickname }} · {{ formatTime(rep.createdAt) }}</div>
                <div class="whitespace-pre-wrap">{{ rep.content }}</div>
              </div>
            </div>
          </div>
        </div>
      </el-scrollbar>

      <div class="flex justify-center py-3">
        <el-pagination
          layout="prev, pager, next"
          :page-size="size"
          :total="total"
          :current-page="page"
          @current-change="onPageChange"
        />
      </div>
    </div>
  </el-drawer>
</template>

<script setup>
import { ref, watch, computed, onMounted } from 'vue'
import { createComment, listRootComments, listReplies, likeComment } from '../api/response'

const props = defineProps({
  modelValue: { type: Boolean, default: false },
})
const emit = defineEmits(['update:modelValue'])

const visible = computed({
  get: () => props.modelValue,
  set: (v) => emit('update:modelValue', v),
})

const drawerSize = computed(() => (window.innerWidth < 600 ? '100%' : '420px'))

const formRef = ref()
const submitting = ref(false)
const form = ref({ nickname: '', email: '', content: '' })
const rules = {
  nickname: [{ required: true, message: '请输入昵称', trigger: 'blur' }],
  content: [{ required: true, message: '请输入内容', trigger: 'blur' }],
}

const page = ref(1)
const size = ref(10)
const total = ref(0)
const list = ref([])

async function fetchList() {
  try {
    const data = await listRootComments(page.value, size.value)
    // 后端当前返回的是数组，先按数组处理；如改为带total的对象，再调整此处
    list.value = Array.isArray(data) ? data : (data?.list || [])
    total.value = Array.isArray(data) ? data.length : (data?.total || 0)
  } catch (e) {
    console.error('加载留言失败', e)
  }
}

function onPageChange(p) {
  page.value = p
  fetchList()
}

function reset() {
  form.value = { nickname: '', email: '', content: '' }
}

async function onSubmit() {
  try {
    await formRef.value.validate()
  } catch {
    return
  }
  submitting.value = true
  try {
    await createComment({ ...form.value })
    reset()
    page.value = 1
    await fetchList()
  } catch (e) {
    console.error('发布失败', e)
  } finally {
    submitting.value = false
  }
}

function toggleReplies(item) {
  item.showReply = !item.showReply
  if (item.showReply && !item.replies) {
    loadReplies(item)
  }
}

async function loadReplies(item) {
  try {
    const reps = await listReplies(item.id)
    item.replies = reps || []
  } catch (e) {
    console.error('加载回复失败', e)
  }
}

async function submitReply(item) {
  if (!item.reply || !item.reply.trim()) return
  item.replying = true
  try {
    await createComment({ nickname: form.value.nickname || '匿名', content: item.reply, parentId: item.id })
    item.reply = ''
    await loadReplies(item)
  } catch (e) {
    console.error('回复失败', e)
  } finally {
    item.replying = false
  }
}

async function onLike(item) {
  try {
    await likeComment(item.id)
    item.likes = (item.likes || 0) + 1
  } catch (e) {
    console.error('点赞失败', e)
  }
}

watch(visible, (v) => {
  if (v) {
    fetchList()
  }
})

onMounted(() => {
  // 可选：首次进入也预载一页
})
</script>

<style scoped>
</style>


