import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

// 当前环境下的 API 基址
window.__VITE_API_BASE__ = import.meta.env.VITE_API_BASE
console.log('VITE_API_BASE =', window.__VITE_API_BASE__)

createApp(App)
  .use(ElementPlus)
  .mount('#app')