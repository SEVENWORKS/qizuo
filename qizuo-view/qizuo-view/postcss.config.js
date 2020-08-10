//对css做一些操作，如同less和sass
//Webpack 中使用 postcss-loader 来执行插件处理
module.exports = {
  plugins: {
    autoprefixer: { remove: false }, //Autoprefixer 是一个流行的 PostCSS 插件，其作用是为 CSS 中的属性添加浏览器特定的前缀
  },
};
