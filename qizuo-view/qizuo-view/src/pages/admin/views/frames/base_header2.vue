<template>
  <div>
    <!-- 标题 -->
    <ol class="breadcrumb hidden-xs">
      <li class="active"><a href="#">首页</a></li>
      <li><a href="#"></a></li>
      <li><a href="#"></a></li>
    </ol>

    <!-- 目录 -->
    <h4 class="page-title">首页</h4>
  </div>
</template>
<script>
export default {
  data() {
    return {};
  },
  methods: {
    baseHeader2_pageTitle(title, level, title2) {
      //空值处理
      title = isNotBlank(title) ? title : "";
      level = isNotBlank(level) ? level : "";
      title2 = isNotBlank(title2) ? title2 : "";
      var lis = $(".breadcrumb").find("li");
      //主菜单点击处理
      if (0 == level) {
        lis.eq(1).find("a").text("");
        lis.eq(2).find("a").text("");
      } else if (1 == level) {
        //右侧文字处理
        lis.eq(0).find("a").text(title2);
      }
      //右侧文字处理
      lis.eq(level).find("a").text(title);
      //左侧文字处理
      $(".page-title").text(title);
    },
  },
};
</script>
<style lang="scss" scoped></style>
