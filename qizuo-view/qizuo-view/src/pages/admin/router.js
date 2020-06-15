import Vue from 'vue'
import VueRouter from 'vue-router'
import commonRouter from '/src/commonRouter/index'
Vue.use(VueRouter)
const routes = [
    ...commonRouter
  // {
  //   path: '/about',
  //   name: 'about',
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
export default router
