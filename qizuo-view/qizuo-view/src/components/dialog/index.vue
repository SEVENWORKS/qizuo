<template>
  <div
    class="dialog_container"
    :style="{ 'z-index': zIndex }"
    ref="dialog_container"
    v-show="show"
  >
    <div
      class="dialog_content"
      :style="{ width: width, height: height, 'z-index': zIndex + 1 }"
    >
      <slot name="header"></slot>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    width: {
      type: String,
      default: "350px",
    },
    height: {
      type: String,
      default: "450px",
    },
    zIndex: {
      type: Number,
      default: 999,
    },
    show: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {};
  },
  mounted() {
    document
      .getElementsByTagName("body")[0]
      .appendChild(this.$refs.dialog_container);
  },
};
</script>

<style scoped>
.dialog_container {
  position: absolute;
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.dialog_content {
  /* background: #fff; */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>
