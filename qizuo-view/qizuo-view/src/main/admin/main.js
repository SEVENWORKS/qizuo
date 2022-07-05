//直接导入
import "@/assets/svg"
import "@/utils/config";
import "@/utils/error";
import "@/components/index";
import "@/directive"
import './router_permission'

import "@/assets/scss/index.scss";
import 'element-ui/lib/theme-chalk/index.css';

//初始化
import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
window._vm = new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");

//vue对象挂载
import utils from "@/utils";
Vue.prototype.$utils = utils;
import request from "@/utils/request";
Vue.prototype.$request = request;
import * as filters from "@/filters";
Object.keys(filters).forEach((key) => {
  Vue.filter(key, filters[key]);
});
import Element from "element-ui";
Vue.use(Element);
Vue.config.productionTip = false;
