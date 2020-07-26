const modulesFiles = require.context("./frames", true, /\.js$/);
// you do not need `import app from './modules/app'`
// it will auto require all vuex module from modules file
const modules = modulesFiles.keys().reduce((modules, modulePath) => {
  // set './app.js' => 'app'
  const moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, "$1");
  const value = modulesFiles(modulePath);
  modules[moduleName] = value.default;
  return modules;
}, {});
//公共store
export default Object.assign(modules, {
  base: {
    state: {
      alertMsg: "", //弹框消息
    },
    mutations: {
      updateAlertMsg(state, alertMsg) {
        state.alertMsg = alertMsg;
      },
    },
    actions: {},
  },
});
