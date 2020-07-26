<template>
  <div class="panel panel-default tile">
    <div class="panel-heading">
      <slot name="header"></slot>
    </div>
    <div class="panel-body">
      <slot name="body"></slot>
    </div>
    <div class="panel-footer">
      <slot name="footer"></slot>
    </div>
  </div>
</template>

<script>
export default {
  props: [],
};
</script>

<style scoped></style>
