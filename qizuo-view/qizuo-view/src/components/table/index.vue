<template>
  <!-- 有悬浮效果的表格 -->
  <div class="block-area">
    <div class="table-responsive overflow">
      <table class="table table-bordered table-hover tile">
        <thead>
          <slot name="header"></slot>
        </thead>
        <tbody>
          <slot name="body"></slot>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped></style>
