<template>
  <index :show="isShow">{{ msg }}</index>
</template>

<script>
import index from "./index";
export default {
  components: {
    index,
  },
  data() {
    return {
      isShow: false,
    };
  },
  computed: {
    msg() {
      console.log(this.$global);
      return this.$global;
    },
  },
  watch: {
    msg(newVal, oldVal) {
      console.log(33333);
    },
  },
};
</script>

<style scoped></style>
