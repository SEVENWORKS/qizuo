//StyleLint 是『一个强大的、现代化的 CSS 检测工具』, 与 ESLint 类似, 是通过定义一系列的编码风格规则帮助我们避免在样式表中出现错误.
//它由PostCSS提供
module.exports = {
  ignoreFiles: ["**/frames/**", "public", "dist", "node_modules"], //忽略文件
  // extends: ["stylelint-config-standard", "stylelint-config-recommended"], //继承规则
  rules: {}, //自定义规则
};
