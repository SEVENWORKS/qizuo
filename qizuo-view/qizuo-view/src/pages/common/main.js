import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "../../components/index";
import Global from "@static/js/system/base_Global";

Vue.config.productionTip = false; //阻止启动生产消息，常用作指令。
Vue.prototype.Global = Global.base; //全局静态变量
window._vm = new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
