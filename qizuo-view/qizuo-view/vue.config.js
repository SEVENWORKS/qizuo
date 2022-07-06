//常量
const path = require("path"); //node path
const resolve = (dir) => path.join(__dirname, dir); //定义一个拼接方法，方便后面使用，就是和当前目录进行拼接
const IS_PROD = ["production", "prod"].includes(process.env.NODE_ENV); //是否是生成环境
const IS_DEV = ["development"].includes(process.env.NODE_ENV); //是否是开发环境

//插件
const BundleAnalyzerPlugin = require("webpack-bundle-analyzer")
  .BundleAnalyzerPlugin; //打包分析插件
const StylelintPlugin = require("stylelint-webpack-plugin"); //webpack中stylelint
const HtmlWebpackPlugin = require("html-webpack-plugin");//html打包插件 cdn
const CompressionWebpackPlugin = require("compression-webpack-plugin") //开启gzip

//多路径匹配，利用glob匹配对应的路径，然后遍历所有符合路径，最后组合成webpack的page入口
const glob = require("glob"); //glob 在webpack中对文件的路径处理非常之方便，比如当搭建多页面应用时就可以使用glob对页面需要打包文件的路径进行很好的处理。
const pages = {};
glob.sync("./src/main/**/main.js").forEach((entry) => {
  let chunk = entry.match(/\.\/src\/main\/(.*)\/main\.js/)[1];
  pages[chunk] = {
    entry,
    template: "public/index.html",
    filename: chunk+".html",
    chunk: ["chunk-vendors", "chunk-common", chunk]
  };
});

//核心webpack
module.exports = {
  publicPath: "./", // 默认'/'，部署应用包时的基本 URL 就是dist目录之后的路径
  outputDir: "dist", // 'dist', 生产环境构建文件的目录
  assetsDir: "static", // 相对于outputDir的静态资源(js、css、img、fonts)目录
  pages, //多入口配置
  configureWebpack: (config) => {

    //cdn
    if(IS_PROD){
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
          // "//unpkg.com/bootstrap/dist/css/bootstrap.min.css",
          // "//unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.min.css",
        ],
        js: [
          "//unpkg.com/vue@2.6.10/dist/vue.min.js", // 访问https://unpkg.com/vue/dist/vue.min.js获取最新版本
          "//unpkg.com/vue-router@3.0.6/dist/vue-router.min.js",
          "//unpkg.com/vuex@3.1.1/dist/vuex.min.js",
          "//unpkg.com/axios@0.19.0/dist/axios.min.js",
          "//unpkg.com/element-ui@2.10.1/lib/index.js",
          // "//unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.min.js",
        ],
      };
      config.plugins.forEach((item) => {
        if (item instanceof HtmlWebpackPlugin) {
          item.options.cdn = cdn;
          item.options.chunksSortMode = "manual";
        }
      });
    }

    const plugins = [];
    //针对开发环境的插件配置
    if (IS_DEV) {
      //Stylelint Plugin
      plugins.push(
        new StylelintPlugin({
          files: ["src/**/*.vue", "**/*.scss"],
          fix: true,
        })
      );
    }
    //生产环境
    if(IS_PROD){
      plugins.push(
          new CompressionWebpackPlugin({
              filename: "[path].gz[query]",
              algorithm: "gzip",
              test: /\.(js|css|json|txt|html|ico|svg)(\?.*)?$/i,
              threshold: 10240,
              minRatio: 0.8
          })
      );
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
      .set("@static", resolve("public/static"))
      .set("@", resolve("src"));
    Object.keys(pages).forEach(key=>{
      config.resolve.alias.set('@'+key,resolve('src/main/'+key));
      config.resolve.alias.set('@'+key+'P',resolve('src/pages/'+key));
    })

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

    // set svg-sprite-loader(配合svg组件使用)
    config.module
      .rule("svg")
      .exclude.add(resolve("src/assets/svg"))
      .end();
    config.module
      .rule("icons")
      .test(/\.svg$/)
      .include.add(resolve("src/assets/svg"))
      .end()
      .use("svg-sprite-loader")
      .loader("svg-sprite-loader")
      .options({
        symbolId: "icon-[name]",
      })
      .end();
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
          @import "@/assets/scss/mixin.scss";
          @import "@/assets/scss/variables.scss";
          `,
      },
    },
  }, //Vue CLI 项目天生支持 PostCSS 、CSS Modules 和包含 Sass 、Less 、Stylus 在内的预处理器
  lintOnSave: true, //关闭每次保存都进行检测
  runtimeCompiler: true, // 是否使用包含运行时编译器的 Vue 构建版本
  productionSourceMap: !IS_PROD, // 生产环境的 source map 默认会产生.map文件，该文件保存有原始代码与运行代码的映射关系， 浏览器可以通过它找到原始代码的位置，所以会很慢
  pwa: {}, //Progressive web apps, 渐进式Web应用
};
