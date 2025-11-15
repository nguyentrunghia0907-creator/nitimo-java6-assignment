import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Homepage.vue";
import ProducDetail from "../views/ProductDetail.vue";
import CheckOut from "../views/Checkout.vue";

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/products/:id',
        name: 'ProductDetail',
        component: ProducDetail
    },
    {
        path: '/checkout',
        name: 'Checkout',
        component: CheckOut
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
