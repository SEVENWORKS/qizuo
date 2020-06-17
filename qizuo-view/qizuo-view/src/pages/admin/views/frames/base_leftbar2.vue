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
            src="/static/img/frame/profile-pic.jpg"
            alt=""
          />
        </a>
        <!-- 头像点击菜单 -->
        <ul class="dropdown-menu profile-menu"></ul>
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
export default {
  data() {
    return {};
  },
  methods: {
    baseLeftBar2_userMenu(userMenus) {
      if (isNotBlank(userMenus)) {
        var html = "";
        for (var i = 0; i < userMenus.length; i++) {
          //参数
          var title = isNotBlank(userMenus[i].title) ? userMenus[i].title : "";
          var url = isNotBlank(userMenus[i].url)
            ? "${pageContext.request.contextPath}/" + userMenus[i].url
            : "";
          var type = isNotBlank(userMenus[i].type) ? userMenus[i].type : "";
          var func = userMenus[i].func;
          //html
          html +=
            '<li><a href="#" onclick="baseLeftBar2_userFunc(\'' +
            url +
            "'," +
            type +
            "," +
            func +
            ')">' +
            title +
            '</a> <i class="icon left">&#61903;</i><i class="icon right">&#61815;</i>';
        }
        $("#profile-menu ul").append(html);
      }
    },
    baseLeftBar2_userFunc(url, type, func) {
      if (isNotBlank(url) && isNotBlank(type)) {
        if (type == global$urlGetType) {
          window.open(url);
        } else {
          $.post(url, {}, function (data) {
            backResultAlert(data, function () {
              if (isFunction(func)) {
                func();
              }
            });
          });
        }
      }
    },
  },
};
</script>
<style lang="scss" scoped></style>
