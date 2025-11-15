<template>
  <div class="product-detail-container py-5">
    <div class="container">
      <div class="row g-5">
        <!-- Product Images Gallery -->
        <div class="col-lg-6">
          <div class="product-gallery">
            <!-- Main Image -->
            <div class="main-image-wrapper">
              <img 
                :src="mainImage" 
                :alt="product.name" 
                class="main-image"
              >
              <div class="discount-badge-large" v-if="product.discount">
                {{ product.discount }}%
              </div>
            </div>

            <!-- Thumbnail Images -->
            <div class="thumbnail-gallery mt-3 d-flex gap-2">
              <img 
                v-for="(image, index) in product.images" 
                :key="index"
                :src="image"
                :alt="`Product ${index + 1}`"
                class="thumbnail-image"
                :class="{ active: mainImage === image }"
                @click="mainImage = image"
              >
            </div>
          </div>
        </div>

        <!-- Product Info -->
        <div class="col-lg-6">
          <div class="product-info">
            <!-- Rating -->
            <div class="rating-section d-flex align-items-center gap-2 mb-3">
              <div class="stars">⭐⭐⭐⭐⭐</div>
              <span class="rating-text">(2.021 đã bán)</span>
            </div>

            <!-- Product Name -->
            <h1 class="product-name mb-3">{{ product.name }}</h1>

            <!-- Price Section -->
            <div class="price-section bg-light p-3 rounded mb-4">
              <div class="d-flex align-items-center gap-3 mb-2">
                <span class="price-current fw-bold">{{ formatPrice(product.currentPrice) }}đ</span>
                <span class="price-original text-muted text-decoration-line-through">
                  {{ formatPrice(product.originalPrice) }}đ
                </span>
              </div>
              <div class="discount-info text-danger">
                <strong>Tiết kiệm: {{ formatPrice(product.originalPrice - product.currentPrice) }}đ</strong>
              </div>
            </div>

            <!-- Product Status -->
            <div class="status-section mb-4 p-3 bg-light rounded">
              <div class="d-flex justify-content-between mb-2">
                <span>Tình trạng:</span>
                <span class="fw-bold text-success">Còn hàng ({{ product.stock }} sản phẩm)</span>
              </div>
              <div class="d-flex justify-content-between">
                <span>Lượt xem:</span>
                <span class="fw-bold">{{ product.views }}</span>
              </div>
            </div>

            <!-- Size Selection -->
            <div class="size-section mb-4">
              <label class="form-label fw-bold">Kích cỡ:</label>
              <div class="size-options d-flex gap-2">
                <button 
                  v-for="size in product.sizes" 
                  :key="size"
                  class="size-btn"
                  :class="{ active: selectedSize === size }"
                  @click="selectedSize = size"
                >
                  {{ size }}
                </button>
              </div>
            </div>

            <!-- Color Selection -->
            <div class="color-section mb-4">
              <label class="form-label fw-bold">Màu sắc:</label>
              <div class="color-options d-flex gap-3">
                <div 
                  v-for="color in product.colors" 
                  :key="color.name"
                  class="color-option"
                  :class="{ active: selectedColor === color.name }"
                  @click="selectedColor = color.name"
                >
                  <div 
                    class="color-circle" 
                    :style="{ backgroundColor: color.code }"
                  ></div>
                  <span class="color-name">{{ color.name }}</span>
                </div>
              </div>
            </div>

            <!-- Quantity Selection -->
            <div class="quantity-section mb-4">
              <label class="form-label fw-bold">Số lượng:</label>
              <div class="quantity-selector d-flex align-items-center gap-2">
                <button 
                  class="qty-btn"
                  @click="quantity > 1 && quantity--"
                >
                  −
                </button>
                <input 
                  v-model.number="quantity"
                  type="number"
                  class="qty-input"
                  min="1"
                  max="999"
                >
                <button 
                  class="qty-btn"
                  @click="quantity < product.stock && quantity++"
                >
                  +
                </button>
                <span class="text-muted ms-3">Còn lại: {{ product.stock }} sản phẩm</span>
              </div>
            </div>

            <!-- Action Buttons -->
            <div class="action-buttons d-flex gap-3 mb-4">
              <button class="btn btn-danger btn-add-to-cart flex-grow-1" @click="addToCart">
                <i class="bi bi-cart-plus"></i> Thêm vào giỏ
              </button>
              <button class="btn btn-outline-danger btn-favorite">
                <i class="bi bi-heart"></i> Yêu thích
              </button>
            </div>

            <!-- Delivery Info -->
            <div class="delivery-info bg-light p-3 rounded mb-4">
              <div class="delivery-row d-flex justify-content-between py-2 border-bottom">
                <span><i class="bi bi-truck"></i> Miễn phí vận chuyển</span>
                <span class="text-muted">Đơn từ 200.000đ</span>
              </div>
              <div class="delivery-row d-flex justify-content-between py-2 border-bottom">
                <span><i class="bi bi-shield-check"></i> Chính hãng</span>
                <span class="text-muted">100% chính hãng</span>
              </div>
              <div class="delivery-row d-flex justify-content-between py-2">
                <span><i class="bi bi-arrow-counterclockwise"></i> Đổi/Trả</span>
                <span class="text-muted">Trong 30 ngày</span>
              </div>
            </div>

            <!-- Shop Info -->
            <div class="shop-info bg-light p-3 rounded">
              <div class="d-flex align-items-center gap-3">
                <div class="shop-avatar">
                  <img src="https://via.placeholder.com/50" alt="Shop" class="img-fluid rounded-circle">
                </div>
                <div class="shop-details flex-grow-1">
                  <h6 class="mb-1">{{ product.shopName }}</h6>
                  <small class="text-muted">{{ product.shopFollowers }} người theo dõi</small>
                </div>
                <button class="btn btn-outline-secondary btn-sm">
                  <i class="bi bi-plus"></i> Theo dõi
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Product Description Section -->
      <div class="row mt-5">
        <div class="col-12">
          <div class="description-section">
            <h3 class="section-title mb-4 pb-3 border-bottom">Chi tiết sản phẩm</h3>
            
            <!-- Description Tabs -->
            <div class="nav nav-tabs mb-4" role="tablist">
              <button 
                class="nav-link active"
                id="description-tab"
                data-bs-toggle="tab"
                data-bs-target="#description"
                type="button"
                role="tab"
              >
                Mô tả sản phẩm
              </button>
              <button 
                class="nav-link"
                id="specs-tab"
                data-bs-toggle="tab"
                data-bs-target="#specs"
                type="button"
                role="tab"
              >
                Thông số kỹ thuật
              </button>
            </div>

            <!-- Tab Content -->
            <div class="tab-content">
              <!-- Description Tab -->
              <div 
                class="tab-pane fade show active"
                id="description"
                role="tabpanel"
              >
                <div class="description-content">
                  <p>{{ product.description }}</p>
                  <img 
                    v-if="product.descriptionImage"
                    :src="product.descriptionImage"
                    alt="Product description"
                    class="img-fluid mt-3"
                  >
                </div>
              </div>

              <!-- Specs Tab -->
              <div 
                class="tab-pane fade"
                id="specs"
                role="tabpanel"
              >
                <table class="table table-borderless">
                  <tbody>
                    <tr v-for="(value, key) in product.specifications" :key="key" class="spec-row">
                      <td class="spec-label fw-bold" style="width: 30%">{{ key }}</td>
                      <td class="spec-value">{{ value }}</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const productId = route.params.id

// Product Data
const product = ref({
  id: 1,
  name: 'Áo khoác lông vũ nữ dáng lộ siêu nhẹ',
  images: [
    'https://cdn.hstatic.net/products/200000503583/cdn01085_1_05c9b02871394e849cbbb413fcfeb97b_grande.jpg',
    'https://cdn.hstatic.net/products/200000503583/cdn01085_2_05c9b02871394e849cbbb413fcfeb97b_grande.jpg',
    'https://cdn.hstatic.net/products/200000503583/cdn01085_3_05c9b02871394e849cbbb413fcfeb97b_grande.jpg',
    'https://cdn.hstatic.net/products/200000503583/cdn01085_4_05c9b02871394e849cbbb413fcfeb97b_grande.jpg',
  ],
  currentPrice: 795000,
  originalPrice: 1250000,
  discount: 55,
  stock: 150,
  views: '2.021 lượt xem',
  sizes: ['XS', 'S', 'M', 'L', 'XL', 'XXL'],
  colors: [
    { name: 'Đỏ Bordo', code: '#8B1538' },
    { name: 'Đen', code: '#000000' },
    { name: 'Xám', code: '#808080' },
  ],
  description: `Áo khoác lông vũ nữ với thiết kế dáng lộ hiện đại, được làm từ chất liệu lông vũ cao cấp siêu nhẹ. 
  Thiết kế tôn dáng, thoải mái, phù hợp cho mọi mùa. Sản phẩm đã qua kiểm tra chất lượng nghiêm ngặt.
  
  Đặc điểm:
  - Chất liệu lông vũ 100% siêu nhẹ
  - Dáng lộ tôn dáng
  - Khóa kéo êm
  - Túi tay tiện dụng
  - Thiết kế thời trang, phù hợp với nhiều phong cách`,
  descriptionImage: 'https://via.placeholder.com/800x400',
  specifications: {
    'Chất liệu': 'Lông vũ 100%',
    'Độ dày': 'Nhẹ (300g)',
    'Kích cỡ': 'XS - XXL',
    'Màu sắc': 'Đỏ Bordo, Đen, Xám',
    'Bảo quản': 'Giặt tay ở 30°C',
    'Xuất xứ': 'Việt Nam',
    'Năm phát hành': '2024',
  },
  shopName: 'Coolmate Official Store',
  shopFollowers: '150K',
})

// State
const mainImage = ref(product.value.images[0])
const selectedSize = ref('M')
const selectedColor = ref('Đỏ Bordo')
const quantity = ref(1)

// Methods
const formatPrice = (price) => {
  return price.toLocaleString('vi-VN')
}

const addToCart = () => {
  console.log({
    productId: product.value.id,
    productName: product.value.name,
    size: selectedSize.value,
    color: selectedColor.value,
    quantity: quantity.value,
    price: product.value.currentPrice,
  })
  alert(`Đã thêm ${quantity.value} sản phẩm vào giỏ!`)
}
</script>

<style scoped>
.product-detail-container {
  background-color: #f8f9fa;
  min-height: 100vh;
}

/* Gallery Section */
.product-gallery {
  position: sticky;
  top: 100px;
}

.main-image-wrapper {
  position: relative;
  background: white;
  border-radius: 8px;
  overflow: hidden;
  margin-bottom: 1rem;
}

.main-image {
  width: 100%;
  height: 600px;
  object-fit: cover;
  display: block;
}

.discount-badge-large {
  position: absolute;
  top: 15px;
  left: 15px;
  background: black;
  color: white;
  padding: 10px 16px;
  border-radius: 6px;
  font-size: 16px;
  font-weight: bold;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
}

.thumbnail-gallery {
  overflow-x: auto;
  padding-bottom: 8px;
}

.thumbnail-image {
  width: 80px;
  height: 80px;
  object-fit: cover;
  border-radius: 6px;
  border: 2px solid transparent;
  cursor: pointer;
  transition: all 0.3s;
}

.thumbnail-image:hover {
  border-color: #dc3545;
}

.thumbnail-image.active {
  border-color: #dc3545;
  box-shadow: 0 0 8px rgba(220, 53, 69, 0.5);
}

/* Product Info Section */
.product-info {
  background: white;
  padding: 2rem;
  border-radius: 8px;
}

.rating-section {
  font-size: 14px;
}

.product-name {
  font-size: 24px;
  font-weight: 700;
  color: #333;
  line-height: 1.4;
}

/* Price Section */
.price-section {
  background-color: #fff5f5 !important;
}

.price-current {
  font-size: 32px;
  color: #dc3545;
}

.price-original {
  font-size: 16px;
}

.discount-info {
  font-size: 14px;
}

/* Status Section */
.status-section {
  font-size: 14px;
}

/* Size Selection */
.size-section {
  margin-bottom: 1.5rem;
}

.size-options {
  flex-wrap: wrap;
  gap: 10px;
}

.size-btn {
  padding: 8px 16px;
  border: 2px solid #ddd;
  background: white;
  border-radius: 6px;
  cursor: pointer;
  font-weight: 500;
  transition: all 0.3s;
}

.size-btn:hover {
  border-color: #dc3545;
  color: #dc3545;
}

.size-btn.active {
  border-color: #dc3545;
  background: #dc3545;
  color: white;
}

/* Color Selection */
.color-section {
  margin-bottom: 1.5rem;
}

.color-options {
  display: flex;
  flex-wrap: wrap;
}

.color-option {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 10px 16px;
  border: 2px solid #ddd;
  border-radius: 6px;
  cursor: pointer;
  transition: all 0.3s;
}

.color-option:hover {
  border-color: #dc3545;
}

.color-option.active {
  border-color: #dc3545;
  background: #fff5f5;
}

.color-circle {
  width: 24px;
  height: 24px;
  border-radius: 50%;
  border: 2px solid #ddd;
}

.color-name {
  font-size: 14px;
  font-weight: 500;
}

/* Quantity Selection */
.quantity-section {
  margin-bottom: 1.5rem;
}

.quantity-selector {
  max-width: 200px;
}

.qty-btn {
  width: 40px;
  height: 40px;
  border: 1px solid #ddd;
  background: white;
  border-radius: 4px;
  cursor: pointer;
  font-size: 18px;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.3s;
}

.qty-btn:hover {
  border-color: #dc3545;
  color: #dc3545;
}

.qty-input {
  width: 60px;
  height: 40px;
  text-align: center;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 16px;
}

.qty-input:focus {
  border-color: #dc3545;
  outline: none;
}

/* Action Buttons */
.btn-add-to-cart {
  padding: 12px 32px;
  font-size: 16px;
  font-weight: 600;
  border-radius: 6px;
  transition: all 0.3s;
}

.btn-add-to-cart:hover {
  background: #bd2130;
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(220, 53, 69, 0.3);
}

.btn-favorite {
  padding: 12px 24px;
  font-size: 16px;
  font-weight: 600;
  border-radius: 6px;
}

/* Delivery Info */
.delivery-info {
  background-color: #f8f9fa !important;
}

.delivery-row {
  font-size: 14px;
  color: #333;
}

.delivery-row i {
  color: #dc3545;
  margin-right: 8px;
}

/* Shop Info */
.shop-info {
  background-color: #f8f9fa !important;
}

.shop-avatar img {
  width: 50px;
  height: 50px;
}

/* Description Section */
.description-section {
  background: white;
  padding: 2rem;
  border-radius: 8px;
}

.section-title {
  font-size: 20px;
  font-weight: 700;
  color: #333;
}

.nav-tabs {
  border-bottom: 2px solid #ddd;
}

.nav-link {
  color: #666;
  border: none;
  padding: 0.75rem 1.5rem;
  border-bottom: 3px solid transparent;
  transition: all 0.3s;
}

.nav-link:hover {
  color: #dc3545;
  border-bottom-color: #dc3545;
}

.nav-link.active {
  color: #dc3545;
  border-bottom-color: #dc3545;
  background: none;
}

.description-content {
  font-size: 14px;
  line-height: 1.8;
  color: #555;
}

.spec-row {
  border-bottom: 1px solid #eee;
}

.spec-row:last-child {
  border-bottom: none;
}

.spec-label {
  color: #333;
  padding: 12px 0;
}

.spec-value {
  color: #666;
  padding: 12px 0;
}

/* Responsive */
@media (max-width: 992px) {
  .main-image {
    height: 400px;
  }

  .product-info {
    padding: 1.5rem;
  }

  .price-current {
    font-size: 24px;
  }

  .product-name {
    font-size: 20px;
  }
}

@media (max-width: 576px) {
  .main-image {
    height: 300px;
  }

  .product-gallery {
    position: static;
  }

  .product-info {
    padding: 1rem;
  }

  .price-current {
    font-size: 20px;
  }

  .product-name {
    font-size: 16px;
  }

  .thumbnail-image {
    width: 60px;
    height: 60px;
  }

  .qty-selector {
    max-width: 160px;
  }

  .btn-add-to-cart {
    padding: 10px 20px;
    font-size: 14px;
  }
}
</style>
