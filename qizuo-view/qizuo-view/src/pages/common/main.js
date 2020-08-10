//base
import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import global from "@utils/base_global";
import request from "@utils/request";
import "../../components/index";
import "@assets/css/frames/frame.css";
import "@assets/css/base_utils.css";
import "@utils/base_utils";
//frames
import Cookies from "js-cookie";
import "normalize.css/normalize.css"; // a modern alternative to CSS resets
import Element from "element-ui";
Vue.use(Element, {
  size: Cookies.get("size") || "medium", // set element-ui default size
});
import "@assets/scss/frames/index.scss"; // global css
import "@assets/icons/frames"; // icon
import "@router/permission"; // permission control
import "@utils/frames/error-log"; // error log
import * as filters from "@filters/frames"; // global filters
Object.keys(filters).forEach((key) => {
  Vue.filter(key, filters[key]);
});
if (process.env.NODE_ENV !== "production") {
  const { mockXHR } = require("mock");
  mockXHR();
}
//config
Vue.config.productionTip = false; //阻止启动生产消息，常用作指令。
Vue.prototype.$global = global; //全局静态变量
Vue.prototype.$request = request; //全局request
window._vm = new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
