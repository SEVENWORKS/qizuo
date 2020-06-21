<template>
  <div>
    <!-- 最上层一行的头 -->
    <!-- 控制最左侧菜单的总菜单 -->
    <div>
      <a href="" id="menu-toggle"></a>
    </div>
    <!-- 项目名称 -->
    <div>
      <a class="logo pull-left" href="#" @click="open">上辈子是条鱼</a>
    </div>

    <!-- 这一行剩下的右侧部分 -->
    <div class="media-body">
      <div class="media" id="top-menu">
        <!-- 日期 -->
        <div id="time" class="pull-right">
          <span id="hours"></span>
          :
          <span id="min"></span>
          :
          <span id="sec"></span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {};
  },
  methods: {
    open() {
      window.open("/static/htmlframe/index.html");
    },
  },
};
</script>
<style lang="scss" scoped></style>
