//StyleLint 是『一个强大的、现代化的 CSS 检测工具』, 与 ESLint 类似, 是通过定义一系列的编码风格规则帮助我们避免在样式表中出现错误.
//它由PostCSS提供
module.exports = {
  ignoreFiles: ["public", "dist", "node_modules"], //忽略文件
  extends: ["stylelint-config-standard"], //继承规则
  rules: {
    'at-rule-no-unknown':[true,{'ignoreAtRules':['mixin,include','extend']}],
    'selector-pseudo-element-no-unknown':[true,{'ignorePseudoElements':'v-deep'}],
    'font-family-no-missing-generic-family-keyword':null
  } //自定义规则
};
