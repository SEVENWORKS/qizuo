//多页面可配置文件
module.exports = {
  admin: {
    template: "public/index.html",
    filename: "admin.html",
    title: "后台管理",
  }, //管理台界面
  common: {
    template: "public/index.html",
    filename: "common.html",
    title: "常用页面",
  }, //基本界面
  frames: {
    template: "public/index.html",
    filename: "frames.html",
    title: "框架页面",
  }, //基本框架，基于vue-element-admin改造，感谢
};
