import VueRouter from 'vue-router'
import Vue from 'vue'
Vue.use(VueRouter)

//导入所有router
let routes=[]
const modulesFiles = require.context("./router", true, /\.js$/);
modulesFiles.keys().forEach((modulePath) => {
  routes=routes.concat(modulesFiles(modulePath).default)
});

const router = new VueRouter({
    mode: "hash",
    scrollBehavior: () => ({ y: 0 }),
    base: process.env.BASE_URL,
    routes
});
export default router
