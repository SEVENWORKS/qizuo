<template>
  <div id="calendar" class="p-relative p-10 m-b-20 w-100 h-100">
    <!-- Calendar Views -->
    <ul class="calendar-actions list-inline clearfix">
      <li class="p-r-0">
        <a data-view="month" href="#" class="tooltips" title="Month">
          <i class="sa-list-month"></i>
        </a>
      </li>
      <li class="p-r-0">
        <a data-view="agendaWeek" href="#" class="tooltips" title="Week">
          <i class="sa-list-week"></i>
        </a>
      </li>
      <li class="p-r-0">
        <a data-view="agendaDay" href="#" class="tooltips" title="Day">
          <i class="sa-list-day"></i>
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
import "@static/js/frame/calendar.min.js";
export default {
  props: [],
  mounted() {
    this.init();
  },
  methods: {
    init() {
      var date = new Date();
      var d = date.getDate();
      var m = date.getMonth();
      var y = date.getFullYear();
      $("#calendar").fullCalendar({
        header: {
          center: "title",
          left: "prev, next",
          right: "",
        },
        selectable: true,
        selectHelper: true,
        editable: true,
        events: [],
      });
    },
  },
};
</script>

<style scoped></style>
