//核心配置，包括打包什么的
const BundleAnalyzerPlugin = require("webpack-bundle-analyzer")
  .BundleAnalyzerPlugin; //打包分析插件
const webpack = require("webpack"); //webpack引入
const StylelintPlugin = require("stylelint-webpack-plugin"); //webpack中stylelint
const HtmlWebpackPlugin = require("html-webpack-plugin");

const path = require("path"); //node path
const resolve = (dir) => path.join(__dirname, dir); //定义一个拼接方法，方便后面使用，就是和当前目录进行拼接
const IS_PROD = ["production", "prod"].includes(process.env.NODE_ENV); //是否是生成环境
const IS_DEV = ["development"].includes(process.env.NODE_ENV); //是否是开发环境

//多路径匹配，利用glob匹配对应的路径，然后遍历所有符合路径，最后组合成webpack的page入口
const glob = require("glob"); //glob 在webpack中对文件的路径处理非常之方便，比如当搭建多页面应用时就可以使用glob对页面需要打包文件的路径进行很好的处理。
const pagesInfo = require("./pages.config"); //入口配置文件
const pages = {};
glob.sync("./src/pages/**/main.js").forEach((entry) => {
  let chunk = entry.match(/\.\/src\/pages\/(.*)\/main\.js/)[1];
  const curr = pagesInfo[chunk];
  if (curr) {
    pages[chunk] = {
      entry,
      ...curr,
      chunk: ["chunk-vendors", "chunk-common", chunk],
    };
  }
});

//核心webpack
module.exports = {
  publicPath: IS_PROD ? process.env.VUE_APP_PUBLIC_PATH : "./", // 默认'/'，部署应用包时的基本 URL 就是dist目录之后的路径
  outputDir: process.env.outputDir || "dist", // 'dist', 生产环境构建文件的目录
  // assetsDir: "", // 相对于outputDir的静态资源(js、css、img、fonts)目录
  //多入口配置
  pages,
  configureWebpack: (config) => {
    //防止将某些 import 的包(package)打包到 bundle 中，而是在运行时(runtime)再去从外部获取这些扩展依赖
    config.externals = {
      vue: "Vue",
      "element-ui": "ELEMENT",
      "vue-router": "VueRouter",
      vuex: "Vuex",
      axios: "axios",
    };

    //修改plugins参数
    //在Vue项目中，引入到工程中的所有js、css文件，编译时都会被打包进vendor.js，浏览器在加载该文件之后才能开始显示首屏。若是引入的库众多，那么vendor.js文件体积将会相当的大，影响首开的体验。
    //解决方法是，将引用的外部js、css文件剥离开来，不编译到vendor.js中，而是用资源的形式引用，这样浏览器可以使用多个线程异步将vendor.js、外部的js等加载下来，达到加速首开的目的。
    //外部的库文件，可以使用CDN资源，或者别的服务器资源等。
    //cdn加速文件下载
    const cdn = {
      // 访问https://unpkg.com/element-ui/lib/theme-chalk/index.css获取最新版本
      css: [
        "//unpkg.com/element-ui@2.10.1/lib/theme-chalk/index.css",
        "//unpkg.com/bootstrap/dist/css/bootstrap.min.css",
        "//unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.min.css",
      ],
      js: [
        "//unpkg.com/vue@2.6.10/dist/vue.min.js", // 访问https://unpkg.com/vue/dist/vue.min.js获取最新版本
        "//unpkg.com/vue-router@3.0.6/dist/vue-router.min.js",
        "//unpkg.com/vuex@3.1.1/dist/vuex.min.js",
        "//unpkg.com/axios@0.19.0/dist/axios.min.js",
        "//unpkg.com/element-ui@2.10.1/lib/index.js",
        "//unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.min.js",
      ],
    };
    config.plugins.forEach((item) => {
      if (item instanceof HtmlWebpackPlugin) {
        item.options.cdn = cdn;
        item.options.chunksSortMode = "manual";
      }
    });

    const plugins = [];
    if (IS_DEV) {
      //Stylelint Plugin
      plugins.push(
        new StylelintPlugin({
          files: ["src/**/*.vue", "src/assets/**/*.scss"],
          fix: true,
        })
      );
      // 关闭host check，方便使用ngrok之类的内网转发工具
      config.devServer = {
        disableHostCheck: true,
      };
    }
    //合并plugins
    config.plugins = [...config.plugins, ...plugins];
  }, //如果这个值是一个函数，则会接收被解析的配置作为参数。该函数及可以修改配置并不返回任何东西，也可以返回一个被克隆或合并过的配置版本
  chainWebpack: (config) => {
    // 修复HMR(热更新)
    config.resolve.symlinks(true);

    // 配置别名，在项目中可缩减引用路径
    config.resolve.alias
      .set("vue$", "vue/dist/vue.esm.js")
      .set("@", resolve("src"))
      .set("~", resolve("public"))
      .set("@comp", resolve("src/components"))
      .set("@pages", resolve("src/pages"))
      .set("@utils", resolve("src/utils"))
      .set("@directive", resolve("src/directive"))
      .set("@filters", resolve("src/filters"))
      .set("@apis", resolve("src/apis"))
      .set("@static", resolve("public/static"));

    //优化处理压缩图片和打包分析
    if (IS_PROD) {
      // 压缩图片
      config.module
        .rule("images")
        .test(/\.(png|jpe?g|gif|svg)(\?.*)?$/)
        .use("image-webpack-loader")
        .loader("image-webpack-loader")
        .options({
          mozjpeg: { progressive: true, quality: 65 },
          optipng: { enabled: false },
          pngquant: { quality: [0.65, 0.9], speed: 4 },
          gifsicle: { interlaced: false },
        });
      // 打包分析
      config.plugin("webpack-report").use(BundleAnalyzerPlugin, [
        {
          analyzerMode: "static",
        },
      ]);
    }
    return config;
  }, //Vue CLI 内部的 webpack 配置是通过 webpack-chain 维护的。这个库提供了一个 webpack 原始配置的上层抽象，使其可以定义具名的 loader 规则和具名插件，并有机会在后期进入这些规则并对它们的选项进行修改。
  // css,
  css: {
    extract: IS_PROD,
    sourceMap: false, //ng serve默认会产生.map文件，该文件保存有原始代码与运行代码的映射关系， 浏览器可以通过它找到原始代码的位置，所以会很慢
    loaderOptions: {
      // 给 sass-loader 传递选项
      scss: {
        // 向全局sass样式传入共享的全局变量, $src可以配置图片cdn前缀
        // 详情: https://cli.vuejs.org/guide/css.html#passing-options-to-pre-processor-loaders
        prependData: `
          $src: "${process.env.VUE_APP_BASE_API}";
          `,
      },
    },
  }, //Vue CLI 项目天生支持 PostCSS 、CSS Modules 和包含 Sass 、Less 、Stylus 在内的预处理器
  lintOnSave: true, //关闭每次保存都进行检测
  runtimeCompiler: true, // 是否使用包含运行时编译器的 Vue 构建版本
  productionSourceMap: !IS_PROD, // 生产环境的 source map 默认会产生.map文件，该文件保存有原始代码与运行代码的映射关系， 浏览器可以通过它找到原始代码的位置，所以会很慢
  pwa: {}, //Progressive web apps, 渐进式Web应用
  // 跨越代理
  devServer: {
    // overlay: { // 让浏览器 overlay 同时显示警告和错误
    //   warnings: true,
    //   errors: true
    // },
    // open: false, // 是否打开浏览器
    // host: "localhost",
    // port: "8080", // 代理断就
    // https: false,
    // hotOnly: false, // 热更新
    // proxy: {
    //   "/api": {
    //     target:
    //       "https://www.easy-mock.com/mock/5bc75b55dc36971c160cad1b/sheets", // 目标代理接口地址
    //     secure: false,
    //     changeOrigin: true, // 开启代理，在本地创建一个虚拟服务端
    //     // ws: true, // 是否启用websockets
    //     pathRewrite: {
    //       "^/api": "/",
    //     },
    //   },
    // },
  },
};
