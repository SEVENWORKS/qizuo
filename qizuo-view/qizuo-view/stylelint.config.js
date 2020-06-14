//StyleLint 是『一个强大的、现代化的 CSS 检测工具』, 与 ESLint 类似, 是通过定义一系列的编码风格规则帮助我们避免在样式表中出现错误.
module.exports = {
  ignoreFiles: ["**/*.js", "src/assets/css/element-variables.scss", "theme/"],
  extends: ["stylelint-config-standard", "stylelint-config-prettier"],
  rules: {
    "no-empty-source": null,
    "at-rule-no-unknown": [
      true,
      {
        ignoreAtRules: ["extend", "mixin"]
      }
    ]
  }
};
