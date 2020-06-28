<template>
  <div>
    <!-- 最左侧和左侧头像部分 -->
    <div class="side-widgets overflow">
      <!-- 头像部分 -->
      <div class="text-center s-widget m-b-25 dropdown" id="profile-menu">
        <!-- 头像 -->
        <a href="" data-toggle="dropdown">
          <img
            class="profile-pic animated"
            src="/static/img/frame/profile-pics/profile-pic.jpg"
            alt=""
          />
        </a>
        <!-- 头像点击菜单 -->
        <ul class="dropdown-menu profile-menu">
          <li>
            <a href="#" onclick="">退出登录</a> <i class="icon left">&#61903;</i
            ><i class="icon right">&#61815;</i>
          </li>
        </ul>
        <!-- 头像下文字 -->
        <h4 class="m-0">qizuo</h4>
      </div>

      <!-- 日历 -->
      <div class="s-widget m-b-25">
        <div id="sidebar-calendar"></div>
      </div>
    </div>
  </div>
</template>
<script>
import "@static/js/frame/calendar.min.js";
export default {
  data() {
    return {};
  },
  mounted() {
    this.initCalender();
  },
  methods: {
    initCalender() {
      if ($("#sidebar-calendar")[0]) {
        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();
        $("#sidebar-calendar").fullCalendar({
          editable: false,
          events: [],
          header: {
            left: "title",
          },
        });
      }
    },
  },
};
</script>
<style lang="scss" scoped></style>
