<template>
  <div>
    <!-- 头部分 -->
    <header id="header" class="media">
      <base-header></base-header>
    </header>
    <div class="clearfix"></div>

    <!-- 左侧菜单栏和右侧iframe主体 -->
    <section id="main" class="p-relative" role="main">
      <!-- 左侧菜单栏 -->
      <aside id="sidebar">
        <base-leftbar></base-leftbar>
        <base-leftbar2></base-leftbar2>
      </aside>
      <!-- 头部分2 -->
      <section id="content" class="container">
        <!--iframe主体 -->
        <section id="baseFrame">
          <div id="app">
            <router-view />
          </div>
        </section>
      </section>
    </section>

    <!-- 脚部分 -->
    <footer>
      <base-footer></base-footer>
    </footer>

    <!-- alert弹框 -->
    <qz-alert></qz-alert>
  </div>
</template>

<script>
import baseFooter from "./views/frames/base_footer";
import baseHeader from "./views/frames/base_header";
import baseLeftbar from "./views/frames/base_leftbar";
import baseLeftbar2 from "./views/frames/base_leftbar2";
export default {
  components: {
    baseFooter,
    baseHeader,
    baseLeftbar,
    baseLeftbar2,
  },
};
</script>

<style lang="scss" scoped></style>
