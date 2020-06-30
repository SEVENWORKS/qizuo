<template>
  <div
    :class="{
      alert: true,
      'alert-success': type == 1,
      'alert-info': type == 2,
      'alert-warning': type == 3,
      'alert-danger': type == 4,
      'alert-dismissable': close,
      'alert-icon': icon,
      fade: close,
      in: close,
    }"
  >
    <button
      type="button"
      class="close"
      data-dismiss="alert"
      aria-hidden="true"
      v-if="close"
    >
      x
    </button>
    <slot></slot>
  </div>
</template>

<script>
export default {
  props: { type: { default: 1, type: Number }, icon: {}, close: {} },
};
</script>

<style scoped></style>
