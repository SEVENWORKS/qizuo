import Vue from "vue";

import App from "./App.vue";
import router from "./router";
import store from "./store";
import "../../components/index";
import global from "@static/js/frames/base/base_global";
import request from "@utils/request";

Vue.config.productionTip = false; //阻止启动生产消息，常用作指令。
Vue.prototype.$global = global.base; //全局静态变量
Vue.prototype.$request = request; //全局request
window._vm = new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
