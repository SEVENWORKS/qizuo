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
import request from "@utils/request";
Vue.prototype.$request = request;
import utils from "@/utils";
Vue.prototype.$utils = utils;
import Element from "element-ui";
Vue.use(Element);
import * as filters from "@filters/frames";
Object.keys(filters).forEach((key) => {
  Vue.filter(key, filters[key]);
});
Vue.config.productionTip = false;

//直接导入
import "@utils/global";
import "../../components/index";
import "@assets/scss/frames/index.scss";
import "@utils/frames/error";
