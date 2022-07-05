import getters from "./store_getters";
import Vuex from 'vuex'
import Vue from 'vue'
Vue.use(Vuex)

//导入所有storemodules
const modulesFiles = require.context("./store", false, /\.js$/);
const modules = modulesFiles.keys().reduce((mus, modulePath) => {
  const moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, "$1");
  const value = modulesFiles(modulePath);
  mus[moduleName] = value.default;
  return mus;
}, {});

const store= new Vuex.Store({
    modules,
    getters
});

export default store