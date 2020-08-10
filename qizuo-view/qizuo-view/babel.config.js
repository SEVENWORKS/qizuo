//Babel 是一个工具链，主要用于将 ECMAScript 2015+ 版本的代码转换为向后兼容的 JavaScript 语法，以便能够运行在当前和旧版本的浏览器或其他环境中。下面列出的是 Babel 能为你做的事情：
// ESLint 不允许使用 console时，可以这样配置
const IS_PROD = ["production", "prod"].includes(process.env.NODE_ENV);
const plugins = [];
if (IS_PROD) {
  plugins.push("transform-remove-console");
}
//核心配置
module.exports = {
  presets: ["@vue/cli-plugin-babel/preset"],
  plugins,
};
