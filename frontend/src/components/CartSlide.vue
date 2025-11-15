<template>
  <!-- Overlay -->
  <transition name="fade">
    <div 
      v-if="isOpen"
      class="cart-overlay"
      @click="closeCart"
    ></div>
  </transition>

  <!-- Slide Panel -->
  <transition name="slide">
    <div v-if="isOpen" class="cart-slide-panel">
      <!-- Header -->
      <div class="cart-slide-header">
        <h3>Giỏ hàng</h3>
        <button class="btn-close" @click="closeCart">
          <i class="bi bi-x" style="font-size: 24px;"></i>
        </button>
      </div>

      <!-- Status Bar -->
      <div class="free-shipping-bar p-2 text-center text-success small">
        Bạn đã được <strong>MIỄN PHÍ VẬN CHUYỂN</strong>
      </div>

      <!-- Empty Cart -->
      <div class="empty-cart-slide text-center py-5">
        <i class="bi bi-cart-x" style="font-size: 48px; color: #ccc;"></i>
        <p class="mt-3 text-muted">Giỏ hàng trống</p>
      </div>

      <!-- Footer (Sticky) -->
      <div class="cart-slide-footer">
        <!-- Summary -->
        <div class="summary-row d-flex justify-content-between mb-2">
          <span>Tạm tính:</span>
          <span>250.000đ</span>
        </div>
        <div class="summary-row d-flex justify-content-between mb-2">
          <span>Vận chuyển:</span>
          <span class="text-success">Miễn phí</span>
        </div>

        <!-- Divider -->
        <div class="border-top my-2"></div>

        <!-- Total -->
        <div class="total-row d-flex justify-content-between mb-3">
          <span class="fw-bold">TỔNG:</span>
          <span class="fw-bold text-danger" style="font-size: 18px;">
            250.000đ
          </span>
        </div>

        <!-- Buttons -->
        <button class="btn btn-danger w-100 mb-2" style="padding: 12px; font-weight: bold;" @click="goToCheckout">
          THANH TOÁN
        </button>
        <button 
          class="btn btn-outline-secondary w-100"
          @click="closeCart"
        >
          Tiếp tục mua sắm
        </button>
      </div>
    </div>
  </transition>
</template>

<script setup>
import { useRouter } from 'vue-router'

const props = defineProps({
  isOpen: {
    type: Boolean,
    required: true
  }
})

const emit = defineEmits(['close'])
const router = useRouter()

const closeCart = () => {
  emit('close')
}

const goToCheckout = () => {
  console.log('Navigating to checkout...')
  router.push('/checkout')
  emit('close')
}
</script>

<style scoped>
/* Overlay */
.cart-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1030;
}

/* Slide Panel */
.cart-slide-panel {
  position: fixed;
  right: 0;
  top: 0;
  bottom: 0;
  width: 420px;
  background-color: white;
  box-shadow: -2px 0 8px rgba(0, 0, 0, 0.15);
  z-index: 1040;
  display: flex;
  flex-direction: column;
  overflow-y: auto;
}

/* Header */
.cart-slide-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
  border-bottom: 1px solid #eee;
  flex-shrink: 0;
}

.cart-slide-header h3 {
  margin: 0;
  font-size: 20px;
  font-weight: 700;
  color: #333;
}

.btn-close {
  background: none;
  border: none;
  cursor: pointer;
  padding: 0;
  color: #333;
}

.btn-close:hover {
  color: #dc3545;
}

/* Free Shipping Bar */
.free-shipping-bar {
  background-color: #f0f8f5;
  border-bottom: 1px solid #ddd;
  flex-shrink: 0;
}

/* Empty Cart */
.empty-cart-slide {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  flex-grow: 1;
}

/* Cart Items Container */
.cart-items-slide {
  flex-grow: 1;
  overflow-y: auto;
  padding: 1rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

/* Cart Item */
.cart-item-slide {
  display: grid;
  grid-template-columns: 80px 1fr 80px;
  gap: 12px;
  padding: 12px;
  background-color: #f9f9f9;
  border-radius: 6px;
  align-items: start;
}

.cart-item-img {
  width: 80px;
  height: 80px;
  object-fit: cover;
  border-radius: 4px;
  background-color: white;
}

.cart-item-info {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.product-name {
  font-size: 13px;
  font-weight: 600;
  color: #333;
  line-height: 1.3;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.product-specs {
  font-size: 11px;
}

.product-price {
  font-size: 14px;
}

/* Actions */
.cart-item-actions {
  display: flex;
  flex-direction: column;
  gap: 6px;
  align-items: flex-end;
}

.quantity-selector-slide {
  border: 1px solid #ddd;
  border-radius: 4px;
  padding: 4px;
  background: white;
}

.qty-btn-slide {
  width: 20px;
  height: 20px;
  border: none;
  background: none;
  cursor: pointer;
  font-size: 11px;
  color: #666;
  padding: 0;
  display: flex;
  align-items: center;
  justify-content: center;
}

.qty-btn-slide:hover {
  color: #dc3545;
}

.qty-display {
  font-size: 12px;
  font-weight: 600;
  min-width: 20px;
  text-align: center;
}

.btn-delete-slide {
  width: 28px;
  height: 28px;
  border: 1px solid #ddd;
  background: white;
  cursor: pointer;
  border-radius: 4px;
  color: #666;
  font-size: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.2s;
}

.btn-delete-slide:hover {
  border-color: #dc3545;
  color: #dc3545;
}

/* Subtotal */
.cart-item-subtotal {
  grid-column: 1 / -1;
  font-size: 13px;
  color: #333;
}

/* Footer */
.cart-slide-footer {
  padding: 1rem;
  border-top: 1px solid #eee;
  background-color: white;
  flex-shrink: 0;
}

.summary-row {
  font-size: 13px;
  color: #666;
}

.total-row {
  font-size: 14px;
  color: #333;
}

/* Animations */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

.slide-enter-active,
.slide-leave-active {
  transition: transform 0.3s ease;
}

.slide-enter-from {
  transform: translateX(100%);
}

.slide-leave-to {
  transform: translateX(100%);
}

/* Responsive */
@media (max-width: 576px) {
  .cart-slide-panel {
    width: 100%;
  }

  .cart-item-slide {
    grid-template-columns: 60px 1fr 60px;
  }

  .cart-item-img {
    width: 60px;
    height: 60px;
  }

  .product-name {
    font-size: 12px;
  }
}
</style>
