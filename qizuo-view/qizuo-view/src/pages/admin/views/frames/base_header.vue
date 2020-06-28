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
  mounted() {
    this.initTime();
  },
  methods: {
    open() {
      window.open("/static/htmlframe/index.html");
    },
    initTime() {
      var monthNames = [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December",
      ];
      var dayNames = [
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
      ];

      // Create a newDate() object
      var newDate = new Date();

      // Extract the current date from Date object
      newDate.setDate(newDate.getDate());

      // Output the day, date, month and year
      $("#date").html(
        dayNames[newDate.getDay()] +
          " " +
          newDate.getDate() +
          " " +
          monthNames[newDate.getMonth()] +
          " " +
          newDate.getFullYear()
      );

      setInterval(function () {
        // Create a newDate() object and extract the seconds of the current time on the visitor's
        var seconds = new Date().getSeconds();

        // Add a leading zero to seconds value
        $("#sec").html((seconds < 10 ? "0" : "") + seconds);
      }, 1000);

      setInterval(function () {
        // Create a newDate() object and extract the minutes of the current time on the visitor's
        var minutes = new Date().getMinutes();

        // Add a leading zero to the minutes value
        $("#min").html((minutes < 10 ? "0" : "") + minutes);
      }, 1000);

      setInterval(function () {
        // Create a newDate() object and extract the hours of the current time on the visitor's
        var hours = new Date().getHours();

        // Add a leading zero to the hours value
        $("#hours").html((hours < 10 ? "0" : "") + hours);
      }, 1000);
    },
  },
};
</script>
<style lang="scss" scoped></style>
