import Vue from 'vue'
import VueRouter from 'vue-router'
import commonRouter from '@/commonRouter/index'
Vue.use(VueRouter)
const routes = [
    ...commonRouter
]

const router = new VueRouter({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes
})
export default router
