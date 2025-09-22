<script setup>
import { ref } from 'vue'
import SiteHeader from './components/SiteHeader.vue'
import Home from './views/Home.vue'
import Test from './views/Test.vue'

const currentView = ref('home')

function showTest() {
  currentView.value = 'test'
}

function goHome() {
  currentView.value = 'home'
}
</script>

<template>
  <div>
    <SiteHeader @show-test="showTest" @go-home="goHome" />
    <div style="padding-top:88px;">
      <Home v-if="currentView === 'home'" />
      <Test v-else />
    </div>
  </div>
</template>

<style scoped>
</style>
