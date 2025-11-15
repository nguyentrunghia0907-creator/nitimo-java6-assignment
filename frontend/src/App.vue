<template>
  <div id="app" class="d-flex flex-column min-vh-100">
    <Header @open-cart="isCartOpen = true" />
    <main class="flex-grow-1">
      <router-view></router-view>
    </main>
    <Footer />
    
    <!-- Cart Slide Panel -->
    <CartSlide :isOpen="isCartOpen" @close="isCartOpen = false" />
  </div>
</template>


<script setup>
import { ref } from 'vue'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import CartSlide from './components/CartSlide.vue'

const isCartOpen = ref(false)
</script>