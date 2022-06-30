import './router_permission'

const modulesFiles = require.context("./router", false, /\.js$/);
const modules = modulesFiles.keys().reduce((modules, modulePath) => {
  // set './app.js' => 'app'
  const moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, "$1");
  const value = modulesFiles(modulePath);
  modules[moduleName] = value.default;
  return modules;
}, {});

new VueRouter({
    mode: "hash",
    scrollBehavior: () => ({ y: 0 }),
    base: process.env.BASE_URL,
    routes: modules,
});